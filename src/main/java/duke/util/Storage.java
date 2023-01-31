package duke.util;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import duke.util.TaskList;

public class Storage {

    HashMap<String, TaskList> database;

    public Storage() {
        this.database = new HashMap<String, TaskList>();
    }

    public static void saveProgress(TaskList taskList) {
        File savedFile = new File("MY_GRAND_PLAN.txt");
        System.out.println("[X] FILE CREATED");
        try {
            FileWriter myWriter = new FileWriter("MY_GRAND_PLAN.txt", true);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task currenttask = taskList.getTask(i);
                if(currenttask.isDone) {
                    myWriter.write(currenttask.nature + " | " + "X" + " | " + currenttask.action + currenttask.getAdditionalInfo() + '\n');
                } else {
                    myWriter.write(currenttask.nature + " | " + " " + " | " + currenttask.action + currenttask.getAdditionalInfo() + '\n');
                }
            }
            myWriter.close();
            System.out.println("[X] FINISHED WRITING");
        } catch (IOException e) {
            System.out.println("BEE BOO BOOP...");
        }
    }
    public static TaskList loadProgress(String link) {
        try {
            File previousProgress = new File("MY_GRAND_PLAN.txt");
            Scanner progressScanner = new Scanner(previousProgress);
            TaskList returnTaskList = new TaskList();
            while (progressScanner.hasNextLine()) {
                String data = progressScanner.nextLine();

            }
            progressScanner.close();
            return returnTaskList;
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    public TaskList getTaskList(String keyword) {
        if (this.database.containsKey(keyword)) {
            return this.database.get(keyword);
        } else {
            return new TaskList();
        }
    }

    public Storage addToStorage(String keyword, Task task) {
        if (this.database.containsKey(keyword)) {
            TaskList currentList = this.database.get(keyword);
            currentList = currentList.addTask(task);
            this.database.put(keyword, currentList);
        } else {
            TaskList newList = new TaskList();
            newList = newList.addTask(task);
            this.database.put(keyword, newList);
        }
        return this;
    }

    public Storage removeFromStorage(String keyword, Task task) {
        if (this.database.containsKey(keyword)) {
            TaskList currentList = this.database.get(keyword);
            int index = -1;
            for (int i = 0; i < currentList.getSize(); i++) {
                if (currentList.getTask(i).toString().equals(task.toString())) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                currentList = currentList.removeTask(index);
            }
            this.database.put(keyword, currentList);
        }
        return this;
    }
}