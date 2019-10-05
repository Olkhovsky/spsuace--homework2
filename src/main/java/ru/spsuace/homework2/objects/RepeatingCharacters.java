package ru.spsuace.homework2.objects;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Нужно найти символ, который встречается подряд в строке чаще всего, и указать количество повторений.
 * Если более одного символа с максимальным значением, то нужно вернуть тот символ,
 * который первый встречается в строчке
 * Если строка пустая или null, то вернуть null
 * Пример abbasbdlbdbfklsssbb -> (s, 4)
 */
public class RepeatingCharacters {

    public static Pair<Character, Integer> getMaxRepeatingCharacters(String str) {
        if (str == null || str == "") {
            return  null;
        }
        char chars[] = str.toCharArray();
        char ch = chars[0];
        int count = 1;
        int maxValue = 1;
        for (int i = 1; i < str.length(); i++)
        {
            if (chars[i - 1] == chars[i]) {
                count++;
                if (maxValue < count) {
                    maxValue = count;
                    ch = chars[i - 1];
                }
            } else {
                count = 1;
            }
        }
        return  new Pair<>(ch, maxValue);
    }

    public static class Pair<T, V> {
        private final T first;
        private final V second;

        public Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

    }
}
