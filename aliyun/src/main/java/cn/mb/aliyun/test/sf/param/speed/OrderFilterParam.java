package cn.mb.aliyun.test.sf.param.speed;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/16
 */
@Data
@NoArgsConstructor
public class OrderFilterParam {

    /**
     * 筛单类别:
     * 1:自动筛单(系统根据地址库进行判断,并返回结果,系统无法检索到可派送的将返回不可派送)
     * 2:可人工筛单(系统首先根据地址库判断,如果无法自动判断是否收派,系统将生成需要人工判断的任务,后续由人工处理,处理结束后,顺丰可主动推送给客户系统)
     */
    @Getter
    public enum FilterTypeEnum {
        AUTO(1),
        MANUAL(2),
        ;

        FilterTypeEnum(Integer value) {
            this.value = value;
        }

        private Integer value;
    }

    public OrderFilterParam(FilterTypeEnum filterType, List<ContactInfo> contactInfos) {
        this.filterType = filterType.getValue();
        this.contactInfos = contactInfos;
    }

    /**
     * 筛单类别
     */
    private Integer filterType;
    /**
     * 地址信息
     */
    private List<ContactInfo> contactInfos;

    @Data
    @NoArgsConstructor
    public static class ContactInfo {

        public ContactInfo(String country, String address, String province, String city, String county, ContactTypeEnum contactType) {
            this.country = country;
            this.address = address;
            this.province = province;
            this.city = city;
            this.county = county;
            this.contactType = contactType.getValue();
        }

        /**
         * 国家或地区代码 2位
         */
        private String country;
        /**
         * 详细地址
         */
        private String address;
        /**
         * 省级行政区名称
         */
        private String province;
        /**
         * 地级行政区名称
         */
        private String city;
        /**
         * 县/区级行政区名称
         */
        private String county;
        /**
         * 地址类型：1，寄件方信息 2，到件方信息
         */
        private Integer contactType;

        /**
         * 地址类型：1，寄件方信息 2，到件方信息
         */
        @Getter
        public enum ContactTypeEnum {
            SENDER(1),
            RECEIVER(2),
            ;

            ContactTypeEnum(Integer value) {
                this.value = value;
            }

            private Integer value;
        }
    }

}
