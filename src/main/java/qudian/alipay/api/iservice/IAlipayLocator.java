package qudian.alipay.api.iservice;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.StudentInfo;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;

import java.io.IOException;
import java.util.Map;

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
