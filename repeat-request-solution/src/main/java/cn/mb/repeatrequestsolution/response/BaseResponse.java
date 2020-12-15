package cn.mb.repeatrequestsolution.response;

/**
 * <p>
 *  相应结果
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
public class BaseResponse {

    private int code;// 响应码
    private String message;//   提示信息
    private Object data;//  数据

    public BaseResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
