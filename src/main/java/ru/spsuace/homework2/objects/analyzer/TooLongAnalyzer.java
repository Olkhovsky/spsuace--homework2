package ru.spsuace.homework2.objects.analyzer;

public class TooLongAnalyzer extends Analyzer implements TextAnalyzer {
    private long MaxLength;

    TooLongAnalyzer(long maxLength){
        super(FilterType.TOO_LONG);
        MaxLength = maxLength;
    }

    @Override
    public boolean ApplyFilter(String Text) {
        if (Text.length() > MaxLength) {
            return true;
        }
        return  false;
    }
}
