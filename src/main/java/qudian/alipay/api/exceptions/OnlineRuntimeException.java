package qudian.alipay.api.exceptions;

/**
 * Created by suzunshou on 16/9/16.
 */
public class OnlineRuntimeException extends RuntimeException {
    private String code = null;
    private String message = null;

    public OnlineRuntimeException(String code) {
        this.code = code;
    }

    public OnlineRuntimeException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
