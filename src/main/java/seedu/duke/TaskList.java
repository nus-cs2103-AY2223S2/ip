package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void list(Ui ui) {
        ui.addToResponseMessage("Here are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            ui.addToResponseMessage(i + "." + tasks.get(i - 1));
        }
    }

    public void mark(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void unmark(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Task deleteTask(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.remove(taskNumber - 1);
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return task;
    }

    public void addTask(Task newTask, Storage storage) {
        /*
        int len = command.length();
        String description;
        Task newTask;
        if (command.substring(0, 4).equals("todo")) {

            description = command.substring(5);
            newTask = new Todo(description);

        } else if (command.substring(0, 8).equals("deadline")) {
            int indexOfBy = -1;
            for (int i = 0; i < len; i++) {
                if (command.charAt(i) == '/') {
                    indexOfBy = i;
                }
            }
            description = command.substring(9, indexOfBy - 1);
            String by = command.substring(indexOfBy + 4);
            newTask = new Deadline(description, Parser.parseDateTime(by));
        } else {
            int indexOfStart = -1, indexOfEnd = -1;
            for (int i = 0; i < len; i++) {
                if (command.charAt(i) == '/') {
                    if (indexOfStart == -1) {
                        indexOfStart = i;
                    } else {
                        indexOfEnd = i;
                    }
                }
            }
            description = command.substring(6, indexOfStart - 1);
            String start = command.substring(indexOfStart + 6, indexOfEnd - 1);
            String end = command.substring(indexOfEnd + 4);
            newTask = new Event(description, Parser.parseDateTime(start), Parser.parseDateTime(end));
        }
        */
        tasks.add(newTask);
        /* Delete this
        ui.indentedPrintln("Got it. I've added this task:");
        ui.indentedPrintln("  " + newTask);
        ui.indentedPrintln("Now you have " + tasks.size() + " tasks in the list.");
         */
        try {
            storage.addTaskToFile(newTask);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void searchTask(String str, Ui ui) {
        assert str != "" : "str is supposed to be non-empty";
        ArrayList<Task> results = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        int index = 1;
        for (Task task : tasks) {
            if (task.description.contains(str)) {
                results.add(task);
                indices.add(index);
            }
            index++;
        }
        ui.addToResponseMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            ui.addToResponseMessage(indices.get(i) + ". " +results.get(i).toString());
        }
    }
}
