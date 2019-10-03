package ru.spsuace.homework2.objects.analyzer;

public class SpamAnalyzer extends Analyzer implements TextAnalyzer {
    private Iterable<String> Spam;

    SpamAnalyzer(Iterable<String> spam){
        super(FilterType.SPAM);
        Spam = spam;
    }
    public boolean ApplyFilter(String Text) {
        if (Text == null) {
            return  false;
        }
        for (String mask : Spam) {
            if (Text.contains(mask)) {
                return true;
            }
        }
        return false;
    }
}
