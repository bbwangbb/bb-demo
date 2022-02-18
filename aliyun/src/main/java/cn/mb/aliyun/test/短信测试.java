package cn.mb.aliyun.test;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.AddSmsSignRequest;
import com.aliyun.dysmsapi20170525.models.AddSmsSignResponse;
import com.aliyun.dysmsapi20170525.models.QuerySmsSignRequest;
import com.aliyun.dysmsapi20170525.models.QuerySmsSignResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/2
 */
public class 短信测试 {

    private final static String regionId = "cn-hangzhou";
    private final static String accessKeyId = "LTAI4GD67ma3DKAME8ejEraw";
    private final static String accessSecret = "PGqMnAF65aDTWNLQyRNkl0DXh5ZsmR";

    public static void main(String[] args) throws Exception {
//        年检提醒();
//        sendOne();
//        sendBatch();
//        query();
        申请标签();
//        申请标签新版();
//        查询短信签名申请状态新版();
//        删除标签();
    }

    private static void 申请标签新版() throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessSecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);
        InputStream inputStream = URLUtil.url("https://jbb-common-dev.oss-cn-hangzhou.aliyuncs.com/UfGL8Wpv97W5YPkLuSZ3.png").openConnection().getInputStream();
        String s = cn.hutool.core.codec.Base64.encode(inputStream);
        AddSmsSignRequest.AddSmsSignRequestSignFileList signFileList0 = new AddSmsSignRequest.AddSmsSignRequestSignFileList()
                .setFileContents(s)
                .setFileSuffix("png");
        AddSmsSignRequest addSmsSignRequest = new AddSmsSignRequest()
                .setSignName("绍兴英2")
                .setSignSource(0)
                .setRemark("消息通知使用")
                .setSignFileList(java.util.Arrays.asList(
                        signFileList0
                ));
        // 复制代码运行请自行打印 API 的返回值
        AddSmsSignResponse addSmsSignResponse = client.addSmsSign(addSmsSignRequest);
        System.out.println(addSmsSignResponse.getBody().code);
        System.out.println(addSmsSignResponse.getBody().message);
        System.out.println(addSmsSignResponse.getBody().requestId);
        System.out.println(addSmsSignResponse.getBody().signName);
        Map<String, Object> stringObjectMap = addSmsSignResponse.body.toMap();
        System.out.println(stringObjectMap);
    }

    private static void 查询短信签名申请状态新版() throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessSecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);
        QuerySmsSignRequest querySmsSignRequest = new QuerySmsSignRequest()
                .setSignName("绍兴英吉8");
        // 复制代码运行请自行打印 API 的返回值
        QuerySmsSignResponse querySmsSignResponse = client.querySmsSign(querySmsSignRequest);
        System.out.println(querySmsSignResponse.getBody().toMap());
    }



    private static void query() {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        request.putQueryParameter("PhoneNumber", "18331181408");
        request.putQueryParameter("BizId", "430620735836992565^0");
        request.putQueryParameter("SendDate", "20211102");
        request.putQueryParameter("PageSize", "11");
        request.putQueryParameter("CurrentPage", "1");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private static void sendBatch() {

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendBatchSms");
        JSONArray phoneNumberList =  new JSONArray();
        phoneNumberList.add("18331181408");
        phoneNumberList.add("1633118140");
        request.putQueryParameter("PhoneNumberJson", phoneNumberList.toString());
        JSONArray signNameList =  new JSONArray();
        signNameList.add("杭州辉曜");
        signNameList.add("杭州辉曜");
        request.putQueryParameter("SignNameJson", signNameList.toString());
        request.putQueryParameter("TemplateCode", "SMS_210061155");
        JSONArray templateParamList =  new JSONArray();
        templateParamList.add(new JSONObject().set("code", "1234"));
        templateParamList.add(new JSONObject().set("code", "5678"));
        request.putQueryParameter("TemplateParamJson", templateParamList.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private static void sendOne() {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", "18331181408");
        request.putQueryParameter("SignName", "杭州辉曜科技");
        request.putQueryParameter("TemplateCode", "SMS_227263298");
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("username", "bb");
        jsonObject.set("discount_coupon", "洗车券，https://wxaurl.cn/BpMjlJoyNLa ");
        System.out.println(jsonObject.toString());
//        request.putQueryParameter("TemplateParam", "{\"code\":\"https://wxaurl.cn/fJ6qClMBj2e\"}");
        request.putQueryParameter("TemplateParam", jsonObject.toString());
        request.putQueryParameter("OutId", "店铺id19");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private static void 年检提醒() {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", "18331181408");
        request.putQueryParameter("SignName", "杭州辉曜科技");
        request.putQueryParameter("TemplateCode", "SMS_227252661");
        request.putQueryParameter("TemplateParam", "{\"name\":\"https://wxaurl.cn/fJ6qClMBj2e\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private static void 申请标签() throws Exception {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("AddSmsSign");// 新增
//        request.setSysAction("ModifySmsSign");//    修改(签名名称不能变)
        byte[] bytes = IoUtil.readBytes(new FileInputStream("C:\\Users\\HUAWEI\\Desktop\\img.jpg"));
//        String s = Base64.encodeBase64String(bytes);
//        String s = Base64.encode(URLUtil.url("https://jbb-common-dev.oss-cn-hangzhou.aliyuncs.com/UfGL8Wpv97W5YPkLuSZ3.png").openConnection().getInputStream());
        InputStream inputStream = URLUtil.url("https://jbb-common-dev.oss-cn-hangzhou.aliyuncs.com/UfGL8Wpv97W5YPkLuSZ3.png").openConnection().getInputStream();
//        System.out.println(inputStream.available());
//        byte[] bytes = IoUtil.readBytes(inputStream);
//        IoUtil.write(new FileOutputStream("C:\\Users\\HUAWEI\\Desktop\\img111.jpg"), true, bytes);
//        byte[] bytes = IoUtil.readBytes();
        String s = Base64.encodeBase64String(bytes);
//        String s = cn.hutool.core.codec.Base64.encode(inputStream);
//        System.out.println(s.length());
//        System.out.println(encode.length());

        request.putQueryParameter("SignFileList.1.FileContents", s);
        request.putQueryParameter("SignFileList.1.FileSuffix", "png");
        request.putQueryParameter("SignSource", "0");
        request.putQueryParameter("Remark", "消息通知使用");
        request.putQueryParameter("SignName", "绍兴英1");
        FormatType httpContentType = request.getHttpContentType();
        System.out.println(httpContentType);

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            System.out.println(response.getHttpResponse().getHttpContentType());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private static void 删除标签() {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("DeleteSmsSign");
        request.putQueryParameter("SignName", "绍兴英吉11111");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
