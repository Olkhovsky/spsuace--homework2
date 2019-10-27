package ru.spsuace.homework2.collections;

import java.util.*;


/**
 * Написать структуру данных, реализующую интерфейс мапы + набор дополнительных методов.
 * 2 дополнительных метода должны вовзращать самый популярный ключ и его популярность.
 * Популярность - это количество раз, который этот ключ учавствовал в других методах мапы, такие как
 * containsKey, get, put, remove.
 * Считаем, что null я вам не передю ни в качестве ключа, ни в качестве значения
 *
 * Важный момент, вам не надо реализовывать мапу, вы должны использовать композицию.
 * Вы можете использовать любые коллекции, которые есть в java. Перечислю реализации основных типов коллекций:
 * List -> {@link java.util.ArrayList}
 * Set -> {@link java.util.HashSet}
 * Map -> {@link java.util.HashMap}
 * Stack -> {@link java.util.Stack}
 * Queue -> {@link java.util.LinkedList}
 * Deque -> {@link java.util.ArrayDeque}
 * Для быстрого перехода в нужный класс или метод, просто зажмите ctrl и щелкните по нему мышкой, или просто щелкните
 * колесиком. Бывает удобно, когда нужно переходить из одной точки кода в другую
 *
 * Помните, что по мапе тоже можно итерироваться
 *
 *         for (Map.Entry<K, V> entry : map.entrySet()) {
 *             entry.getKey();
 *             entry.getValue();
 *         }
 *
 *
 * Дополнительное задание описано будет ниже
 * @param <K> - тип ключа
 * @param <V> - тип значения
 */
public class PopularMap<K, V> implements Map<K, V> {

    private final Map<K, V> map;
    private final HashMap<K, Integer> keyCount;
    private final HashMap<V, Integer> valueCount;

    public PopularMap() {
        map = new HashMap<>();
        keyCount = new HashMap<>();
        valueCount = new HashMap<>();
    }

    public PopularMap(Map<K, V> map) {
        this.map = map;
        keyCount = new HashMap<>();
        valueCount = new HashMap<>();

        for(K key : map.keySet()) {
            keyCount.put(key, 0);
        }
        for(V value: map.values()) {
                valueCount.put(value, 0);
        }

    }

    @Override
    public int size() {
        return  map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        IncrementKeyCount(key);
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        IncrementValueCount(value);
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        IncrementKeyCount(key);
        V value = map.get(key);
        IncrementValueCount(value);
        return value;
    }



    @Override
    public V put(K key, V value) {
        IncrementKeyCount(key);
        IncrementValueCount(value);
        return  map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        IncrementKeyCount(key);
        IncrementValueCount(map.get(key));
        return  map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    private void IncrementKeyCount(Object key) {
        Object o = keyCount.get(key);
        if (o != null)
        {
            int value = (int)o + 1;
            keyCount.put((K) key, value);
        } else {
            keyCount.put((K)key, 1);
        }
    }
    private void IncrementValueCount(Object keyValue) {
        Object o = valueCount.get(keyValue);
        if (o != null)
        {
            int value = (int)o + 1;
            valueCount.put((V)keyValue, value);
        } else {
            valueCount.put((V)keyValue, 1);
        }
    }

    /**
     * Возвращает самый популярный, на данный момент, ключ
     */
    public K getPopularKey() {
        return GetKeyLinkedToMaxVal(keyCount);
    }


    /**
     * Возвращает количество использование ключа
     */
    public int getKeyPopularity(K key) {
        Object o =  keyCount.get(key);
        if (o != null)
            return (int)o;
        return  0;
    }

    /**
     * Дополнительное задание (1 балл)
     * Возвращает самое популярное, на данный момент, значение. Надо учесть что значени может быть более одного
     */
    public V getPopularValue() {
        return GetKeyLinkedToMaxVal(valueCount);
    }
    /**
     * Дополнительное задание (1 балл)
     * Возвращает количество использований значений в методах: containsValue, get, put (учитывается 2 раза, если
     * старое значение и новое - одно и тоже), remove (считаем по старому значению).
     */
    public int getValuePopularity(V value) {
        return valueCount.get(value);
    }

    /**
     * Дополнительное задание (1 балла)
     * Вернуть итератор, который итерируется по значениям (от самых НЕ популярных, к самым популярным)
     */
    public Iterator<V> popularIterator() {
        return null;
    }

    private<T> T GetKeyLinkedToMaxVal(Map<T,Integer> map)
    {
        return map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() - entry2.getValue()).get().getKey();
    }
}
