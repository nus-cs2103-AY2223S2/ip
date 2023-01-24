package duke.storage;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import duke.task.*;
import duke.exceptions.InvalidDateException;

public class FileManagement {
    private File file;
    private String filePath;
    private String dataDirectory;

    public FileManagement() {
        this.dataDirectory = "./data/";
        this.filePath = this.dataDirectory + "duke.txt";
        this.file = new File(this.filePath);
        File directory = new File(this.dataDirectory);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void save(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (Task task : taskList.getTasks()) {
                writer.write(task.encode() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Task> retrieve() {
        Scanner scanner = null;
        List<Task> taskList = new ArrayList<>();
        try {
            scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String encoded = scanner.nextLine();
                taskList.add(this.decodeTask(encoded));
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (InvalidDateException e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
        return taskList;
    }


    private Task decodeTask(String task) throws InvalidDateException {
        String[] components = task.split(" ### ");
        String command = components[0];
        String toMark = components[1];
        String des = components[2];
        Task decoded = null;
        switch (command) {
            case "todo":
                decoded = new ToDo(des);
                break;
            case "deadline":
                decoded = new Deadline(des, components[3]);
                break;
            case "event":
                decoded = new Event(des, components[3], components[4]);
                break;
        }
        if (toMark.equals("true")) {
            decoded.markTask();
        }
        return decoded;
    }
}
