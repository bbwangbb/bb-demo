package cn.mb.test;

import cn.mb.converter.PersonConverter;
import cn.mb.entity.Person;
import cn.mb.vo.PersonVO;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/13
 */
public class Test {

    public static void main(String[] args) {

        //  实体list -> vo list
//        Person person1 = new Person("bb", 24, "男", "牛杯", new String[]{"矮", "穷", "挫"});
//        Person person2 = new Person("cc", 25, "女", "牛", new String[]{"高", "富", "帅"});
//        List<Person> persons = Arrays.asList(person1, person2);
//        List<PersonVO> personVOS = PersonConverter.INSTANCE.toPersonVOList(persons);
//        System.out.println(personVOS);

        //  实体 -> vo
        Person person = new Person("bb", 24, "男", "牛杯", new String[]{"矮", "穷", "挫"});
        PersonVO personVO = PersonConverter.INSTANCE.toPersonVO(person);
        System.out.println(personVO);

    }

}
