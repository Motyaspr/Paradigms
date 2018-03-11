package queue;

public class LinkedQueue extends AbstractQueue implements Queue {
    private class Node{
        Object value;
        Node next;

        private Node(Object value){
            assert value != null;
            this.value = value;
        }
    }

    private Node start;
    private Node end;

    protected void pop_front(){
        assert size != 0;
        start = start.next;

    }

    protected void pushImpl(Object element){
        assert element != null;
        Node last = end;
        end = new Node(element);
        if (size == 0)
            start = end;
        else
            last.next = end;


    }

    protected void clearImpl(){
        start = null;
        end = null;
    }

    protected Object peekImpl(){
        assert start != null;
        return start.value;
    }

    protected LinkedQueue copyQueue(){
        LinkedQueue cop = new LinkedQueue();
        Node newStart = start;
        while(newStart != null){
            cop.enqueue(newStart.value);
            newStart = newStart.next;
        }
        return cop;
    }
}
