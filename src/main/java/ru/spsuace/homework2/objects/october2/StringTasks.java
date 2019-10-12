package ru.spsuace.homework2.objects.october2;


import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTasks {

    /**
     * Убрать все лишние символы из строки и вернуть получившееся число.
     * Разрешенные символы: цифры, '-', '.', 'e'
     * Если '.' и 'e' больше чем 1, возвращаем null
     * Любой класс-обертка над примитивами наследуется от Number
     * Можно использовать функции Double.valueOf() и другие такие же.
     *
     * Работайте со строкой, НЕ надо ее переводить в массив байт (это можно использовать только для цикла)
     * У класса Character есть полезные методы, например Character.isDigit()
     */
    public static Number simpleValueOf(String str) {
        if (str == null) {
            return  null;
        }
        String result = "";
        Pattern pattern  = Pattern.compile("\\d|e|-|\\.");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            result += str.substring(matcher.start(), matcher.end());
        }

        pattern = Pattern.compile("e");
        int count = GetCount(pattern, str);
        if (count > 1) {
            return null;
        }
        pattern = Pattern.compile("\\.");
        count = GetCount(pattern,str);
        if (count > 1) {
            return null;
        }

        pattern = Pattern.compile("-");
        count = GetCount(pattern,str);
        if (count > 1) {
            if (result.toCharArray()[0] == '-' && result.toCharArray()[1] == '-') {
                return  null;
            }
        }

        try {
            return Integer.valueOf(result);
        } catch (NumberFormatException e1) {
            try {
                return Long.valueOf(result);
            } catch (NumberFormatException e2) {
                try {
                    return Double.valueOf(result);
                } catch (NumberFormatException e3) {
                    return  null;
                }
            }
        }
    }

    private static int GetCount(Pattern pattern, String str) {
        int count = 0;
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            count++;
        }
        return  count;
    }


    /**
     * Тоже самое, что и выше, но нельзя пользоваться функциями valueOf()
     */
    public static Number valueOf(String str) {
        if (str == null) {
            return  null;
        }
        String result = "";
        Pattern pattern  = Pattern.compile("\\d|e|-|\\.");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            result += str.substring(matcher.start(), matcher.end());
        }

        pattern = Pattern.compile("e");
        int count = GetCount(pattern, str);
        if (count > 1) {
            return null;
        }
        pattern = Pattern.compile("\\.");
        count = GetCount(pattern,str);
        if (count > 1) {
            return null;
        }

        pattern = Pattern.compile("-");
        count = GetCount(pattern,str);
        if (count > 1) {
            if (result.toCharArray()[0] == '-' && result.toCharArray()[1] == '-') {
                return  null;
            }
        }

        try {
            return NumberFormat.getInstance().parse(result);
        } catch (Exception e) {
            return  null;
        }
    }
}

