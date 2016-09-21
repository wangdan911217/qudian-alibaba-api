package qudian.alipay.api.services;

import com.alipay.api.domain.StudentInfo;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import qudian.alipay.api.iservice.IAlipayApiService;
import qudian.alipay.api.iservice.IAlipayLocator;

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
}
