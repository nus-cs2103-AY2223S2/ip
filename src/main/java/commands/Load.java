package commands;
import tasks.Task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Load implements Command{
    private ArrayList<Task> destination;

    public Load(ArrayList<Task> destination)  {
        this.destination = destination;
    }

    public String execute() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/save.txt"))) {
            String task;
            int count = 0;
            while ((task = reader.readLine()) != null) {
                // process the line.
                String[] taskArgs = task.split(",");
                switch (taskArgs[0]) {
                    case "E": {

                    }
                    case "D": {

                    }
                    case "T": {

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
