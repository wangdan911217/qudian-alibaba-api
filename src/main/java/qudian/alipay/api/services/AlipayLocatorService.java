package qudian.alipay.api.services;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.StudentInfo;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import qudian.alipay.api.iservice.IAlipayApiService;
import qudian.alipay.api.iservice.IAlipayLocator;

import java.io.IOException;
import java.util.Map;

/**
 * Created by suzunshou on 16/9/18.
 */
public class AlipayLocatorService implements IAlipayLocator {

    private IAlipayApiService alipayApiService;

    public void setAlipayApiService(IAlipayApiService alipayApiService) {
        this.alipayApiService = alipayApiService;
    }

    /**
     * 获取授权信息
     *
     * @param authCode
     * @return
     */
    public AlipaySystemOauthTokenResponse getUserAuth(String authCode) {
        return alipayApiService.getUserAuthResponse(authCode);
    }

    /**
     * 获取用户信息
     *
     * @param accessToken 通过getUserAuth获取
     * @return
     */
    public AlipayUserUserinfoShareResponse getUserInfo(String accessToken) {
        return alipayApiService.getUserInfoByAccessToken(accessToken);
    }

    /**
     * 获取学生信息
     *
     * @param accessToken 通过getUserAuth获取
     * @return
     */
    public StudentInfo getStudentInfo(String accessToken) {
        return alipayApiService.getUserEducateInfoByAccessToken(accessToken);
    }

    /**
     * 调用收银台
     *
     * @param out_trade_no
     * @param total_amount
     * @param subject
     * @return
     * @throws AlipayApiException
     * @throws IOException
     */
    public String invokeCheckOutPage(String out_trade_no, String total_amount, String subject) throws AlipayApiException, IOException {
        return alipayApiService.invokeCheckOutPage(out_trade_no, total_amount, subject);
    }

    /**
     * 验签
     *
     * @param params 待签名参数,
     *               可通过自定义的AlipayUtils#getParamsFromGetRequestParams(java.util.Map)获取
     * @return
     */
    public boolean verify(Map<String, String> params) throws AlipayApiException {
        return alipayApiService.verify(params);
    }
}
