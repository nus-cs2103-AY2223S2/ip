import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.nio.file.Path;
import java.util.regex.Pattern;


public class ToDoList {
    private ArrayList<Task> arr = new ArrayList<>(); //1-based index
    private int toDoCount;

    public ToDoList() {
        arr.add(new ToDoTask("0index")); //1-based index
        this.toDoCount = 0;
    }

    public int getToDoCount() {
        return this.toDoCount;
    }

    public Task getTask(int index) {
        return this.arr.get(index);
    }

    public void add(Task task) {
        ++this.toDoCount;
        arr.add(task);
    }

    public Task delete(int ind) throws DukeException {
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        Task rm = arr.get(ind);
        arr.remove(ind);
        --this.toDoCount;
        return rm;
    }


    public void unmarkTask(int ind) throws DukeException {
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        arr.get(ind).markNotDone();
    }

    public void markTask(int ind) throws DukeException {
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        arr.get(ind).markDone();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= this.toDoCount; i++) {
            output = output + i + "." + arr.get(i) + "\n";
        }
        return output;
    }
}
