package util;

import task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WriteToFile implements Serializable {
    private static final String FILEPATH = "src/main/java/data/UserTasks.txt";

    //Credit to @Junyi00 for the simple and easy to understand serialisation method for level-7
    public void saveTasksToFile(TaskManager taskManager) {
        try{
            File file = new File(FILEPATH);
            if(!file.isFile() && !file.isDirectory()) {
                System.out.println("File or folder not found!");
                System.out.println("Please create the file or folder.");
            }
            FileWriter fw = new FileWriter(file, true);
            ArrayList<Task> taskArr = taskManager.getTaskArr();

            for(int i = 0; i < taskArr.size(); i++) {
                fw.write(taskArr.get(i).serialise());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
//            System.out.println("IO Error Occurred");
        }
    }

    public int loadDataToArrayList(TaskManager taskManager) {
        File file = new File(FILEPATH);
        if(!file.isFile()) {
            return -1;
        }

        try {
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String data = s.next();
                String id = data.split(",")[0];
                Task task;

                switch (id) {
                case "Todo":
                    task = ToDo.deserialise(data);
                    break;
                case "Event":
                    task = Event.deserialise(data);
                    break;
                case "Dead":
                    task = Deadline.deserialise(data);
                    break;
                default:
                    task = Task.deserialise(data);
                    break;
                }

                taskManager.addTaskToList(task);
            }
        } catch (FileNotFoundException e) {
            return -1;
        } catch (DukeException e) {
            return 0;
        }
        return 0;
    }

}
