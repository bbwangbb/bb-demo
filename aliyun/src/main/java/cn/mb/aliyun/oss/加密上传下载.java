package cn.mb.aliyun.oss;

import cn.hutool.core.io.IoUtil;
import com.aliyun.oss.OSSEncryptionClient;
import com.aliyun.oss.OSSEncryptionClientBuilder;
import com.aliyun.oss.crypto.SimpleRSAEncryptionMaterials;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/5
 */
public class 加密上传下载 {

    public static void main(String[] args) throws Exception {
// Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "xxx";
        String accessKeySecret = "PGqMnAF65aDTWNLQyRNkl0DXh5ZsmR";
        String bucketName = "jbb-common-dev";
        String objectName = "img.jpg";
        String content = "Hello OSS!";

// 填写您的RSA私钥字符串。
        final String PRIVATE_PKCS1_PEM = "-----BEGIN PRIVATE KEY-----\n" +
                "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALw0J0XSOKrukAy9\n" +
                "cCC0M/pSQ2jv8Veh3ED1VTdcoq40cIJHkr+YySdx1qa5ZRYt/U7vKZ7aW5wr5dJk\n" +
                "tJY0RolLDOMPweuWRfFU47PTZP2+7tHQlcrSdZ3Arok7bnEwNhLTPrpS7e4R/Gag\n" +
                "uM+vapxXRu4ealZMq+Rwcs2Ihbr1AgMBAAECgYBtwFQuqVsWgyxJ4acjyzKMhbB9\n" +
                "3370RZW149uf/0t3H2mE78wqIyUa9Y3hiDfk6UjQ4TN6SYgm2IqbWNLezPlcUuEn\n" +
                "+wPUoBoQM1E24/65XmAoStdh2TJ6iaWKY+OaxY9/QoD2JCBLl9sBWNeyXG8in9tI\n" +
                "Evagm/Wm3Mqw53WgOQJBAOJXqB2Y9c2+Pb4SJzib4AGOe4LG314XdOq6w4ZGddKw\n" +
                "5LkZ97lgIyIUbgi7VyeYfsXmEM4xBIUuWT1VWkliBEsCQQDU3S/uvr0yENDn0dey\n" +
                "+5zy3BDVrlxfX28Z7V5NJKsfuAdpfdTTW7eeLnUGRAz3buDLENUSm3DQ1vLcge2t\n" +
                "4zW/AkAPJA367OCEgtVegZheEQ9LC1z3wpXulWMc0KGDufLnxS2HtFT98ZBoFzXF\n" +
                "H8MUwv63/kvhfyXuQst1buA7SHNBAkEAm9UZxCRrCX36EdwzltpQ+hJVWLl+8mtB\n" +
                "QK/fe4Y+qMfm1TnDVsAsWmcUUPP8sh+//ali825MVvclcHzF7akKrQJBANF2PiZg\n" +
                "pIp8qcqvaC6j+7dyby62V9Fh7w7alXaGq+J0+4kBoltid6IyDEd/LspkRoLZic0G\n" +
                "bfji+iTP27ZebaM=\n" +
                "-----END PRIVATE KEY-----\n";
// 填写您的RSA公钥字符串。
        final String PUBLIC_X509_PEM = "-----BEGIN PUBLIC KEY-----\n" +
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8NCdF0jiq7pAMvXAgtDP6UkNo\n" +
                "7/FXodxA9VU3XKKuNHCCR5K/mMkncdamuWUWLf1O7yme2lucK+XSZLSWNEaJSwzj\n" +
                "D8HrlkXxVOOz02T9vu7R0JXK0nWdwK6JO25xMDYS0z66Uu3uEfxmoLjPr2qcV0bu\n" +
                "HmpWTKvkcHLNiIW69QIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";

// 创建一个RSA密钥对。
//        RSAPrivateKey privateKey = SimpleRSAEncryptionMaterials.getPrivateKeyFromPemPKCS1(PRIVATE_PKCS1_PEM);
        RSAPrivateKey privateKey = SimpleRSAEncryptionMaterials.getPrivateKeyFromPemPKCS8(PRIVATE_PKCS1_PEM);
        RSAPublicKey publicKey = SimpleRSAEncryptionMaterials.getPublicKeyFromPemX509(PUBLIC_X509_PEM);
        KeyPair keyPair = new KeyPair(publicKey, privateKey);

// 创建主密钥RSA的描述信息，创建后不允许修改。主密钥描述信息和主密钥一一对应。
// 如果所有的Object都使用相同的主密钥，主密钥描述信息可以为空，但后续不支持更换主密钥。
// 如果主密钥描述信息为空，解密时无法判断文件使用的是哪个主密钥进行加密。
// 强烈建议为每个主密钥都配置描述信息，由客户端保存主密钥和描述信息之间的对应关系（服务端不保存两者之间的对应关系）。
        Map<String, String> matDesc = new HashMap<String, String>();
        matDesc.put("<yourDescriptionKey>", "<yourDescriptionValue>");

// 创建RSA加密材料。
        SimpleRSAEncryptionMaterials encryptionMaterials = new SimpleRSAEncryptionMaterials(keyPair, matDesc);
// 如果要下载并解密其他RSA密钥加密的文件，请将其他主密钥及其描述信息添加到加密材料中。
// encryptionMaterials.addKeyPairDescMaterial(<otherKeyPair>, <otherKeyPairMatDesc>);

// 创建RSA加密客户端。
        OSSEncryptionClient ossEncryptionClient = new OSSEncryptionClientBuilder().
                build(endpoint, accessKeyId, accessKeySecret, encryptionMaterials);
// 加密上传文件。
//        ossEncryptionClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpg");
        FileInputStream input = new FileInputStream("C:\\Users\\HUAWEI\\Desktop\\img.jpg");
        meta.setContentLength(input.available());
        ossEncryptionClient.putObject(bucketName, objectName, input, meta);


// 下载文件时自动解密。
        OSSObject ossObject = ossEncryptionClient.getObject(bucketName, objectName);
        IoUtil.write(new FileOutputStream("C:\\Users\\HUAWEI\\Desktop\\img666.jpg"), true, IoUtil.readBytes(ossObject.getObjectContent()));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
//        StringBuffer buffer = new StringBuffer();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            buffer.append(line);
//        }
//        reader.close();

// 查看解密后的内容是否与上传的明文一致。
//        System.out.println("Put plain text: " + content);
//        System.out.println("Get and decrypted text: " + buffer.toString());
    }

    private static void 上传() {

    }

}
