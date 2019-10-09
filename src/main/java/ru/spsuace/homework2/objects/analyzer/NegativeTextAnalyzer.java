package ru.spsuace.homework2.objects.analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NegativeTextAnalyzer extends SpamAnalyzer {

    NegativeTextAnalyzer() {
        super(FilterType.NEGATIVE_TEXT, Arrays.asList("=(", ":(", ":|"));
    }
}
