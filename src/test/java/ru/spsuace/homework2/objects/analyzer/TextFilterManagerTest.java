package ru.spsuace.homework2.objects.analyzer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class TextFilterManagerTest {

    @Test
    public void analyzeEmptyFilters() {
        TextFilterManager manager = new TextFilterManager(Collections.emptyList());
        assertEquals("GOOD", manager.analyze("Привет, я Петя :(").toString());
        assertEquals("GOOD", manager.analyze("").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("GOOD", manager.analyze("Скажите код из смс").toString());
        assertEquals("GOOD", manager.analyze("Ооооооочень длиннннннаааааяяяя стрроооооооккккаааааа").toString());
    }

    @Test
    public void analyzeOnlyLongFilter() {
        TextFilterManager manager = new TextFilterManager(Collections.singleton(TextAnalyzer.createTooLongAnalyzer(19)));
        assertEquals("GOOD", manager.analyze("Привет, я Петя :(").toString());
        assertEquals("GOOD", manager.analyze("").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("TOO_LONG", manager.analyze("Скажите код из смс  ").toString());
        assertEquals("TOO_LONG", manager.analyze("Ооооооочень длиннннннаааааяяяя стрроооооооккккаааааа").toString());

        manager = new TextFilterManager(Collections.singleton(TextAnalyzer.createTooLongAnalyzer(0)));
        assertEquals("TOO_LONG", manager.analyze("Привет, я Петя").toString());
        assertEquals("GOOD", manager.analyze("").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("TOO_LONG", manager.analyze("Скажите код из смс  ").toString());
        assertEquals("TOO_LONG", manager.analyze("Ооооооочень длиннннннаааааяяяя стрроооооооккккаааааа").toString());

        manager = new TextFilterManager(Arrays.asList(
                TextAnalyzer.createTooLongAnalyzer(19),
                TextAnalyzer.createTooLongAnalyzer(8)));
        assertEquals("TOO_LONG", manager.analyze("Привет, я Петя :(").toString());
        assertEquals("GOOD", manager.analyze("").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("TOO_LONG", manager.analyze("Скажите код из смс  ").toString());
        assertEquals("TOO_LONG", manager.analyze("Ооооооочень длиннннннаааааяяяя стрроооооооккккаааааа").toString());

    }

    @Test
    public void analyzeOnlySpamFilter() {
        TextFilterManager manager = new TextFilterManager(
                Collections.singleton(TextAnalyzer.createSpamAnalyzer(Arrays.asList("я", "й"))));
        assertEquals("SPAM", manager.analyze("Привет, я Петя :(").toString());
        assertEquals("GOOD", manager.analyze("").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("GOOD", manager.analyze("Скажите код из смс  :(").toString());
        assertEquals("SPAM", manager.analyze("Скажите код из смс пожалуйста ").toString());
        assertEquals("SPAM", manager.analyze("Ооооооочень длиннннннаааааяяяя стрроооооооккккаааааа").toString());

        manager = new TextFilterManager(Arrays.asList(
                TextAnalyzer.createSpamAnalyzer(Collections.singleton("я")),
                TextAnalyzer.createSpamAnalyzer(Collections.singleton("й"))));
        assertEquals("SPAM", manager.analyze("Привет, я Петя :(").toString());
        assertEquals("GOOD", manager.analyze("").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("GOOD", manager.analyze("Скажите код из смс  :(").toString());
        assertEquals("SPAM", manager.analyze("Скажите код из смс пожалуйста ").toString());
        assertEquals("SPAM", manager.analyze("Ооооооочень длиннннннаааааяяяя стрроооооооккккаааааа").toString());

        manager = new TextFilterManager(Collections.singleton(TextAnalyzer.createSpamAnalyzer(Collections.emptyList())));
        assertEquals("GOOD", manager.analyze("Привет, я Петя :(").toString());
        assertEquals("GOOD", manager.analyze("").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("GOOD", manager.analyze("Скажите код из смс  :(").toString());
        assertEquals("GOOD", manager.analyze("Скажите код из смс пожалуйста ").toString());
        assertEquals("GOOD", manager.analyze("Ооооооочень длиннннннаааааяяяя стрроооооооккккаааааа").toString());
    }

    @Test
    public void analyzeOnlyNegativeFilter() {
        TextFilterManager manager = new TextFilterManager(
                Collections.singleton(TextAnalyzer.createNegativeTextAnalyzer()));
        assertEquals("NEGATIVE_TEXT", manager.analyze("Привет, я Петя :(").toString());
        assertEquals("GOOD", manager.analyze("").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("GOOD", manager.analyze("Скажите код из смс :-( ").toString());
        assertEquals("NEGATIVE_TEXT", manager.analyze("Скажите код из смс пожалуйста :|").toString());
        assertEquals("GOOD", manager.analyze("Ооооооочень длиннннннаааааяяяя стрроооооооккккаааааа").toString());
    }

    @Test
    public void analyzeAllFiltersGood() {
        TextFilterManager manager = new TextFilterManager(Arrays.asList(
                TextAnalyzer.createNegativeTextAnalyzer(),
                TextAnalyzer.createSpamAnalyzer(Arrays.asList("пинкод", "смс", "cvv")),
                TextAnalyzer.createTooLongAnalyzer(20)));
        assertEquals("GOOD", manager.analyze("Привет, я Петя :-(").toString());
        assertEquals("GOOD", manager.analyze("").toString());
        assertEquals("GOOD", manager.analyze(null).toString());
        assertEquals("GOOD", manager.analyze("СкажитеКодИзСмс:-(").toString());
        assertEquals("GOOD", manager.analyze("сМс пожалуйста ;|").toString());
    }

    @Test
    public void analyzeAllFiltersOne() {
        TextFilterManager manager = new TextFilterManager(Arrays.asList(
                TextAnalyzer.createNegativeTextAnalyzer(),
                TextAnalyzer.createSpamAnalyzer(Arrays.asList("пинкод", "смс", "cvv")),
                TextAnalyzer.createTooLongAnalyzer(20)));
        assertEquals("NEGATIVE_TEXT", manager.analyze("Привет, я Петя :(").toString());
        assertEquals("TOO_LONG", manager.analyze("Скажите Код Из Смс :-(").toString());
        assertEquals("SPAM", manager.analyze("смс пожалуйста ;|").toString());
    }

    @Test
    public void analyzeAllFiltersMany() {
        manyFilters(false);
    }
//
//    @Test
//    public void analyzeAllFiltersManyWithPriority() {
//        manyFilters(true);
//    }
//
    @Test
    private void manyFilters(boolean withPriority) {
        TextFilterManager manager = new TextFilterManager(Arrays.asList(
                TextAnalyzer.createNegativeTextAnalyzer(),
                TextAnalyzer.createSpamAnalyzer(Arrays.asList("пинкод", "смс", "cvv")),
                TextAnalyzer.createTooLongAnalyzer(20)));
        if (withPriority) {
            assertEquals("SPAM", manager.analyze("Привет, я Петя вот мой cvv").toString());
            assertEquals("TOO_LONG", manager.analyze("Скажите Код Из Смс :(").toString());
            assertEquals("SPAM", manager.analyze("смс пожалуйста           :|").toString());
        } else {
            assertTrue(Arrays.asList("SPAM", "TOO_LONG").contains(
                    manager.analyze("Привет, я Петя вот мой cvv").toString()));
            assertTrue(Arrays.asList("NEGATIVE_TEXT", "TOO_LONG").contains(
                    manager.analyze("Скажите Код Из Смс :(").toString()));
            assertTrue(Arrays.asList("NEGATIVE_TEXT", "TOO_LONG", "SPAM").contains(
                    manager.analyze("смс пожалуйста           =(").toString()));
        }
    }


}