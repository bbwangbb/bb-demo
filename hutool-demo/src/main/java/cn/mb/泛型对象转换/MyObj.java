package cn.mb.泛型对象转换;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class MyObj {

    private String name;

    public static void main(String[] args) {
        MyResponse<MyObj> myResponse2 = JSONUtil.toBean("{\"msg\":\"msg\", \"data\":{\"name\":\"bb\"}}", new TypeReference<MyResponse<MyObj>>() {}, false);
        System.out.println(myResponse2);
    }

}
