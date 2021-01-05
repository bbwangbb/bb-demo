package cn.mb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String  name;
    private Integer age;
    private String  sex;
    private String  desc;
    private String[] tags;

}
