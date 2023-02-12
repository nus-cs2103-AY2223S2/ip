package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void mark(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        String message = "Nice! I've marked this task as done:";
        Ui.indentedPrintln(message);
        Ui.indentedPrintln("  " + task);
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
        String message = "OK, I've marked this task as not done yet:";
        Ui.indentedPrintln(message);
        Ui.indentedPrintln("  " + task);
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteTask(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        Ui.indentedPrintln("Noted. I've removed this task:");
        Ui.indentedPrintln("  " + task);
        Ui.indentedPrintln("Now you have " + tasks.size() + " tasks in the list.");

        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addTask(String command, Storage storage) {
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
            newTask = new Deadline(description, Ui.parseDateTime(by));
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
            newTask = new Event(description, Ui.parseDateTime(start), Ui.parseDateTime(end));
        }
        tasks.add(newTask);
        Ui.indentedPrintln("Got it. I've added this task:");
        Ui.indentedPrintln("  " + newTask);
        Ui.indentedPrintln("Now you have " + tasks.size() + " tasks in the list.");

        try {
            storage.addTaskToFile(newTask);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void searchTask(String str) {
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
        Ui.indentedPrintln("Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            Ui.indentedPrintln(indices.get(i) + ". " +results.get(i).toString());
        }
    }

    public void checkDuplicate() {
        int taskNumber = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (Task t : tasks) {
            if (map.get(t.toString()) != null) {
                Ui.indentedPrintln("There exist duplicate tasks! Tasks "
                        + map.get(t.toString()) + "and " + taskNumber + " are duplicates.");
                return;
            }
            map.put(t.toString(), taskNumber);
            taskNumber++;
        }
        Ui.indentedPrintln("There's no duplicate task :)");
    }
}
