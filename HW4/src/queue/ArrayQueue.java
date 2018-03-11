package queue;


public class ArrayQueue extends AbstractQueue implements Queue{
    private int start = 0, end = 0;
    private Object[] elements = new Object[20];



    private int next(int x) {
        return (x + 1) % elements.length;
    }


    private void ensureCapacity() {
        if (next(end) != start)
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

    protected void pushImpl(Object element) {
        assert element != null;
        ensureCapacity();
        elements[end] = element;
        end = next(end);
    }


    protected Object peekImpl() {
        assert size() > 0;
        return elements[start];
    }


    protected void pop_front() {
        assert size() > 0;
        elements[start] = null;
        start = next(start);

    }

    protected void clearImpl() {
        start = 0;
        end = 0;
        elements = new Object[2];

    }

    protected ArrayQueue copyQueue(){
        ArrayQueue que = new ArrayQueue();
        que.start = start;
        que.end = end;
        que.size = size;
        que.elements = new Object[elements.length];
        System.arraycopy(elements, 0, que.elements, 0, elements.length);
        return que;
    }

}