package com.robsil.erommerce.util;

import java.util.List;
import java.util.function.Predicate;

public class ListUtil {

    public static <T> boolean contains(List<T> list, Predicate<T> predicate) {
        return list.stream().anyMatch(predicate);
    }

}
