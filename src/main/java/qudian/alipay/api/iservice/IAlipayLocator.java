package qudian.alipay.api.iservice;

import com.alipay.api.domain.StudentInfo;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;

/**
 * Created by suzunshou on 16/9/18.
 */
public interface IAlipayLocator {
    /**
     * 获取授权信息
     *
     * @param authCode
     * @return
     */
    AlipaySystemOauthTokenResponse getUserAuth(String authCode);

    /**
     * 获取用户信息
     *
     * @param accessToken 通过getUserAuth获取
     * @return
     */
    AlipayUserUserinfoShareResponse getUserInfo(String accessToken);

    /**
     * 获取学生信息
     *
     * @param accessToken 通过getUserAuth获取
     * @return
     */
    StudentInfo getStudentInfo(String accessToken);
}
