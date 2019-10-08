package ru.spsuace.homework2.objects.analyzer;

/**
 * Типы фильтров
 */
public enum FilterType {
    TOO_LONG(2),
    SPAM(1),
    NEGATIVE_TEXT(3),
    CUSTOM(4),
    GOOD(0);

    int priority;
    FilterType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
