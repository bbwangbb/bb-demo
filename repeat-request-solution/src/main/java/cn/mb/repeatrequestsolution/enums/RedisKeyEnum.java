package cn.mb.repeatrequestsolution.enums;

/**
 * <p>
 *  redis key枚举类
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
public enum  RedisKeyEnum {

    REP_REQ_SN_KEY("重复请求根据请求编号处理的key", "REQ", 0L, 60L),
    REP_REQ_PARAM_KEY("重复请求根据参数处理的key", "USER=%sM=%sP=%s", 0L, 60L),
    ;

    //  描述
    private String desc;
    //  键名
    private String key;
    //  值
    private Object value;
    //  过期时间(单位:s)
    private Long expireTime;

    RedisKeyEnum(String desc, String key, Object value, Long expireTime) {
        this.desc = desc;
        this.key = key;
        this.value = value;
        this.expireTime = expireTime;
    }

    public String getDesc() {
        return desc;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Long getExpireTime() {
        return expireTime;
    }
}
