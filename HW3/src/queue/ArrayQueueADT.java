package queue;

public class ArrayQueueADT {
    // n >= 0 && a[i] != null(0 <= i < n)
    private int size = 0;
    private int start = 0, end = 0;
    private Object[] elements = new Object[20];

    //PRE OK
    public static int size(ArrayQueueADT Queue){
        return Queue.size;
    }
    //POST R = size && a[i]' = a[i] (for 0 <= i < size) && size' = size

    //PRE OK
    public static boolean isEmpty(ArrayQueueADT Queue){
        return Queue.size == 0;
    }
    //POST R = (size == 0) && a[i]' = a[i] (for 0 <= i < size) && size' = size

    //PRE (0 <= x < elements.length) && element.length != 0
    private static int next(ArrayQueueADT Queue, int x){
        return (x + 1) % Queue.elements.length;
    }
    // POST //POST R = (size == 0) && a[i]' = a[i] (for 0 <= i < size) && size' = size

    private static int prev(ArrayQueueADT Queue,int x){
        return (x - 1 + Queue.elements.length) % Queue.elements.length;
    }

    // PRE OK
    private static void ensureCapacity(ArrayQueueADT Queue) {
        if (size(Queue) < Queue.elements.length)
            return;
        int j = 0;
        Object[] newElements = new Object[2 * size(Queue) + 1];
        for (int i = Queue.start; i != Queue.end; i = next(Queue, i))
            newElements[j++] = Queue.elements[i];
        Queue.elements = newElements;
        Queue.start = 0;
        Queue.end = j;
    }
    // POST (size < elements.length()) && INV is OK

    //PRE element != null
    public static void enqueue(ArrayQueueADT Queue, Object element){
        assert element != null;
        Queue.size++;
        ensureCapacity(Queue);
        Queue.elements[Queue.end] = element;
        Queue.end = next(Queue, Queue.end);

    }
    //POST size' = size + 1 && a[size] = element && a[i]' == a[i] (for i = 0...size - 1)

    //PRE: size > 0
    public static Object remove(ArrayQueueADT Queue){
        assert Queue.size > 0;
        Object ans = Queue.elements[prev(Queue, Queue.end)];
        Queue.end = prev(Queue, Queue.end);
        Queue.size--;
        return ans;
    }
    //POST size' = size - 1 && a[i] = a[i]' for i 0...size' - 1

    //PRE x != null
    public static void push(ArrayQueueADT Queue, Object x){
        assert x != null;
        Queue.size++;
        ensureCapacity(Queue);
        Queue.elements[Queue.prev(Queue, Queue.start)] = x;
        Queue.start = prev(Queue, Queue.start);

    }
    //POST: size' = size + 1 && a[0]' = x && a[i]' = a[i - 1]

    //PREV size > 0
    public static Object peek(ArrayQueueADT Queue){
        assert Queue.size > 0;
        return Queue.elements[Queue.prev(Queue, Queue.end)];
    }
    //POST R = a[size - 1] size' = size && elements[i] = elements[i]' for i 0...size - 1


    //PRE size > 0
    public static Object element(ArrayQueueADT Queue){
        assert Queue.size > 0;
        return Queue.elements[Queue.start];
    }
    //POST R = a[0] && a[i]' == a[i] (for i = 0...size - 1) && size' == size

    //PRE size > 0.
    public static Object dequeue(ArrayQueueADT Queue){
        assert Queue.size > 0;
        Object ans = Queue.elements[Queue.start];
        Queue.elements[Queue.start] = null;
        Queue.start = next(Queue, Queue.start);
        Queue.size--;
        return ans;
    }
    //POST size' = size - 1 && a[i - 1]' = a[i]


    // PRE OK
    public static void clear(ArrayQueueADT Queue){
        Queue.size = 0;
        Queue.start = 0;
        Queue.end = 0;
        Object cop[] = new Object[2];
        Queue.elements = cop;
    }
    //POST size = 0


}