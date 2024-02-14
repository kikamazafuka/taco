package lt.springinaction.tacocloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(2);
        list.add(5);
        list.add(1);
        List<Integer> list2 = list
                .stream().filter(integer -> integer != 1).toList();

        list2.stream().forEach(System.out::println);
        list.stream().sorted().forEach(System.out::println);

    }
}
