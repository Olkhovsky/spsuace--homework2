package ru.spsuace.homework2.collections;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Реализовать двусвязный список, аналог LinkedList в java (то что я рассказывал на лекции)
 * Если не понятно, что должны возвращать методы, смотрите документацию интерфейсов List и Collection
 * Если в метод передается индекс, которого не существует (за исключением, add(size(), obj)), то надо бросить ошибку:
 * throw new IndexOutOfBoundsException()
 */

public class DoubleLinkedList<T> implements Iterable<T>{

    private Link first;
    private Link last;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        int index = indexOf((T)o);
        if (index >= 0)
            return true;
        return  false;
    }

    public void clear() {
        last = null;
        first = null;
        size = 0;
    }

    public void add(int index, T element) {
        if (index <= 0) {
            addFirst(element);
            return;
        }

        if (index >= size()) {
            addLast(element);
            return;
        }


        Link current = getLink(index);
        size++;
        Link<T> newLink = new Link<T>(element);

        newLink.next = current;
        newLink.previous = current.previous;
        current.previous.next = newLink;
        current.previous = newLink;
    }

    public void addLast(T element) {
        size++;
        Link<T> newLink = new Link<T>(element);
        if (last != null) {
            newLink.previous = last;
            last.next = newLink;
            last = newLink;
        } else {
            last = newLink;
            first = newLink;
        }

    }


    public void addFirst(T element) {
        size++;
        Link<T> newLink = new Link<T>(element);
        if (first != null) {
            newLink.next = first;
            first.previous = newLink;
            first = newLink;
        } else {
            last = newLink;
            first = newLink;
        }
    }


    public T set(int index, T element) {
        if (index >= size || index < 0) {
            return  null;
        }
        Link<T> current = getLink(index);
        T buffer = current.data;
        current.data = element;
        return buffer;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return  null;
        }
        return (T)getLink(index).data;
    }

    public int indexOf(T o) {
        int index = -1;
        Link current = first;
        while (current != null) {
            index++;
            if(current.data.equals(o)) {
                return index;
            }
            current = current.next;
        }
        return -1;
    }

    public T remove(int index) {
        if (index >= size || index < 0) {
            return  null;
        }

        Link current = getLink(index);
        size--;

        if (index == 0) {
            first = current.next;
            return (T)current.data;
        }

        if (index >= size) {
            last = current.previous;
            return (T)current.data;
        }

        current.next.previous = current.previous;
        current.previous.next = current.next;
        return (T)current.data;
    }

    /**
     * Дополнительное задание
     */
    @Override
    public Iterator<T> iterator() {
//        Link current = first;
//        ArrayList list = new ArrayList<T>();
//        while (current != null) {
//            list.add(current.data);
//            current = current.next;
//        }
//        return list.iterator();

        return first;
    }

    private Link<T> getLink(int index)
    {
        Link current = null;
        if (index < size / 2) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = 0; i < (size -1) - index; i++) {
                current = current.previous;
            }
        }
        return  current;
    }



    private static class Link<T> implements Iterator<T> {
        private T data;
        private Link next;
        private Link previous;

        private Link(T data)
        {
            this.data = data;
        }

        @Override
        public boolean hasNext() {
            if(next != null)
                return  true;
            return  false;
        }

        @Override
        public T next() {
            return (T)next.data;
        }
        @Override
        public void remove() {

            if (previous == null) {
                next.previous = null;
                return;
            }

            if (next == null) {
                previous.next = null;
                return;
            }

            next.previous = previous;
            previous.next = next;
        }
    }

}
