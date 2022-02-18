package cn.mb.test;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.FileOutputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/5/27
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Object o = new Object();

    }

    private static void 银联支付() throws Exception {

    }

    private void 三方平台生成小程序无限制二维码() throws Exception {
        JSONObject params = new JSONObject();
        params.set("scene", "id=1");
        HttpResponse response = HttpRequest.post("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=49_Ev93gI21-U5BvfSq6dS-cK7OM3AiFeqbl4O9v2iNjd63NL_BjrN1luwiv5WOaKZim9zndGp55ncaz39LLYYFDLeLvBUDVfaZxajWe9kxDYSa5U5S7x3GSkI0XREkGHyx7suHzX84RCG4JAB_URMfAHDDAV")
                .body(params.toString()).execute();
        String body = response.body();
        System.out.println(body);
        System.out.println(JSONUtil.isJson(body));
        byte[] bytes = response.bodyBytes();
        IoUtil.write(new FileOutputStream("D:/aa.jpg"), true, body.getBytes());
    }
}
