package utility.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import tasklist.task_types.Deadline;
import tasklist.task_types.Event;
import tasklist.task_types.Task;
import tasklist.task_types.ToDo;

public class Storage {
    private static final String folderPath = "src/main/java/utility/storage";
    private static final String filePath = "src/main/java/utility/storage/data.txt";

    public static ArrayList<Task> readData() {
        ArrayList<Task> list = new ArrayList<>();

        try {
            File f = validateFile();
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return list;
    }

    public static void writeData(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).toTextString() + System.lineSeparator());
            }
            System.out.println(String.format("%d task(s) saved successfully.", list.size()));
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static File validateFile() {
        File dataFolder = new File(folderPath);
        File data = new File(filePath);

        if (!dataFolder.exists()) {
            FileCreate.createFolder(Paths.get(folderPath));
        }

        if (!data.exists()) {
            return FileCreate.createFile(Paths.get(filePath));
        }

        return data;
    }
}
