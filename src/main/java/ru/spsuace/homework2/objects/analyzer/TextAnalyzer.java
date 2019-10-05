package ru.spsuace.homework2.objects.analyzer;


import java.util.Arrays;


/**
 * Базовый интерефейс фильтра, наследники этого интерефейса должны инкапсулировать в себе всю логику
 * анализа текста.
 * Ниже надо реализовать методы, которые создают фильтры заданного типа (то что они возвращают интерфейс, это как раз
 * прием ООП, где нам не важна конкретная реализация, а важен только контракт, что результат статических методов
 * умеет как-то анализировать текст). Сами статические методы мне нужны для создания тестов,
 * что бы без реальных классов (которые вы напишите) я смог "сэмулировать" их создание.
 *
 * Так же необходимо создать все необходимы методы, которые будут вам нужны для прогона текста
 * по всем фильтрам в классе TextFilterManager
 */
public interface TextAnalyzer {
    static TextAnalyzer createTooLongAnalyzer(long maxLength) {
        return  new TooLongAnalyzer(maxLength);
    }

    static TextAnalyzer createSpamAnalyzer(Iterable<String> spam) {
        return new SpamAnalyzer(spam);
    }

    static TextAnalyzer createNegativeTextAnalyzer() {
        return new SpamAnalyzer(Arrays.asList("=(", ":(", ":|"));
    }

    /**
     * Дополнительное задание: придумать свой фильтр
     */
    static TextAnalyzer createCustomAnalyzer(String key) {
        return new CustomAnalyzer(key);
    }


    boolean ApplyFilter (String Text);
    FilterType GetFilterType();
}
