package cn.mb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonVO {

    private String  name;
    private Integer age;
    private String  description;
    private List<String> tags;

}
