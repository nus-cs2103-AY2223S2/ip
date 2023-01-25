package duke.command;

import duke.exceptions.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDo;
import duke.utilities.Parser;

import java.util.ArrayList;

/**
 * The type Task list.
 */
public class TaskList {
    /**
     * The List of tasks.
     */
    ArrayList<Task> ListOfTasks;
    /**
     * The Storage.
     */
    Storage storage;

    /**
     * Instantiates a new Task list.
     *
     * @param path the path
     */
    public TaskList(String path) {
        //default size
        ListOfTasks = new ArrayList<>(100);
        storage = new Storage(path);
        init();
    }

    /**
     * Add.
     * add Task
     * @param input the input
     */
    public void add(Task input) {
        try {
            if (input.task_name.equals("") || input.task_name.equals(" ")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
            } else {
                ListOfTasks.add(input);
                input.add();
                System.out.println(input.message_add + "\n Now you have " + ListOfTasks.size() + " tasks in the list");
                if (input instanceof ToDo) {
                    storage.write("T|" + input.done + "|" + ((ToDo) input).raw);
                } else if (input instanceof Deadlines) {
                    storage.write("D|" + input.done + "|" + ((Deadlines) input).raw);
                } else if (input instanceof Events) {
                    storage.write("E|" + input.done + "|" + ((Events) input).raw);
                } else {
                    System.out.println("Unspecific type");
                }
            }
        } catch (DukeException e) {
            if (input instanceof ToDo)
                System.out.println("OOPS!!! The description of a todo cannot be empty.\n");
            else if (input instanceof Deadlines)
                System.out.println("OOPS!!! The description of a deadline cannot be empty.\n");
            else
                System.out.println("OOPS!!! The description of a event cannot be empty.\n");
        }
    }

    /**
     * Display all.
     * Print out all Tasks in memory
     */
    public void displayAll() {
        System.out.println(Parser.displaylist);
        for (int x = 0; x < ListOfTasks.size(); x++) {
            ListOfTasks.get(x).display();
            System.out.println(x + 1 + ". " + ListOfTasks.get(x).message_display);
        }
    }

    /**
     * Mark.
     * Marks task at given index
     * @param index the index
     */
    public void mark(int index) {
        try {
            Task temp = ListOfTasks.get(index);
            temp.marked();
            System.out.println(temp.message_marked);
            storage.markAt(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Index");
        }
    }

    /**
     * Unmark.
     * unmarked task at given index
     * @param index the index
     */
    public void unmark(int index) {
        try {
            Task temp = ListOfTasks.get(index);
            temp.unmarked();
            System.out.println(temp.message_unmarked);
            storage.unmarkAt(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Index");
        }
    }

    /**
     * Delete.
     * delete task at given index
     * @param index the index
     */
    public void delete(int index) {
        try {
            Task temp = ListOfTasks.get(index);
            temp.delete();
            ListOfTasks.remove(index);
            System.out.println(temp.message_delete + "\n Now you have " + ListOfTasks.size() + " tasks in the list");
            storage.deteleAt(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Index");
        }
    }

    /**
     * Init.
     * Init and Load in Tasks from Txt file
     */
    void init() {
        storage.read();
        if (!storage.records.isEmpty()) {
            for (int x = 0; x < storage.records.size(); x++) {
                String[] tokens = storage.records.get(x).split("\\|");
                boolean status = Boolean.parseBoolean(tokens[1]);
                if (tokens[0].equals("T")) {
                    ListOfTasks.add(new ToDo(tokens[2], status));
                } else if (tokens[0].equals("D")) {
                    ListOfTasks.add(new Deadlines(tokens[2], status));
                } else {
                    ListOfTasks.add(new Events(tokens[2], status));
                }
            }
        }
    }

    /**
     * File write all.
     */
    public void file_writeAll() {
        storage.WriteAll();
    }


}
