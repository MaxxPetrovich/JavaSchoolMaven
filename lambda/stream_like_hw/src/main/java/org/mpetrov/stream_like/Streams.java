package org.mpetrov.stream_like;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Streams<T> {
    private ArrayList<T> list;

    public static <T> Streams<T> of(List<T> list) {
        Streams<T> stream = new Streams<>();
        stream.list = new ArrayList<>(list);
        return stream;
    }

    public Streams<T> filter(Predicate<T> predicate) {
        ArrayList<T> innerList = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) innerList.add(item);
        }
        list = innerList;
        return this;
    }

    public Streams<T> transform(UnaryOperator<T> operator) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, operator.apply(list.get(i)));
        }
        return this;
    }

    public <K, V> Map<K, V> toMap(Function<T, K> key, Function<T, V> value) {
        Map<K, V> result = new HashMap<>();
        for (T item : list) {
            result.put(key.apply(item), value.apply(item));
        }
        return result;
    }
}

