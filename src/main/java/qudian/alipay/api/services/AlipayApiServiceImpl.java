package qudian.alipay.api.services;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.EduStudentInfoShareResult;
import com.alipay.api.domain.StudentInfo;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayCommerceEducateStudentinfoShareRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipayCommerceEducateStudentinfoShareResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qudian.alipay.api.config.AlipayApiConfig;
import qudian.alipay.api.exceptions.OnlineRuntimeException;
import qudian.alipay.api.iservice.IAlipayApiService;
import qudian.alipay.api.utils.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suzunshou on 16/9/16.
 */
public class AlipayApiServiceImpl implements IAlipayApiService {

    private final static Logger logger = LoggerFactory.getLogger(AlipayApiServiceImpl.class);

    private AlipayApiConfig alipayApiConfig;

    public void setAlipayApiConfig(AlipayApiConfig alipayApiConfig) {
        this.alipayApiConfig = alipayApiConfig;
    }

    /**
     * 获取授权信息 access_token
     *
     * @param authCode 授权码
     * @return
     */
    public String getAccessTokenByAuthCode(String authCode) {
        logger.info("authCode的值={}", authCode);
        AlipaySystemOauthTokenResponse response = getUserAuthResponse(authCode);
        return response.getAccessToken();
    }

    /**
     * 检查授权是否过期,
     *
     * @param userAuth
     * @return true:有效、false:无效
     */
    public boolean checkIsAuthValid(AlipaySystemOauthTokenResponse userAuth) {
        return !("40002".equals(userAuth.getCode()) && "isv.code-invalid".equals(userAuth.getSubCode()));
    }

    public AlipaySystemOauthTokenResponse getUserAuthResponse(String authCode) {
        AlipaySystemOauthTokenRequest request
                = new AlipaySystemOauthTokenRequest();
        request.setCode(authCode);
        request.setGrantType(alipayApiConfig.getGrant_type());
        try {
            AlipaySystemOauthTokenResponse response = alipayApiConfig.getAlipayClient_info().execute(request);
            if (StringUtils.isNotBlank(response.getCode()) && response.getSubCode().equals("isv.refresh-token-invalid")) {
                throw new OnlineRuntimeException(response.getSubCode(), response.getSubMsg());
            }

            return response;
        } catch (AlipayApiException e) {
            throw new OnlineRuntimeException("UN_PASS_AUTH", "未通过支付宝授权,请重试!");
        }
    }

    /**
     * 获取用户信息
     *
     * @param access_token
     * @return
     */
    public AlipayUserUserinfoShareResponse getUserInfoByAccessToken(String access_token) {
        AlipayUserUserinfoShareRequest request
                = new AlipayUserUserinfoShareRequest();
        try {
            AlipayUserUserinfoShareResponse response
                    = alipayApiConfig.getAlipayClient_info().execute(request, access_token);

            if (StringUtils.isNotBlank(response.getCode())) {
                logger.warn(response.getBody());
                throw new OnlineRuntimeException(response.getSubCode(), response.getSubMsg());
            }

            return response;
        } catch (AlipayApiException e) {
            throw new OnlineRuntimeException("UN_GET_USERINFO", "未能从支付宝获得用户信息!");
        }
    }

    /**
     * 获取学历信息
     *
     * @param access_token
     * @return
     */
    public StudentInfo getUserEducateInfoByAccessToken(String access_token) {
        AlipayCommerceEducateStudentinfoShareRequest request
                = new AlipayCommerceEducateStudentinfoShareRequest();
        AlipayCommerceEducateStudentinfoShareResponse response = null;
        try {
            response = alipayApiConfig.getAlipayClient_info().execute(request, access_token);
            if (!response.getCode().equals("10000")) {
                logger.error(response.getSubCode(), response.getSubMsg());
                throw new OnlineRuntimeException(response.getSubCode(), response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            throw new OnlineRuntimeException("GET_USER_EDUCATE_FAIL", "未能获得用户学历!");
        }

        Map<String, Object> bodyMap = JSON.parseObject(response.getBody(), HashMap.class);
        String responsResult =
                JSON.toJSONString(
                        bodyMap.get("alipay_commerce_educate_studentinfo_share_response"));
        EduStudentInfoShareResult result = JSON.parseObject(responsResult, EduStudentInfoShareResult.class);
        List<StudentInfo> studentInfos = result.getStudentInfos();
        return studentInfos.size() > 0 ? studentInfos.get(0) : null;
    }

    /**
     * 唤醒支付宝收银台,返回收银台表单
     *
     * @param out_trade_no 商户订单号
     * @param total_amount 总金额
     * @param subject      订单标题
     * @return
     * @throws AlipayApiException
     * @throws IOException
     */
    public String invokeCheckOutPage(String out_trade_no, String total_amount, String subject) throws AlipayApiException, IOException {
        Map<String, String> map = new HashMap();
        map.put("out_trade_no", out_trade_no);
        map.put("total_amount", total_amount);
        map.put("subject", subject);
        map.put("seller_id", alipayApiConfig.getSeller_id());
        map.put("product_code", alipayApiConfig.getProduct_code());
        logger.info("传递给支付宝的信息为:map={}", map);

        AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();
        alipayTradeWapPayRequest.setNotifyUrl(alipayApiConfig.getNotifyUrl());
        alipayTradeWapPayRequest.setReturnUrl(alipayApiConfig.getReturnUrl());
        alipayTradeWapPayRequest.setBizContent(JSON.toJSONString(map));
        return alipayApiConfig.getAlipayClient_pay().pageExecute(alipayTradeWapPayRequest).getBody();
    }

    /**
     * 验签
     *
     * @param params 待签名参数,
     *               可通过自定义的AlipayUtils#getParamsFromGetRequestParams(java.util.Map)获取
     * @return
     */
    public boolean verify(Map<String, String> params) throws AlipayApiException {
        return AlipaySignature.rsaCheckV1(params, alipayApiConfig.getPublicKey(), alipayApiConfig.getCharset());
    }

}

