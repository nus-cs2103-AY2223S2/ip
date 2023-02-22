package commands;
import tasks.Task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Load implements Command{
    private ArrayList<Task> tasks;

    public Load(ArrayList<Task> tasks)  {
        this.tasks = tasks;
    }

    public String execute() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/save.txt"))) {
            String task;
            int count = 0;
            while ((task = reader.readLine()) != null) {
                // process the line.
                // assumes saved tasks are correct
                String[] taskArgs = task.split(",", 3);
                switch (taskArgs[0]) {
                    case "E": {
                        Command addTask = new ImportTask("event", taskArgs[1], taskArgs[2], tasks);
                        addTask.execute();
                        break;
                    }
                    case "D": {
                        Command addTask = new ImportTask("deadline", taskArgs[1], taskArgs[2], tasks);
                        addTask.execute();
                        break;
                    }
                    case "T": {
                        Command addTask = new ImportTask("todo", taskArgs[1], taskArgs[2], tasks);
                        addTask.execute();
                        break;
                    }
                    default:
                        System.out.println("Unrecognised task format");
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Completed.";
    }
}
