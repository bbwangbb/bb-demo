package cn.mb.泛型对象转换;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/6/7
 */
public class Test {


    public static void main(String[] args) {
//        MyResponse<MyObj> myResponse = new MyResponse<>();
//        myResponse.setCode(1);
//        MyObj myObj = new MyObj();
//        myObj.setName("bb");
//        myResponse.setData(myObj);
//        String jsonStr = JSONUtil.toJsonStr(myResponse);
//        System.out.println(jsonStr);
//
//        MyResponse<MyObj> myResponse1 = JSONUtil.toBean(jsonStr, new TypeReference<MyResponse<MyObj>>() {}, false);
//        System.out.println(myResponse1);
//
        //  一个参数Nonnull可以。2个不性 ----
        //  不是这样的，因为NonNull只对引用类型生效
        //  加上了在构造器也会对其做NUll判断
        //  再看看@RequiredArgsConstructor
        MyResponse<MyObj> myResponse2 = JSONUtil.toBean("{\"msg\":\"msg\", \"data\":{\"name\":\"bb\"}}", new TypeReference<MyResponse<MyObj>>() {}, false);
        System.out.println(myResponse2);



//        RentaiResult<AddResult> a = new RentaiResult<>();
//        a.setCode(1);
//        a.setMsg("1");
//        RentaiResult<AddResult> a = new RentaiResult<>(1, "msg");
//        AddResult addResult = new AddResult();
//        addResult.setBd_id("1");
//        a.setData(addResult);
//        String jsonStr1 = JSONUtil.toJsonStr(a);
//        System.out.println(jsonStr1);
//
//        RentaiResult<AddResult> myResponse3 = JSONUtil.toBean(jsonStr1, new TypeReference<RentaiResult<AddResult>>() {}, false);
//        System.out.println(myResponse3);
//
//
//        RentaiResult<AddResult> myResponse4 = JSONUtil.toBean("{\"code\":1,\"msg\":\"提交成功\",\"data\":{\"bd_id\":\"34\"}}", new TypeReference<RentaiResult<AddResult>>() {}, false);
//        System.out.println(myResponse4);
    }

}
