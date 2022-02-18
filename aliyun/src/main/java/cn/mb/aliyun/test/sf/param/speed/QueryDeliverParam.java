package cn.mb.aliyun.test.sf.param.speed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 时效及价格查询参数
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/16
 */
@Data
@NoArgsConstructor
public class QueryDeliverParam {

    public QueryDeliverParam(BusinessTypeEnum businessType, SearchPriceEnum searchPrice, Double weight, Address srcAddress, Address destAddress) {
        this.businessType = businessType.getValue();
        this.searchPrice = searchPrice.getValue();
        this.weight = weight;
        this.srcAddress = srcAddress;
        this.destAddress = destAddress;
    }

    /**
     * 快件产品：可以为空，为空时查询顺丰支持的所有业务类型。不为空时以数字代码业务类型，
     * 例如：1(特快)：表示“”2(标快)
     */
    @Getter
    public enum BusinessTypeEnum {
        SUPER_FAST("1"),
        STANDARD_FAST("2"),
        STANDARD_FAST1("238"),
        ;

        BusinessTypeEnum(String value) {
            this.value = value;
        }

        private String value;
    }

    /**
     * 1：表示查询含价格的接口0：表示查询不含价格的接口
     */
    @Getter
    public enum SearchPriceEnum {
        WITH_PRICE("1"),
        WITHOUT_PRICE("0"),
        ;

        SearchPriceEnum(String value) {
            this.value = value;
        }

        private String value;
    }

    /**
     * 快件产品：可以为空，为空时查询顺丰支持的所有业务类型。不为空时以数字代码业务类型，
     * 例如：1(特快)：表示“”2(标快)：表示“顺丰特惠”5：表示“顺丰次晨”6：表示“即日件
     */
    private String businessType;

    /**
     * 寄件时间，格式为YYYY-MM-DD HH24:MM:SS，示例2013-12-27 17:54:20。
     */
    private String consignedTime;

    /**
     * 1：表示查询含价格的接口0：表示查询不含价格的接口
     */
    private String searchPrice;

    /**
     * 货物总重量，包含子母件，单位千克，精确到小数点后2位，如果提供此值，必须>0。
     */
    private Double weight;

    /**
     * 原寄地信息
     */
    private Address srcAddress;

    /**
     * 目的地信息
     */
    private Address destAddress;

    /**
     * 地址
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {

        /**
         * 目的地所在省份，字段填写要求：必须是标准的省名称称谓
         * 如：广东省; 如果是直辖市，请直接传北京、上海等，如果字段code为空时为必填。
         */
        private String province;
        /**
         * 目的地所在城市，必须是标准的城市称谓 如：深圳市，如果字段code为空时为必填。
         */
        private String city;
        /**
         * 目的地所在县/区，必须是标准的县/区称谓，示例：“福田区”。
         */
        private String district;
        /**
         * 目的地详细地址，此详细地址需包含省市信息，以提高地址识别的成功率，
         * 示例：“广东省深圳市福田区新洲十一街万基商务大厦10楼”。
         */
        private String address;

    }

}
