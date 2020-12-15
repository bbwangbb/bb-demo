package cn.mb.repeatrequestsolution.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import cn.mb.repeatrequestsolution.exception.BusinessException;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * <p>
 *  请求去重工具
 *      根据参数去重
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
public class ReqReqHelper {

    /**
     * <p>
     *  通过set k v ex nx验证重复请求
     * </p>
     * @param redisUtil     redis工具
     * @param key           key
     * @param expireTime    过期时间
     * @return void
     * @author guohaibin
     * @date 2020-11-20 17:27:02
     */
    public static void setExNxVerifyRepReq(RedisUtil redisUtil, String key, Long expireTime) {
        Boolean successSet = redisUtil.setExNx(key, "", expireTime);
        if (successSet == null || !successSet) {
            throw new BusinessException("请勿重复请求！");
        }
    }

    /**
     * <p>
     *  通过incr验证重复请求
     * </p>
     * @param redisUtil redis工具
     * @param key       key
     * @return void
     * @author guohaibin
     * @date 2020-11-20 17:27:54
     */
    public static void setExVerifyRepReq(RedisUtil redisUtil, String key) {
        if (!redisUtil.hasKey(key)) {
            throw new BusinessException("未携带下单凭证/下单凭证失效！");
        }
        Long value = redisUtil.incr(key, 1L);
        if (!value.equals(1L)) {
            if (value.compareTo(10L) >= 0) {
                throw new BusinessException("重复请求过多,禁用用户请求2分钟！");
            }
            throw new BusinessException("请勿重复请求！");
        }
    }

    /**
     * <p>
     *  参数md5加密,将需要排除的key排除
     * </p>
     *
     * @param reqJSON 请求的参数，这里通常是JSON
     * @param excludeKeys 请求参数里面要去除哪些字段再求摘要
     * @return 去除参数的MD5摘要
     */
    public static String paramMD5(final String reqJSON, String... excludeKeys) {
        String decryptParam = reqJSON;
        TreeMap paramTreeMap = JSONUtil.toBean(decryptParam, TreeMap.class);
        if (excludeKeys!=null) {
            List<String> dedupExcludeKeys = Arrays.asList(excludeKeys);
            if (!dedupExcludeKeys.isEmpty()) {
                for (String dedupExcludeKey : dedupExcludeKeys) {
                    paramTreeMap.remove(dedupExcludeKey);
                }
            }
        }
        String paramTreeMapJSON = JSONUtil.toJsonStr(paramTreeMap);
        String md5Param = SecureUtil.md5(paramTreeMapJSON);
        return md5Param;
    }

}
