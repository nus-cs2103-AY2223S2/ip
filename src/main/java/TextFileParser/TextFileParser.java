package TextFileParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;
import Utilities.FileCreate;

public class TextFileParser {
    private static String folderPath = "data";
    private static String filePath = "data/data.txt";
    
    public static ArrayList<Task> readData() {
        ArrayList<Task> list = new ArrayList<>();

        try {
            File f = validateFile();
            Scanner s = new Scanner(f);
        
            while(s.hasNext()) {
                String unfilteredData = s.nextLine();
                String[] data = unfilteredData.split("\\|");
                Arrays.parallelSetAll(data, i -> data[i].trim());


                if (data[0].equals("T")) {
                    ToDo toDoTask = new ToDo(data[2]);
                    if (data[1].equals("1")) {
                        toDoTask.setStatus(true);
                    }
                    list.add(toDoTask);
                } else if (data[0].equals("D")) {
                    Deadline deadlineTask = new Deadline(data[2], data[3]);
                    if (data[1].equals("1")) {
                        deadlineTask.setStatus(true);
                    }
                    list.add(deadlineTask);
                } else if (data[0].equals("E")) {
                    Event eventTask = new Event(data[2], data[3], "");
                    if (data[1].equals("1")) {
                        eventTask.setStatus(true);
                    }
                    list.add(eventTask);
                } else {
                    System.out.println("Invalid input in data.txt");
                }
            }

            s.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        } 

        return list;
    }

    private static File validateFile() {
        File dataFolder = new File(folderPath);
        File data = new File(filePath);

        if(!dataFolder.exists()) {
            FileCreate.createFolder(Paths.get(folderPath));
        }


        if(!data.exists()) {
            return FileCreate.createFile(Paths.get(filePath));
        }

        return data;
    }
}
