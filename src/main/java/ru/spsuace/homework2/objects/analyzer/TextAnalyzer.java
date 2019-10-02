package ru.spsuace.homework2.objects.analyzer;


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
        Analyzer result = new Analyzer(FilterType.TooLongAnalyzer);
        result.SetMaxLength((maxLength));
        return  result;
    }

    static TextAnalyzer createSpamAnalyzer(String[] spam) {
        return new Analyzer(FilterType.SpamAnalyzer);

    }

    static TextAnalyzer createNegativeTextAnalyzer() {
        return new Analyzer(FilterType.NegativeTextAnalyzer);
    }

    /**
     * Дополнительное задание: придумать свой фильтр
     */
    static <T> TextAnalyzer createCustomAnalyzer(T something) {
        return null;
    }


    boolean ApplyFilter (String Text);
    FilterType GetFilterType();
}
