package qudian.alipay.api.iservice;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.StudentInfo;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Created by suzunshou on 16/9/16.
 */
public interface IAlipayApiService {
    /**
     * 获取授权信息 access_token
     *
     * @param authCode 授权码
     * @return
     */
    String getAccessTokenByAuthCode(String authCode);

    /**
     * 获得用户授权
     *
     * @param authCode
     * @return
     */
    AlipaySystemOauthTokenResponse getUserAuthResponse(String authCode);

    /**
     * 获取用户信息
     *
     * @param access_token
     * @return
     */
    AlipayUserUserinfoShareResponse getUserInfoByAccessToken(String access_token);

    /**
     * 获取学历信息
     *
     * @param access_token
     * @return
     */
    StudentInfo getUserEducateInfoByAccessToken(String access_token);

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
    String invokeCheckOutPage(String out_trade_no, String total_amount, String subject) throws AlipayApiException, IOException;

    /**
     * 验签
     *
     * @param params 待签名参数,
     *               可通过自定义的AlipayUtils#getParamsFromGetRequestParams(java.util.Map)获取
     * @return
     */
    boolean verify(Map<String, String> params) throws AlipayApiException;
}
