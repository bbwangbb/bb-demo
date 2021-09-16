package cn.mb.service;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/8/23
 */
public class DemoService {

    public String name;
    public Integer age;
    public DemoService(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public String bb(){
        return "name:" + name + "\tage:" + age;
    }

}
