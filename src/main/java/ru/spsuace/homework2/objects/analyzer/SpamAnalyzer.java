package ru.spsuace.homework2.objects.analyzer;

public class SpamAnalyzer extends Analyzer {
    private Iterable<String> Spam;

    public SpamAnalyzer(Iterable<String> spam){
        super(FilterType.SPAM);
        Spam = spam;
    }

    protected SpamAnalyzer(FilterType type, Iterable<String> spam){
        super(type);
        Spam = spam;
    }

    @Override
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
