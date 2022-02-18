package cn.mb.aliyun.test.sf.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import cn.mb.aliyun.test.sf.response.SFResponse;
import sun.misc.BASE64Encoder;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * <p>
 * 顺丰工具
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/16
 */
public class SFUtil {

    /**
     * 沙箱环境url
     */
    public final static String SAND_BOX_URL = "https://sfapi-sbox.sf-express.com/std/service";
    /**
     * 生产环境url
     */
    public final static String PROD_URL = "https://sfapi.sf-express.com/std/service";
    /**
     * content-type取值
     */
    private final static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded;charset=UTF-8";
    /**
     * 顾客编码变量名
     */
    private final static String PARTNER_ID_KEY = "partnerID";
    /**
     * 校验码变量名
     */
    private final static String REQUEST_ID_KEY = "requestID";
    /**
     * 接口服务代码变量名
     */
    private final static String SERVICE_CODE_KEY = "serviceCode";
    /**
     * 请求时间戳变量名
     */
    private final static String TIMESTAMP_KEY = "timestamp";
    /**
     * 数字签名变量名
     */
    private final static String MSG_DIGEST_KEY = "msgDigest";
    /**
     * 业务数据报文变量名
     */
    private final static String MSG_DATA_KEY = "msgData";

    /**
     * <p>
     * 发送请求
     * </p>
     * @param url
     * @param checkWord     校验码
     * @param partnerID     顾客编码
     * @param serviceCode   接口服务代码
     * @param msgData       参数(JSON格式)
     * @return java.lang.String 成功请求时返回响应数据，失败则会报错
     * @author guohaibin
     * @date 2021-11-16 15:35:26
     */
    public static String request(String url, String checkWord, String partnerID, String serviceCode, String msgData) throws Exception {
        String timestamp = System.currentTimeMillis() + "";
        String msgDigest = sign(checkWord, msgData, timestamp);
        HttpResponse httpResponse = HttpRequest.post(url + "?" +
                PARTNER_ID_KEY + "=" + partnerID + "&" +
                REQUEST_ID_KEY + "=" + UUID.randomUUID() + "&" +
                SERVICE_CODE_KEY + "=" + serviceCode + "&" +
                TIMESTAMP_KEY + "=" + timestamp + "&" +
                MSG_DIGEST_KEY + "=" + msgDigest + "&" +
                MSG_DATA_KEY + "=" + msgData
        )
        .header(Header.CONTENT_TYPE.getValue(), CONTENT_TYPE_VALUE)
        .execute();
        String responseBody = httpResponse.body();
        SFResponse SFResponse = JSONUtil.toBean(responseBody, SFResponse.class);
        return getMsgData(SFResponse);
    }

    /**
     * <p>
     * 从响应体中获取返回数据
     * </p>
     * @param SFResponse  响应体
     * @return java.lang.String 返回数据
     * @author guohaibin
     * @date 2021-11-16 15:34:57
     */
    private static String getMsgData(SFResponse SFResponse) {
        //  TODO 改成业务报错
        if (ObjectUtil.isNull(SFResponse)) {
            throw new IllegalArgumentException("响应结果为null");
        }
        if (!StrUtil.equals("A1000", SFResponse.getApiResultCode())) {
            throw new IllegalArgumentException("顺丰接口调用异常，" + SFResponse.getApiErrorMsg());
        }
        String apiResultDataStr = SFResponse.getApiResultData();
        System.out.println(apiResultDataStr);
        SFResponse.ApiResultData apiResultData = JSONUtil.toBean(apiResultDataStr, SFResponse.ApiResultData.class);
        if (!apiResultData.getSuccess()) {
            throw new IllegalArgumentException("顺丰接口调用成功，返回错误，" + apiResultData.getErrorMsg());
        }
        return apiResultData.getMsgData();
    }

    /**
     * <p>
     * 签名
     * </p>
     * @param checkWord 校验码
     * @param msgData   参数
     * @param timestamp 时间戳
     * @return java.lang.String 签名字符串
     * @author guohaibin
     * @date 2021-11-16 15:33:36
     */
    private static String sign(String checkWord, String msgData, String timestamp) throws Exception {
        //  将业务报文+时间戳+校验码组合成需加密的字符串(注意顺序)
        String toVerifyText = msgData+timestamp+checkWord;
        //  因业务报文中可能包含加号、空格等特殊字符，需要urlEnCode处理
        toVerifyText = URLEncoder.encode(toVerifyText,"UTF-8");
        //  进行Md5加密
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(toVerifyText.getBytes("UTF-8"));
        byte[] md = md5.digest();
        //  通过BASE64生成数字签名
        return new BASE64Encoder().encode(md);
    }

}
