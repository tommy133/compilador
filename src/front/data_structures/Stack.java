package front.data_structures;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private final List<T> elements;

    public Stack(){
        elements = new ArrayList<>();
    }


    public T Peek() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(elements.size() - 1);
    }

    public T Pop() {
        if (elements.isEmpty()) {
            return null;
        }
        T top = elements.get(elements.size() - 1);
        elements.remove(elements.size() - 1);
        return top;
    }

    public void Push(T element) {
        elements.add(element);
    }

    public T Get(int i){
        return elements.get(i);
    }

    public int Size() {
        return elements.size();
    }

    @Override
    public String toString() {
        return "Stack [elements=" + elements + "]";
    }

}
