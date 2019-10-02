package ru.spsuace.homework2.objects.analyzer;

public class Analyzer implements TextAnalyzer {
    private final FilterType Type;
    private long MaxLenght;
    private String[] Spam;
    private String[] Negativ = {"=(", ":(", ":|"};

    public Analyzer (FilterType type) {
        Type = type;
    }
    public boolean ApplyFilter(String Text) {
        switch (Type) {
            case TooLongAnalyzer:
                if (Text.length() > MaxLenght) {
                    return  true;
                }
                break;
            case SpamAnalyzer:
                for(String mask : Spam) {
                    if (Text.contains(mask)) {
                        return  true;
                    }
                }
                break;

            case NegativeTextAnalyzer:
                for(String mask : Negativ) {
                    if (Text.contains(mask)) {
                        return  true;
                    }
                }
                break;
        }
        return  false;
    }
    public FilterType GetFilterType() {
        return Type;
    }

    public void SetMaxLength(long maxLenght) {
        MaxLenght = maxLenght;
    }

    public void SetSpam (String[] spam) {
        Spam = spam;
    }
}
