package ru.spsuace.homework2.objects.analyzer;

public class NegativeTextAnalyzer extends Analyzer implements TextAnalyzer {

    private final String[] Negativ = {"=(", ":(", ":|"};

    NegativeTextAnalyzer () {
        super(FilterType.NEGATIVE_TEXT);
    }
    public boolean ApplyFilter(String Text) {
        if (Text == null) {
            return  false;
        }
        for (String mask : Negativ) {
            if (Text.contains(mask)) {
                return true;
            }
        }
        return false;
    }
}
