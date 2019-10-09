package ru.spsuace.homework2.objects.october2;

import java.util.Arrays;
import java.util.Collections;

public class MaxTask {

    /**
     * Вам дан массив и количество элементов в возвращаемом массиве
     * Вернуть нужно массив из count максимальных элементов array, упорядоченный по убыванию.
     * Если длина массива меньше count, то вернуть null
     * Например ({1, 3, 10, 11, 22, 0}, 2) -> {22, 11}
     * ({1, 3, 22, 11, 22, 0}, 3) -> {22, 22, 11}
     *
     */
    public static int[] getMaxArray(int[] array, int count) {
        if (array.length < count ) {
            return  null;
        }
        int[] arr = {};
        if (count == 0) {
            return  arr;
        }

        Arrays.sort(array);
        arr = Arrays.copyOfRange(array, array.length - count, array.length);
        int[] reverseArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reverseArr[i] = arr[arr.length -i -1];
        }
        return  reverseArr;
    }

}
