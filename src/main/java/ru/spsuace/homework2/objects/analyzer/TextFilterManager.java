package ru.spsuace.homework2.objects.analyzer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Задание написать систему фильтрации комментариев.
 * Надо реализовать три типа обязательных фильтров
 * 1) фильтр для слишком длинных текстов (длина задается при создании) (TOO_LONG)
 * 2) фильтр для спама (передается массив плохих слов, которых не должно быть в тексте) (SPAM)
 * 3) фильтр для текстов с плохими эмоциями. (в тексте не должно быть таких смайлов:
 * "=(", ":(", ":|" (NEGATIVE_TEXT)
 * + в качестве доп задания, можете сделать любой свой фильтр (CUSTOM)
 * <p>
 * Класс TextFilterManager должен содержать все фильтры, которые передаются ему в конструкторе,
 * и при анализе текста через метод analyze должен выдавать первый "успешный" фильтр,
 * если не один не прошел, то возвращать тип GOOD.
 * + в качестве доп задания, можно всем типам фильтров задать приоритет
 * (SPAM, TOO_LONG, NEGATIVE_TEXT, CUSTOM - в таком порядке) и возвращать тип с максимальным приоритетом.
 * Отсортировать фильтра можно с помощью функции
 * Arrays.sort(filter, (filter1, filter2) -> {
 *     if (filter1 < filter2) {
 *         return -1;
 *     } else if (filter1 == filter2) {
 *         return 0;
 *     }
 *     return 1;
 * }
 * где вместо сравнение самих фильтров должно быть стравнение каких-то количественных параметров фильтра
 */
public class TextFilterManager {

    /**
     * Для работы с каждым элементом массива, нужно использовать цикл for-each
     * Хочется заметить, что тут мы ничего не знаем, какие конкретно нам объекты переданы, знаем только то,
     * что в них реализован интерфейс TextAnalyzer
     */

    Iterable<TextAnalyzer> Filters;
    public TextFilterManager(Iterable<TextAnalyzer> filters) {
        List<TextAnalyzer> list = new ArrayList();
        for (TextAnalyzer filter : filters) {
            list.add(filter);
        }

        Collections.sort(list, (filter1, filter2) -> {
            int priority1 = filter1.GetFilterType().priority;
            int priority2 = filter1.GetFilterType().priority;
            if (priority1 < priority2) {
                return -1;
            } else if (priority1 == priority2) {
                return 0;
            }
            return 1;
        });
        Filters = list;
    }

    /**
     * Если переменная текст никуда не ссылается, то это означает, что не один фильтр не сработал
     */
    public FilterType analyze(String text) {
        for (TextAnalyzer filter : Filters) {
            if (filter.ApplyFilter((text))) {
                return filter.GetFilterType();
            }
        }
        return FilterType.GOOD;
    }
}
