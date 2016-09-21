package qudian.alipay.api.utils;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by suzunshou on 16/9/16.
 */
public class AlipayUtils {
    /**
     * 获取支付宝异步回调POST过来的参数
     *
     * @param requestParams request.getParameterMap()
     * @return
     */
    public static Map<String, String> getParamsFromGetRequestParams(Map<String, String[]> requestParams) {
        Map<String, String> params = new HashMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }


    /**
     * 检查授权是否过期,
     *
     * @param userAuth
     * @return true:有效、false:无效
     */
    public static boolean checkIsAuthValid(AlipaySystemOauthTokenResponse userAuth) {
        return !("40002".equals(userAuth.getCode()) && "isv.code-invalid".equals(userAuth.getSubCode()));
    }

    /**
     * 是否支付宝实名认证
     *
     * @param userInfo
     * @return
     */
    public static boolean IsCertified(AlipayUserUserinfoShareResponse userInfo) {
        return userInfo.getUserStatus().equals("T") || userInfo.getIsCertified().equals("T");
    }

    /**
     * 是否学籍认证
     *
     * @param userInfo
     * @return
     */
    public static boolean IsStudentCertified(AlipayUserUserinfoShareResponse userInfo) {
        return userInfo.getIsStudentCertified().equals("T");
    }
}
