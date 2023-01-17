package storage;

import java.util.ArrayList;

import exception.InvalidArgumentException;

/**
 * Represents the to do list
 * @author clevon-w
 */
public class ToDoList {
    /**
     * Represents the to do list
     */
    private ArrayList<Task> toDoList;

    /**
     * Creates an empty ArrayList of Strings as an empty to do list
     */
    public ToDoList() {
        this.toDoList = new ArrayList<>();
    }

    /**
     * Adds a task into the to do list
     * @param newToDo The new task to be added
     */
    public void createToDo(Task newToDo) {
        this.toDoList.add(newToDo);
    }

    /**
     * Custom String representation of the to do list
     * @return Custom string representation
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Here are the tasks in your list:\n");
        if (toDoList.size() == 0) {
            return "There are currently no tasks in your to do list,\n\t create one now!";
        }
        for (int i = 0; i < toDoList.size(); i++) {
            res.append(String.format("\t %d.%s\n", i + 1, toDoList.get(i).toString()));
        }
        return res.toString().trim();
    }

    public Task mark(Integer i) {
        try {
            Task curr = this.toDoList.get(i - 1);
            curr.markAsDone();
            return curr;
        } catch (IndexOutOfBoundsException err) {
            throw new InvalidArgumentException("Make sure you enter an index of a task that exists in the list!");
        }
    }

    public Task unmark(Integer i) {
        try {
            Task curr = this.toDoList.get(i - 1);
            curr.unmarkAsDone();
            return curr;
        } catch (IndexOutOfBoundsException err) {
            throw new InvalidArgumentException("Make sure you enter an index of a task that exists in the list!");
        }
    }
}
