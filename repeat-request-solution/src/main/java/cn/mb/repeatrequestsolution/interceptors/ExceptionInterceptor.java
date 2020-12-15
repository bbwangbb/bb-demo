package cn.mb.repeatrequestsolution.interceptors;

import cn.mb.repeatrequestsolution.exception.BusinessException;
import cn.mb.repeatrequestsolution.response.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *  异常拦截器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse handleBusinessException(BusinessException ex) {
        return new BaseResponse(444, ex.getMessage());
    }

}
