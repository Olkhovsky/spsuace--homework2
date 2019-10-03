package ru.spsuace.homework2.objects.analyzer;

public class Analyzer  {

    protected final FilterType Type;

    public Analyzer (FilterType type) {
        Type = type;
    }

    public FilterType GetFilterType() {
        return Type;
    }

}
