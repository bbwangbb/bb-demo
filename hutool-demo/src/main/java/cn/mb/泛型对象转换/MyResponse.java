package cn.mb.泛型对象转换;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MyResponse<T> {
    @NonNull
    private Integer code;
    private T data;
}