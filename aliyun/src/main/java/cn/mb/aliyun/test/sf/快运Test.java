package cn.mb.aliyun.test.sf;

import cn.hutool.json.JSONUtil;
import cn.mb.aliyun.test.sf.code.SFSpeedAPIServiceCode;
import cn.mb.aliyun.test.sf.param.fast.QueryAgingAndFeeParam;
import cn.mb.aliyun.test.sf.util.SFUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/10
 */
public class 快运Test {

    private static final String CHECK_WORD = "IJTvm7ehZYup9utqqtQ894gSOZfmJ78c";//  hy
    private static final String PARTNER_ID = "HYXXKEKc8HU9";// hy

    public static void main(String[] args) throws Exception {

        运费时效查询();

    }

    private static void 运费时效查询() throws Exception {
        QueryAgingAndFeeParam queryAgingAndFeeParam = new QueryAgingAndFeeParam("浙江省", "杭州市", "滨江区", "海南省", "海口市", "美兰区", "1", "1000", "2");
        String request = SFUtil.request(SFUtil.SAND_BOX_URL, CHECK_WORD, PARTNER_ID, SFSpeedAPIServiceCode.QUERY_AGING_AND_FEE, JSONUtil.toJsonStr(queryAgingAndFeeParam));
    }

}
