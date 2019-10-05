package ru.spsuace.homework2.objects.analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NegativeTextAnalyzer extends Analyzer {

    private List<String> Negative = Arrays.asList("=(", ":(", ":|");

    NegativeTextAnalyzer(Iterable<String> spam){
        super(FilterType.NEGATIVE_TEXT);
    }

    @Override
    public boolean ApplyFilter(String Text) {
        SpamAnalyzer spam = new SpamAnalyzer(Negative);
        if (spam.ApplyFilter(Text)) {
            return true;
        }
        return false;
    }
}
