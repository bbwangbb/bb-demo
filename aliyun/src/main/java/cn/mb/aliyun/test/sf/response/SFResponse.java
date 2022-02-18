package cn.mb.aliyun.test.sf.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 顺丰接口响应体
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SFResponse {

    /**
     * API平台结果代码
     */
    private String apiResultCode;
    /**
     * API平台异常信息
     */
    private String apiErrorMsg;
    /**
     * API响应唯一号UUID
     */
    private String apiResponseID;
    /**
     * 业务处理详细结果
     */
    private String apiResultData;

    /**
     * 业务处理详细结果
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiResultData {
        /**
         * 返回状态 true/false
         */
        private Boolean success;
        /**
         * 错误代码
         */
        private Integer errorCode;
        /**
         * 错误详细信息
         */
        private String errorMsg;
        /**
         * 如果success为true，这个字段代表筛单结果；Success为false时，此字段为空
         */
        private String msgData;

    }

}
