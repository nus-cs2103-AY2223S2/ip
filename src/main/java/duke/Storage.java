package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Storage {

    String filePath;
    String directoryPath;

    public Storage(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
    }

    TaskList<Task> readFile() throws NeroException {
        TaskList<Task> taskList = new TaskList<Task>();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] cleanedInputs = line.split(" \\| ");
                boolean isDone = (cleanedInputs[1].equals("0")) ? false : true;
                if (cleanedInputs[0].toUpperCase().equals("T")) {
                    taskList.addTask(new ToDo(cleanedInputs[2], isDone));
                } else if (cleanedInputs[0].toUpperCase().equals("D")) {
                    taskList.addTask(new Deadline(cleanedInputs[2], isDone, cleanedInputs[3]));
                } else if (cleanedInputs[0].toUpperCase().equals("E")) {
                    taskList.addTask(new Event(cleanedInputs[2], isDone, cleanedInputs[3]));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new NeroException("File was not found!");
        }
        return taskList;
    }

    void saveFile(TaskList<Task> taskList) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                fw.write(taskList.get(i).toSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("The file was not found!");
        }
    }
}
