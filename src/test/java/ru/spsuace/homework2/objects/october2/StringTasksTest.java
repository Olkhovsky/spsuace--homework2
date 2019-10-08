package ru.spsuace.homework2.objects.october2;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringTasksTest {

    @Test
    public void simpleValueOf() {
        System.out.println(Integer.MIN_VALUE);
        assertEquals(null, StringTasks.simpleValueOf(null));
        assertEquals(null, StringTasks.simpleValueOf(""));
        assertEquals(0, StringTasks.simpleValueOf("0"));
        assertEquals(0, StringTasks.simpleValueOf("a0"));
        assertEquals(-1, StringTasks.simpleValueOf("-a1"));
        assertEquals(null, StringTasks.simpleValueOf("--a1"));
        assertEquals(-12, StringTasks.simpleValueOf("-a1bsc2"));
        assertEquals(-132, StringTasks.simpleValueOf("a-132a"));
        assertEquals(2147483649L, StringTasks.simpleValueOf("asddgsdf2fg1474asdfg8364asd9asd"));
        assertEquals(-2147483649L, StringTasks.simpleValueOf("asd-dgsdf2fg1474asdfg8364asd9asd"));
        assertEquals(1e2, StringTasks.simpleValueOf("1e2"));
        assertEquals(-1e-2, StringTasks.simpleValueOf("asd-asd1asddecvxv-dsf2fsdv"));
        assertEquals(1e-3, StringTasks.simpleValueOf("1e-asdsad3"));
        assertEquals(1.2e-3, StringTasks.simpleValueOf("ccz1asd.zc2deg-h3j"));
        assertEquals(null, StringTasks.simpleValueOf("ccz1asd.zc2de.g-h3j"));
        assertEquals(1.3, StringTasks.simpleValueOf("fff1fdf.asdsad3"));
    }

    @Test
    public void valueOf() {
        System.out.println(Integer.MIN_VALUE);
        assertEquals(null, StringTasks.simpleValueOf(null));
        assertEquals(null, StringTasks.simpleValueOf(""));
        assertEquals(0, StringTasks.simpleValueOf("0"));
        assertEquals(0, StringTasks.simpleValueOf("a0"));
        assertEquals(-1, StringTasks.simpleValueOf("-a1"));
        assertEquals(null, StringTasks.simpleValueOf("--a1"));
        assertEquals(-12, StringTasks.simpleValueOf("-a1bsc2"));
        assertEquals(-132, StringTasks.simpleValueOf("a-132a"));
        assertEquals(2147483649L, StringTasks.simpleValueOf("asddgsdf2fg1474asdfg8364asd9asd"));
        assertEquals(-2147483649L, StringTasks.simpleValueOf("asd-dgsdf2fg1474asdfg8364asd9asd"));
        assertEquals(1e2, StringTasks.simpleValueOf("1e2"));
        assertEquals(-1e-2, StringTasks.simpleValueOf("asd-asd1asddecvxv-dsf2fsdv"));
        assertEquals(1e-3, StringTasks.simpleValueOf("1e-asdsad3"));
        assertEquals(1.2e-3, StringTasks.simpleValueOf("ccz1asd.zc2deg-h3j"));
        assertEquals(null, StringTasks.simpleValueOf("ccz1asd.zc2de.g-h3j"));
        assertEquals(1.3, StringTasks.simpleValueOf("fff1fdf.asdsad3"));
    }
}