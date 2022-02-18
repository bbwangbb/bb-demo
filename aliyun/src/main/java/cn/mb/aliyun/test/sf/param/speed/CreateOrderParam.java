package cn.mb.aliyun.test.sf.param.speed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 下单参数
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderParam {

    private List<CargoDetail> cargoDetails;
    private List<ContactInfo> contactInfoList;
    private String language;
    private String orderId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CargoDetail {
        /**
         * 货物数量 跨境件报关需要填写
         */
        private Double count;
        /**
         * 货物单位，如：个、台、本，跨境件报关需要填写
         */
        private String unit;
        /**
         * 订单货物单位重量，包含子母件，单位千克，精确到小数点后3位跨境件报关需要填写
         */
        private Double weight;
        /**
         * 货物单价，精确到小数点后3位，跨境件报关需要填写
         */
        private Double amount;
        /**
         * 货物单价的币别：参照附录币别代码附件
         */
        private String currency;
        /**
         * 货物名称，如果需要生成电子运单，则为必填
         */
        private String name;
        /**
         * 原产地国别，跨境件报关需要填写
         */
        private String sourceArea;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContactInfo {
        /**
         * 详细地址，若province/city字段的值不传，此字段必须包
         * 含省市信息，避免影响原寄地代码识别，如：广东省深圳市
         * 福田区新洲十一街万基商务大厦10楼；若需要生成电子运
         * 单，则为必填
         */
        private String address;
        /**
         * 联系人
         */
        private String contact;
        /**
         * 地址类型：1，寄件方信息 2，到件方信息
         */
        private Integer contactType;
        /**
         * 国家或地区 2位代码参照附录国家代码附件
         */
        private String country;
        /**
         * 邮编，跨境件必填（中国内地，港澳台互寄除外）
         */
        private String postCode;
        /**
         * 联系电话
         */
        private String tel;
        /**
         * 公司名称
         */
        private String company;
    }

}
