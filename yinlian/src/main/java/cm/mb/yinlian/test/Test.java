package cm.mb.yinlian.test;

import cm.mb.yinlian.sdk.AcpService;
import cm.mb.yinlian.sdk.SDKConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/10/20
 */
public class Test {
    public static void main(String[] args) {
        String merId = "777290058193883";
        String orderId = "20210927000001";
        String txnTime = "20210927144311";
        String txnAmt = "1";
        String encoding = "UTF-8";
        Map<String, String> contentData = new HashMap<String, String>();

        /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
        contentData.put("version", "5.1.0");                  //版本号
        contentData.put("encoding", encoding);           //字符集编码 可以使用UTF-8,GBK两种方式
        contentData.put("signMethod", "01"); //签名方法
        contentData.put("txnType", "01");                              //交易类型 01-消费
        contentData.put("txnSubType", "01");                           //交易子类型 01-消费
        contentData.put("bizType", "000301");                          //业务类型 认证支付2.0
        contentData.put("channelType", "07");                          //渠道类型07-PC

        /***商户接入参数***/
        contentData.put("merId", merId);                   			   //商户号码（本商户号码仅做为测试调通交易使用，该商户号配置了需要对敏感信息加密）测试时请改成自己申请的商户号，【自己注册的测试777开头的商户号不支持代收产品】
        contentData.put("accessType", "0");                            //接入类型，商户接入固定填0，不需修改
        contentData.put("orderId", orderId);             			   //商户订单号，如上送短信验证码，请填写获取验证码时一样的orderId，此处默认取demo演示页面传递的参数
        contentData.put("txnTime", txnTime);         				   //订单发送时间，如上送短信验证码，请填写获取验证码时一样的txnTime，此处默认取demo演示页面传递的参数
        contentData.put("currencyCode", "156");						   //交易币种（境内商户一般是156 人民币）
        contentData.put("txnAmt", txnAmt);							   //交易金额，单位分，如上送短信验证码，请填写获取验证码时一样的txnAmt，此处默认取demo演示页面传递的参数
        contentData.put("accType", "01");                              //账号类型
        Map<String,String> customerInfoMap = new HashMap<String,String>();
        customerInfoMap.put("smsCode", "111111");			    	//短信验证码,测试环境不会真实收到短信，固定填111111

        ////////////如果商户号开通了【商户对敏感信息加密】的权限那么需要对 accNo，pin和phoneNo，cvn2，expired加密（如果这些上送的话），对敏感信息加密使用：
        String accNo = AcpService.encryptData("6212149999960000008", encoding);  //这里测试的时候使用的是测试卡号，正式环境请使用真实卡号
        contentData.put("accNo", accNo);
        contentData.put("encryptCertId",AcpService.getEncryptCertId());       //加密证书的certId，配置在acp_sdk.properties文件 acpsdk.encryptCert.path属性下
        String customerInfoStr = AcpService.getCustomerInfoWithEncrypt(customerInfoMap,"6216261000000000018", encoding);
        contentData.put("customerInfo", customerInfoStr);

        //  回调url
        contentData.put("backUrl", "");

        Map<String, String> reqData = AcpService.sign(contentData, encoding);			//报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String requestBackUrl = SDKConfig.getConfig().getBackRequestUrl();   			//交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
        Map<String, String> rspData = AcpService.post(reqData,requestBackUrl,encoding); //发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过

        rspData.forEach((s1, s2) -> System.out.println(s1 + "   -   " + s2));
    }
}
