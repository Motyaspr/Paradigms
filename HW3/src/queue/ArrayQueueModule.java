package queue;

public class ArrayQueueModule {
    // n >= 0 && a[i] != null(0 <= i < n)
    private static int start = 0, end = 0;
    private static Object[] elements = new Object[20];

    //PRE OK
    public static int size() {
        if (end >= start)
            return end - start;
        else
            return elements.length + end - start;
    }
    //POST R = size && a[i]' = a[i] (for 0 <= i < size) && size' = size

    //PRE OK
    public static boolean isEmpty() {
        return size() == 0;
    }
    //POST R = (size == 0) && a[i]' = a[i] (for 0 <= i < size) && size' = size

    //PRE (0 <= x < elements.length) && element.length != 0
    private static int next(int x) {
        return (x + 1) % elements.length;
    }
    // POST //POST R = (size == 0) && a[i]' = a[i] (for 0 <= i < size) && size' = size

    private static int prev(int x) {
        return (x - 1 + elements.length) % elements.length;
    }

    // PRE OK
    private static void ensureCapacity() {
        if (end + 1 != start)
            return;
        int j = 0;
        Object[] newElements = new Object[2 * size() + 2];
        if (start <= end){
            j = end - start;
            System.arraycopy(elements, start, newElements, 0, end - start);
        }
        else{
            j = elements.length + end - start;
            System.arraycopy(elements, start, newElements, 0, elements.length - start);
            System.arraycopy(elements, 0, newElements, elements.length - start, end);
        }
        elements = newElements;
        start = 0;
        end = j;
    }
    // POST (size < elements.length()) && INV is OK

    //PRE element != null
    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity();
        elements[end] = element;
        end = next(end);

    }
    //POST size' = size + 1 && a[size] = element && a[i]' == a[i] (for i = 0...size - 1)

    //PRE size > 0
    public static Object element() {
        assert size() > 0;
        return elements[start];
    }
    //POST R = a[0] && a[i]' == a[i] (for i = 0...size - 1) && size' == size

    //PRE size > 0
    public static Object dequeue() {
        assert size() > 0;
        start = next(start);
        Object ans = elements[prev(start)];
        elements[prev(start)] = null;

        return ans;
    }
    //POST size' = size - 1 && a[i - 1]' = a[i]

    //PRE x != null
    public static void push(Object x) {
        assert x != null;

        ensureCapacity();
        elements[prev(start)] = x;
        start = prev(start);

    }
    //POST: size' = size + 1 && a[0]' = x && a[i]' = a[i - 1]

    //PRE: size > 0
    public static Object remove() {
        assert size() > 0;
        Object ans = elements[prev(end)];
        end = prev(end);
        return ans;
    }
    //POST size' = size - 1 && a[i] = a[i]' for i 0...size' - 1

    //PREV size > 0
    public static Object peek() {
        assert size() > 0;
        return elements[prev(end)];
    }

    // PRE OK
    public static void clear() {
        start = 0;
        end = 0;
        elements = new Object[2];
    }
    //POST size = 0


}