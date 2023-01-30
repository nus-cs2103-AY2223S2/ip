package duke.functions;

import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void readDatabase(TaskList dl) {
        File f = new File(filePath);
        try {
            Scanner fileReader = new Scanner(f);
            System.out.println("Database loaded.");
            int index = -1;
            while (fileReader.hasNextLine()) {
                String input = fileReader.nextLine();
                String[] split = input.split("\\|");
                String cmd = split[0];
                String status = split[1];
                String taskName = split[2];
                index++;
                switch (cmd) {
                    case "T":
                        dl.insertToDo(taskName, true);
                        if (status.equals("X")) {
                            dl.mark(index);
                        }
                        break;
                    case "D":
                        String deadline = "a";
                        dl.insertDeadline(taskName, deadline, true);
                        if (status.equals("X")) {
                            dl.mark(index);
                        }
                        break;
                    case "E":
                        String time = split[3];
                        dl.insertEvent(taskName, time, true);
                        if (status.equals("X")) {
                            dl.mark(index);
                        }
                        break;
                    default:
                        break;
                }
            }
            fileReader.close();
            dl.toString();
        } catch (FileNotFoundException e) {
            this.initialiseDatabase();
        }
    }


    private void initialiseDatabase() {
        File f = new File(filePath);
        File dir = new File("duke.txt");
        dir.mkdir();
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating database.");
        }
    }

}
