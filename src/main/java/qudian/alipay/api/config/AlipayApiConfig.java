package qudian.alipay.api.config;

import com.alipay.api.AlipayClient;

/**
 * Created by suzunshou on 16/9/16.
 * 授权访问地址:https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?
 * app_id=APPID&scope=SCOPE&redirect_uri=ENCODED_URL
 * appid : 应用id
 * scope : 权限
 * redirect_uri : 授权回调地址
 */
public class AlipayApiConfig {

    //==============获取会员信息配置==============//

    //获取用户信息使用的AlipayClient
    private AlipayClient alipayClient_info;

    /**
     * 授权类型:
     * 值为authorization_code时，代表用code换取；
     * 值为refresh_token时，代表用refresh_token换取
     */
    private String grant_type;

    public AlipayApiConfig(AlipayClient alipayClient_info, String grant_type) {
        this.alipayClient_info = alipayClient_info;
        this.grant_type = grant_type;
    }


    //==============调用收银台配置==============//

    //调用收银台使用的AlipayClient
    private AlipayClient alipayClient_pay;

    //实际收款账号，一般填写商户PID即可
    private String seller_id;

    //销售产品码，商家和支付宝签约的产品码:QUICK_WAP_PAY
    private String product_code;

    //同步回跳地址
    private String returnUrl;

    //异步通知地址
    private String notifyUrl;

    //收银台支付宝公钥
    private String publicKey;

    //收银台字符集
    private String charset;

    public AlipayApiConfig(AlipayClient alipayClient_pay, String seller_id, String product_code, String returnUrl, String notifyUrl, String publicKey, String charset) {
        this.alipayClient_pay = alipayClient_pay;
        this.seller_id = seller_id;
        this.product_code = product_code;
        this.returnUrl = returnUrl;
        this.notifyUrl = notifyUrl;
        this.publicKey = publicKey;
        this.charset = charset;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public AlipayClient getAlipayClient_info() {
        return alipayClient_info;
    }

    public void setAlipayClient_info(AlipayClient alipayClient_info) {
        this.alipayClient_info = alipayClient_info;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public AlipayClient getAlipayClient_pay() {
        return alipayClient_pay;
    }

    public void setAlipayClient_pay(AlipayClient alipayClient_pay) {
        this.alipayClient_pay = alipayClient_pay;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
