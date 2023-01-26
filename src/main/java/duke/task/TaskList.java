package duke.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a List of tasks
 */
public class TaskList implements List<Task>, Serializable {
    private final List<Task> tasks;

    private TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static TaskList fromIterable(Iterable<Task> tasks) {
        List<Task> lst = new ArrayList<Task>();
        tasks.forEach(lst::add);
        return new TaskList(lst);
    }
  
    @Override
    public void add(int index, Task element) {
        tasks.add(index, element);
    }

    @Override
    public boolean add(Task e) {
        return tasks.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends Task> c) {
        return tasks.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Task> c) {
        return tasks.addAll(index, c);
    }

    @Override
    public void clear() { 
        tasks.clear();
    }

    @Override
    public boolean contains(Object o) {
        return tasks.contains(o); 
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return tasks.containsAll(c);
    }

    @Override
    public Task get(int index) { 
        return tasks.get(index);
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty(); 
    }
    
    @Override
    public int indexOf(Object o) {
        return tasks.indexOf(o);
    }

    @Override
    public Iterator<Task> iterator() { 
        return tasks.iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return tasks.lastIndexOf(o);
    }

    @Override
    public ListIterator<Task> listIterator() {
        return tasks.listIterator();
    }

    @Override
    public ListIterator<Task> listIterator(int index) {
        return tasks.listIterator(index);
    }

    @Override
    public boolean remove(Object o) { 
        return tasks.remove(o);
    }
    
    @Override
    public Task set(int index, Task t) { 
        return tasks.set(index, t); 
    }

    @Override
    public Task remove(int index) {
        return tasks.remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return tasks.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return tasks.retainAll(c);
    }

    @Override
    public int size() { 
        return tasks.size(); 
    }

    @Override
    public List<Task> subList(int fromIndex, int toIndex) {
        return tasks.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return tasks.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return tasks.toArray(a);
    }
}
