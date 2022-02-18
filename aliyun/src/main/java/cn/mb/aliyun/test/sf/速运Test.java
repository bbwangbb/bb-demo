package cn.mb.aliyun.test.sf;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import cn.mb.aliyun.test.sf.code.CountryCode;
import cn.mb.aliyun.test.sf.code.SFFastAPIServiceCode;
import cn.mb.aliyun.test.sf.param.speed.CreateOrderParam;
import cn.mb.aliyun.test.sf.param.speed.OrderFilterParam;
import cn.mb.aliyun.test.sf.param.speed.QueryDeliverParam;
import cn.mb.aliyun.test.sf.param.speed.QuerySfwaybillParam;
import cn.mb.aliyun.test.sf.response.OrderFilterResponse;
import cn.mb.aliyun.test.sf.response.QueryDeliverResponse;
import cn.mb.aliyun.test.sf.util.SFUtil;

import java.util.List;

/**
 * <p>
 * 测试demo
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/16
 */
public class 速运Test {

    /**
     * 校验码
     */
//    private static final String CHECK_WORD = "dr6teInKsn27tkTlOs8K0RoYevW0ThbF";//  bb
    private static final String CHECK_WORD = "IJTvm7ehZYup9utqqtQ894gSOZfmJ78c";//  hy
    /**
     * 顾客编码
     */
//    private static final String PARTNER_ID = "GBOOs5i2h";// bb
    private static final String PARTNER_ID = "HYXXKEKc8HU9";// hy

    public static void main(String[] args) throws Exception {
//        下单();
//        清单运费查询();
        时效及价格查询();
//        订单筛选();
    }

    private static void 下单() throws Exception {
        CreateOrderParam.ContactInfo 寄件方 = new CreateOrderParam.ContactInfo("海南省海口市美兰区文明东路亚东公寓", "郭彬", 2, "CN", "570203", "18331181408", null);
        CreateOrderParam.ContactInfo 收件方 = new CreateOrderParam.ContactInfo("浙江省杭州市拱墅区富越香郡", "郭海", 1, "CN", "659475", "18331181408", "顺丰速运");
        CreateOrderParam.CargoDetail 西瓜 = new CreateOrderParam.CargoDetail(1.0, "个", 5.0, 100.0, "CNY", "西瓜", "CHN");
        CreateOrderParam CreateOrderParam = new CreateOrderParam(CollUtil.newArrayList(西瓜), CollUtil.newArrayList(寄件方, 收件方), "zh_CN", "DD202111161353");
        SFUtil.request(SFUtil.SAND_BOX_URL, CHECK_WORD, PARTNER_ID, SFFastAPIServiceCode.CREATE_ORDER, JSONUtil.toJsonStr(CreateOrderParam));
    }

    private static void 清单运费查询() throws Exception {
        QuerySfwaybillParam QuerySfwaybillParam = new QuerySfwaybillParam("2", "SF7444437018298");
        SFUtil.request(SFUtil.SAND_BOX_URL, CHECK_WORD, PARTNER_ID, SFFastAPIServiceCode.QUERY_SFWAYBILL, JSONUtil.toJsonStr(QuerySfwaybillParam));
    }

    private static void 时效及价格查询() throws Exception {
        //  时效及价格查询
        QueryDeliverParam.Address srcAddress = new QueryDeliverParam.Address("海南省", "海口市", null, null);
//        QueryDeliverParam.Address srcAddress = new QueryDeliverParam.Address("海南省", "海口市", "美兰区", "海南省海口市美兰区文明东路亚东公寓");
//        QueryDeliverParam.Address destAddress = new QueryDeliverParam.Address("浙江省", "杭州市", "拱墅区", "浙江省杭州市拱墅区富越香郡");
        QueryDeliverParam.Address destAddress = new QueryDeliverParam.Address("浙江省", "杭州市", null, null);
//        QueryDeliverParam queryDeliverParam = new QueryDeliverParam(QueryDeliverParam.BusinessTypeEnum.STANDARD_FAST, QueryDeliverParam.SearchPriceEnum.WITH_PRICE, null, srcAddress, destAddress);
        QueryDeliverParam queryDeliverParam = new QueryDeliverParam(QueryDeliverParam.BusinessTypeEnum.STANDARD_FAST, QueryDeliverParam.SearchPriceEnum.WITHOUT_PRICE, null, srcAddress, destAddress);
        String msgDataStr = SFUtil.request(SFUtil.PROD_URL, CHECK_WORD, PARTNER_ID, SFFastAPIServiceCode.QUERY_DELIVERTM, JSONUtil.toJsonStr(queryDeliverParam));
        QueryDeliverResponse queryDeliverResponse = JSONUtil.toBean(msgDataStr, QueryDeliverResponse.class);
        System.out.println(queryDeliverResponse);
    }

    private static void 订单筛选() throws Exception {
        OrderFilterParam.ContactInfo 订单筛选寄件方 = new OrderFilterParam.ContactInfo(CountryCode.CHINA, "海南省海口市美兰区文明东路亚东公寓", "海南省", "海口市", "美兰区", OrderFilterParam.ContactInfo.ContactTypeEnum.SENDER);
        OrderFilterParam.ContactInfo 订单筛选收件方 = new OrderFilterParam.ContactInfo(CountryCode.CHINA, "浙江省杭州市拱墅区富越香郡", "浙江省", "杭州市", "拱墅区", OrderFilterParam.ContactInfo.ContactTypeEnum.RECEIVER);
        OrderFilterParam orderFilterParam = new OrderFilterParam(OrderFilterParam.FilterTypeEnum.AUTO, CollUtil.newArrayList(订单筛选寄件方, 订单筛选收件方));
        List<OrderFilterParam> orderFilterParamList = CollUtil.newArrayList(orderFilterParam);
        String msgDataStr = SFUtil.request(SFUtil.SAND_BOX_URL, CHECK_WORD, PARTNER_ID, SFFastAPIServiceCode.FILTER_ORDER, JSONUtil.toJsonStr(orderFilterParamList));
        List<OrderFilterResponse> orderFilterResponseList = JSONUtil.toList(JSONUtil.parseArray(msgDataStr), OrderFilterResponse.class);
        System.out.println(orderFilterResponseList);
    }
}
