package alfred.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import alfred.exceptions.AlfredException;

public class TaskList {

    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String getList() {
        int itemIndex = 1;
        StringBuilder listOfItems = new StringBuilder();
        for (Task item : tasks) {
            listOfItems.append(String.format("    %d. %s\n", itemIndex, item));
            itemIndex++;
        }
        String numTasks = tasks.size() == 1 ? "task" : "tasks";
        listOfItems.append(String.format("    You have %d %s in the list\n", tasks.size(), numTasks));
        return listOfItems.toString();
    }

    public String getList(LocalDate date) {
        int itemIndex = 1;
        StringBuilder listOfItems = new StringBuilder();
        for (Task item : tasks) {
            if (item.containsDate(date)) {
                listOfItems.append(String.format("    %d. %s\n", itemIndex, item));
                itemIndex++;
            }
        }
        String numTasks = itemIndex == 1 ? "task" : "tasks";
        listOfItems.append(String.format("    You have %d %s on %s in the list\n", itemIndex - 1, numTasks, date));
        return listOfItems.toString();
    }

    public void writeToFile(File dataFile) throws AlfredException {
        try {
            FileWriter fw = new FileWriter(dataFile);
            for (Task task : tasks) {
                fw.write(task.addToFile());
            }
            fw.close();
        } catch (IOException e) {
            throw new AlfredException(String.format("Something went wrong while"
                    + " saving the tasks, %s\n", e.getMessage()));
        }
    }

    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
