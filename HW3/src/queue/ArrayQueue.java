package queue;

public class ArrayQueue {
    // n >= 0 && a[i] != null(0 <= i < n)
    private int start = 0, end = 0;
    private Object[] elements = new Object[20];

    //PRE OK
    public int size() {
        if (end >= start) {
            return end - start;
        } else {
            return elements.length + end - start;
        }
    }
    //POST R = size && a[i]' = a[i] (for 0 <= i < size) && size' = size

    //PRE OK
    public boolean isEmpty() {
        return size() == 0;
    }
    //POST R = (size == 0) && a[i]' = a[i] (for 0 <= i < size) && size' = size

    //PRE (0 <= x < elements.length) && element.length != 0
    private int next(int x) {
        return (x + 1) % elements.length;
    }
    // POST //POST R = (size == 0) && a[i]' = a[i] (for 0 <= i < size) && size' = size

    private int prev(int x) {
        return (x - 1 + elements.length) % elements.length;
    }

    // PRE OK
    private void ensureCapacity() {
        if (end + 1 != start)
            return;
        int j = 0;
        Object[] newElements = new Object[2 * size() + 2];
        //for (int i = start; i != end; i = next(i))
        //  newElements[j++] = elements[i];
        if (start > end) {
            j = elements.length + end - start;
            System.arraycopy(elements, start, newElements, 0, elements.length - start);
            System.arraycopy(elements, 0, newElements, elements.length - start, end);
        } else {
            j = end - start;
            System.arraycopy(elements, start, newElements, 0, end - start);
        }

        elements = newElements;
        start = 0;
        end = j;
    }
    // POST (size < elements.length()) && INV is OK

    //PRE element != null
    public void enqueue(Object element) {
        assert element != null;
        ensureCapacity();
        elements[end] = element;
        end = next(end);
    }
    //POST size' = size + 1 && a[size] = element && a[i]' == a[i] (for i = 0...size - 1)

    //PRE x != null
    public void push(Object x) {
        assert x != null;

        ensureCapacity();
        elements[prev(start)] = x;
        start = prev(start);

    }
    //POST: size' = size + 1 && a[0]' = x && a[i]' = a[i - 1]


    //PRE: size > 0
    public Object remove() {
        assert size() > 0;
        Object ans = elements[prev(end)];
        end = prev(end);
        return ans;
    }
    //POST size' = size - 1 && a[i] = a[i]' for i 0...size' - 1

    //PRE size > 0
    public Object element() {
        assert size() > 0;
        return elements[start];
    }
    //POST R = a[0] && a[i]' == a[i] (for i = 0...size - 1) && size' == size


    //PREV size > 0
    public Object peek() {
        assert size() > 0;
        return elements[prev(end)];
    }
    //POST R = a[size - 1] size' = size && elements[i] = elements[i]' for i 0...size - 1

    //PRE size > 0
    public Object dequeue() {
        assert size() > 0;
        Object ans = elements[start];
        elements[start] = null;
        start = next(start);

        return ans;
    }
    //POST size' = size - 1 && a[i - 1]' = a[i]


    // PRE OK
    public void clear() {
        start = 0;
        end = 0;
        elements = new Object[2];

    }
    //POST size = 0


}