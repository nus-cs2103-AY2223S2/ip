package database;

import java.util.ArrayList;
import dukeexception.DatabaseException;

public class Database<T> {
    ArrayList<T> items = new ArrayList<T>();

    public void listItems() {
        int size = this.size();

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < size; i++)
            System.out.println(
                    (i + 1) + ". " + this.getItem(i));

        System.out.println();
    }

    public void addItem(T item) {
        this.items.add(item);

        System.out.println("Got it. I've added this task:");
        System.out.println(item);
        System.out.println("Now you have " + this.size() + " tasks in the list.\n");
    }

    public T getItem(int id) {
        return this.items.get(id);
    }

    public void deleteItem(int id) throws DatabaseException {
        if (id >= this.size()) {
            throw new DatabaseException("☹ OOPS!!! delete index does not exist");
        }

        T task = this.items.remove(id);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.size() + " tasks in the list.\n");
    }

    public int size() {
        return this.items.size();
    }
}
