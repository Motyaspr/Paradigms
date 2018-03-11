package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    protected abstract void pop_front();

    public Object dequeue() {
        Object result = element();
        pop_front();
        size--;
        return result;
    }

    protected abstract Object peekImpl();

    public Object element() {
        assert size > 0 : "queue is empty";
        return peekImpl();
    }

    protected abstract void pushImpl(Object x);

    public void enqueue(Object element){
        pushImpl(element);
        size++;
    }

    public int size(){
        return size;
    }

    protected abstract void clearImpl();

    public void clear(){
        size = 0;
        clearImpl();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected abstract AbstractQueue copyQueue();

    public AbstractQueue filter(Predicate<Object> predicate){
        AbstractQueue que = copyQueue();
        int sz = que.size;
        for (int i = 0; i < sz; i++){
            Object elem = que.dequeue();
            if (predicate.test(elem)){
                que.enqueue(elem);
            }

        }
        return que;
    }

    public AbstractQueue map(Function<Object, Object> func){
        AbstractQueue que = copyQueue();
        int sz = que.size;
        for (int i = 0; i < sz; i++){
            Object elem = que.dequeue();
            que.enqueue(func.apply(elem));
        }
        return que;
    }
}
