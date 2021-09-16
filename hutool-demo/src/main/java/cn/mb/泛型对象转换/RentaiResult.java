package cn.mb.泛型对象转换;

import lombok.Data;
import lombok.NonNull;

/**
 * <p>
 * 人太接口返回值
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/6/4
 */
@Data
public class RentaiResult<T> {

    /**
     * 错误码，0 请求失败，返回错误信息、1 请求成功、10001 用户未登录、10002 门店没权限操作
     */
    @NonNull
    private Integer code;

    /**
     * 提示信息
     */
    @NonNull
    private String msg;

    /**
     * 返回结果
     */
    private T data;

}
