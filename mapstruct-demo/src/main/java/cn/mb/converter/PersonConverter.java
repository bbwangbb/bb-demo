package cn.mb.converter;

import cn.mb.entity.Person;
import cn.mb.vo.PersonVO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  xx对象转换器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/26
 */
@Mapper(imports = {Arrays.class})// 如果@Mapping的expression中引用了外部jar，需要在此引入，否则编译不会自动引入
public interface PersonConverter {

    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

    //  实体 -> vo
    @Mappings({
            //  关系映射，类似mapper.xml中的resultMap
            @Mapping(target = "description", source = "desc"),
            @Mapping(target = "tags", expression = "java( Arrays.asList(person.getTags()).subList(0, 2) )")
    })
    PersonVO toPersonVO(Person person);

    //  实体list -> vo list
    List<PersonVO> toPersonVOList(List<Person> persons);

}