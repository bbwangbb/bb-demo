package cn.mb.aliyun.test.java;

import java.util.function.*;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/22
 */
public class Test {

    public static void main(String[] args) {
        Supplier<String> stringSupplier = () -> "stringSupplier";
        System.out.println(stringSupplier.get());

        Consumer<String> stringConsumer = (str) -> System.out.println(str);
        stringConsumer.accept("stringConsumer");

        Function<String, Integer> stringIntegerFunction = (str) -> Integer.valueOf(str);
        System.out.println(stringIntegerFunction.apply("111"));

        Predicate<Integer> integerPredicate = (i) -> i > 0;
        System.out.println(integerPredicate.test(10));

        UnaryOperator<Integer> integerUnaryOperator = (i) -> i;

        BiFunction<Integer, Double, String> integerDoubleStringBiFunction = (i, d) -> i + " - " + d;
        System.out.println(integerDoubleStringBiFunction.apply(1, 2.0));

        System.out.println(integerDoubleStringBiFunction.andThen(stringIntegerFunction));


//        new DO().do1((函数式接口2)() -> System.out.println(1111));
    }

}

@FunctionalInterface
interface 函数式接口1 {
    void doThing();
}

@FunctionalInterface
interface 函数式接口2 {
    void doThing();
}


class DO {
    void do1(函数式接口1 函数式接口1) {
        System.out.println("函数式接口1");
        函数式接口1.doThing();
    }

    void do1(函数式接口2 函数式接口2) {
        System.out.println("函数式接口2");
        函数式接口2.doThing();
    }

    <T> T do1(Supplier<T> supplier) {
        System.out.println("函数式接口2");
        return supplier.get();
    }
}