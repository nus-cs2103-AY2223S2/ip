package roody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import roody.exceptions.RoodyException;

/**
 * Represents the data storage handler
 */
public class Storage {
    /** filepath to data file */
    private String filePath;
    /** filepath to folder */
    private String defaultFolderPath = "./data";

    /**
     * Creates a Storage handler with specified filepath to save data.
     * @param filePath The filepath to a file to save data on.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns information from the data file.
     * If folder not present, creates a new folder.
     * If file not present, creates a new file.
     * @return Tasks.
     */
    public ArrayList<Task> loadFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File data = new File(filePath);
            File folder = new File(defaultFolderPath);
            // check if file exists
            if (!folder.exists()) {
                folder.mkdir();
            }
            if (data.createNewFile()) {
                //System.out.println("File created: " + data.getName());
            } else {
                //System.out.println("File already exists.");
                Scanner s = new Scanner(data);
                String task = "";
                while (s.hasNextLine()) {
                    task = s.nextLine();
                    String[] inputs = task.split("\\|", 5);
                    //System.out.println(Arrays.toString(inputs));
                    // filter by task
                    Task temp;
                    if (inputs[2].equals("T")) {
                        temp = new Todo(inputs[0]);
                    } else if (inputs[2].equals("D")) {
                        temp = new Deadline(inputs[0], LocalDate.parse(inputs[3]));
                    } else if (inputs[2].equals("E")) {
                        temp = new Event(inputs[0], LocalDate.parse(inputs[3]), LocalDate.parse(inputs[4]));
                    } else {
                        new RoodyException("Error loading text");
                        s.close();
                        return null;
                    }
                    if (inputs[1].equals("true")) {
                        temp.setDone();
                    }
                    list.add(temp);
                }
                s.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Saves Task information, if any, into a preset file.
     * @param list The ArrayList of Tasks to be saved.
     */
    public void saveFile(ArrayList<Task> list) {
        ArrayList<String> buffer = new ArrayList<>();
        Path output = Paths.get(filePath);
        for (Task t : list) {
            buffer.add(t.saveTask());
        }
        try {
            Files.write(output, buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
