package cn.mb.wxpay.test;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/8
 */
public class Test {
    public static void main(String[] args) throws Exception {

//        WxPayConfig wxPayConfig = new WxPayConfig();
//        wxPayConfig.setAppId("wx17df887658185a51");
//        wxPayConfig.setMchId("1603847436");
//        wxPayConfig.setMchKey("2613fa5946944d4281855b5e63c8656d");
//        WxPayOrderQueryRequest wxPayOrderQueryRequest = new WxPayOrderQueryRequest();
//        wxPayOrderQueryRequest.setOutTradeNo("DD20210115000002");
//        WxPayService wxPayService = new WxPayServiceImpl();
//        wxPayService.setConfig(wxPayConfig);
//        WxPayOrderQueryResult wxPayOrderQueryResult = wxPayService.queryOrder(wxPayOrderQueryRequest);
//        System.out.println(wxPayOrderQueryResult);

        long start = System.currentTimeMillis();
        LocalDate startDate = LocalDate.parse("2020-11-01");
        LocalDate endDate = LocalDate.parse("2021-01-23");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend?access_token=41_onoMyQsVTPR5auU3gglIj8xfAEhGXmbKH84-WpMHfJXNZyqhNf1_zEEV_EmbhFy7S_99uQinV9YPTSb0VbySkwOgnBgEoayi88yIZb4g-pFCDM-0JF8bpc7YypSk5iKQzn7Qdg9JQRv3bMXjUPWeAHDHCJ";
        long days = startDate.until(endDate, ChronoUnit.DAYS);
        JSONObject params = new JSONObject();
        List<String> results = new ArrayList<>();
        String date;
        for (int i = 0; i <= days; i++) {
            date = startDate.plusDays(i + 1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            System.out.println("current date " + date);
            params.set("begin_date", date);
            params.set("end_date", date);
            results.add(HttpRequest.post(url).body(params.toString()).execute().body());
        }
        System.out.println(results);
        long end = System.currentTimeMillis();
        System.out.println("spend " + (end - start));
    }


    public void 测试() {
    }
}
