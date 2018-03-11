package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Queue {
    // n >= 0 && a[i] != null(0 <= i < n)

    //PRE element != null)
    void enqueue(Object element);
    //POST size' = size + 1 && a[size] = element && a[i]' == a[i] (for i = 0...size - 1)

    //PRE size > 0
    Object element();
    //POST R = a[0] && a[i]' == a[i] (for i = 0...size - 1) && size' == size

    //PRE size > 0
    Object dequeue();
    //POST size' = size - 1 && a[i - 1]' = a[i]

    //PRE OK
    int size();
    //POST R = size && a[i]' = a[i] (for 0 <= i < size) && size' = size

    //PRE OK
    boolean isEmpty();
    //POST R = (size == 0) && a[i]' = a[i] (for 0 <= i < size) && size' = size


    // PRE OK
    void clear();
    //POST size = 0

    //PRE OK
    Object filter(Predicate<Object> predicate);
    //POST a' - new queue.
    //order in a' the same in a
    // a not change
    // a'.size - MaX

    //PRE OK
    Object map(Function<Object, Object> function);
    //POST a' - new queue.
    // a not change
    // a'[i] = f(a[i])
    // a'.size = a.size
}
