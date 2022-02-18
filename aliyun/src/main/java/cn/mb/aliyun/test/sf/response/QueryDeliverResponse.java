package cn.mb.aliyun.test.sf.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 时效及价格查询响应数据
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryDeliverResponse {

    private List<DeliverTmDto> deliverTmDto;

    /**
     * 时效及价格查询响应数据具体对象
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class DeliverTmDto {
        /**
         * 快件产品
         */
        private String businessType;
        /**
         * 快件产品描述
         */
        private String businessTypeDesc;
        /**
         * 承诺时间
         */
        private String deliverTime;
        /**
         * 价格
         */
        private Double fee;
        /**
         * 是否查询价格（1,返回价格；0，不返回价格）
         */
        private String searchPrice;
        /**
         * 截单时间
         */
        private String closeTime;
    }

}
