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
        ListOfTasks = new ArrayList<>();
        storage = new Storage(path);
        init();
    }

    /**
     * Gui add string.
     *
     * @param input the input
     * @return the string
     */
    public String gui_add(Task input) {
        try {
            String str;
            if (input.taskName.equals("") || input.taskName.isBlank()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
            } else {
                ListOfTasks.add(input);
                input.add();
                str = input.messageAdd + "\n Now you have " + ListOfTasks.size() + " tasks in the list";
                if (input instanceof ToDo) {
                    storage.write("T|" + input.done + "|" + ((ToDo) input).rawInput);
                } else if (input instanceof Deadlines) {
                    storage.write("D|" + input.done + "|" + ((Deadlines) input).rawInput);
                } else if (input instanceof Events) {
                    storage.write("E|" + input.done + "|" + ((Events) input).rawInput);
                } else {
                    System.out.println("Unspecific type");
                }

            }
            return str;
        } catch (DukeException e) {
            if (input instanceof ToDo)
                return "OOPS!!! The description of a todo cannot be empty.\n";
            else if (input instanceof Deadlines)
               return "OOPS!!! The description of a deadline cannot be empty.\n";
            else
                return "OOPS!!! The description of a event cannot be empty.\n";
        }
    }

    /**
     * Gui display all string.
     *
     * @return the string
     */
    public String gui_displayAll() {
        StringBuilder str = new StringBuilder(Parser.THE_TASKS_IN_YOUR_LIST);
        for (int x = 0; x < ListOfTasks.size(); x++) {
            ListOfTasks.get(x).display();
            str.append(x + 1).append(". ").append(ListOfTasks.get(x).messageDisplay).append("\n");
        }
        return str.toString();
    }

    /**
     * Gui mark string.
     *
     * @param index the index
     * @return the string
     */
    public String gui_mark(int index) {
        try {
            String str;
            Task temp = ListOfTasks.get(index);
            temp.marked();
            str = temp.messageMarked;
            storage.markAt(index);
            return str;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Index");
        }
        return null;
    }

    /**
     * Gui unmark string.
     *
     * @param index the index
     * @return the string
     */
    public String gui_unmark(int index) {
        try {
            String str;
            Task temp = ListOfTasks.get(index);
            temp.unmarked();
            str = temp.messageUnmarked;
            storage.unmarkAt(index);
            return str;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Index");
        }
        return null;
    }

    /**
     * Delete.
     * delete task at given index
     *
     * @param index the index
     * @return the string
     */
    public String gui_delete(int index) {
        try {
            String str;
            Task temp = ListOfTasks.get(index);
            temp.delete();
            ListOfTasks.remove(index);
            str = temp.messageDelete + "\n Now you have " + ListOfTasks.size() + " tasks in the list";
            storage.deteleAt(index);
            return str;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Index");
        }
        return null;
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
        storage.writeAll();
    }

    /**
     * Gui find string.
     *
     * @param key the key
     * @return the string
     */
    public String gui_find(String key) {
        int[] indexes = new int[ListOfTasks.size()];
        int count = 0;
        StringBuilder output = new StringBuilder();
        for (int x = 0; x < ListOfTasks.size(); x++) {
            if (ListOfTasks.get(x).taskName.contains(key)) {
                indexes[count] = x;
                count++;
            }
        }
        for (int x = 0; x < count; x++) {
            ListOfTasks.get(indexes[x]).display();
            output.append(x + 1).append(".").append(ListOfTasks.get(indexes[x]).messageDisplay).append("\n");
        }
        return Parser.FIND_MESSAGE + output;
    }


}
