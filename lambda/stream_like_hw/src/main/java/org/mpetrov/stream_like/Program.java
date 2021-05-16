package org.mpetrov.stream_like;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        List<Person> someCollection = new ArrayList<>();
        someCollection.add(new Person("John", 35));
        someCollection.add(new Person("Jane", 30));
        someCollection.add(new Person("Bob", 37));
        someCollection.add(new Person("Sarah", 28));

        Map<String, Person> m = Streams.of(someCollection)
                .filter(p -> p.getAge() > 30)
                .transform(p -> new Person(p.getName(), p.getAge() - 10))
                .toMap(Person::getName, p -> p);
        System.out.println(m);
    }

}
