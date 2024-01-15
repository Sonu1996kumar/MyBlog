package com.myblog;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestClass1 {
    public static void main(String[] args) {
//        Predicate<Integer> val = y->y%2==0;
//        boolean result = val.test(10);
//        System.out.println(result);

//        Predicate<String> val = str->str.equals("mike");
//       boolean result = val.test("aashif");
//        System.out.println(result);

//        List<Integer> numbers = Arrays.asList(10,12,1,5,16,19,20);
//        List<Integer> evenNumbers = numbers.stream().filter(n->n%2 == 0).collect(Collectors.toList());
//        System.out.println(evenNumbers);

//        List<Integer> numbers = Arrays.asList(10,12,1,5,16,19,20);
//        List<Integer> oddNumbers = numbers.stream().filter(n->n%2 != 0).collect(Collectors.toList());
//        System.out.println(oddNumbers);

        List<String> names = Arrays.asList("mike","adam","mike","stallin");
        List<String> data1 = names.stream().filter(s->s.startsWith("a")).collect(Collectors.toList());
        List<String> data2 = names.stream().filter(s->s.equals("mike")).collect(Collectors.toList());
        List<String> data3 = names.stream().filter(s->s.endsWith("n")).collect(Collectors.toList());
        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);
    }
}
