package database;

import java.util.ArrayList;
import dukeexception.DatabaseException;

public class Database<T> {
    ArrayList<T> items = new ArrayList<T>();

    public void listItems() {
        int size = this.size();

        for (int i = 0; i < size; i++)
            System.out.println((i + 1) + ". " + this.getItem(i));

        System.out.println();
    }

    public void addItem(T item) {
        this.items.add(item);
    }

    public T getItem(int id) {
        return this.items.get(id);
    }

    public T deleteItem(int id) throws DatabaseException {
        if (id >= this.size()) {
            throw new DatabaseException("☹ OOPS!!! delete index does not exist");
        }

        T task = this.items.remove(id);
        
        return task;
    }

    public int size() {
        return this.items.size();
    }
}
