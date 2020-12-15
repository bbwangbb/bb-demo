package cn.mb.repeatrequestsolution.exception;

/**
 * <p>
 *  业务异常
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
