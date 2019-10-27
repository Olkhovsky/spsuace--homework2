package ru.spsuace.homework2.collections;

import java.util.*;


/**
 * Реализуйте симметричную разность двух множеств: https://ru.wikipedia.org/wiki/Симметрическая_разность
 * Исходные множества модифицировать нельзя.
 * Пример: {1, 2, 3} и {0, 1, 2} -> {0, 3}.
 *
 */
public class SymmetricDifference {

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> result1 = new HashSet<>(set1);
        result1.removeAll(set2);

        Set<T> result2 = new HashSet<>(set2);
        result2.removeAll(set1);

        result1.addAll(result2);
        return  result1;
    }
}
