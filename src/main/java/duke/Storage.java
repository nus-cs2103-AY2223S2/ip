package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Utility class for saving and loading a list of tasks to and from a text file.
 */
public class Storage {
    private String storagePath;

    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Writes a String to the file indicated by storagePath
     *
     * @param data String to be written to the file indicated by storagePath
     * @throws IOException
     */
    public void save(String data) throws IOException {
        FileWriter fw = new FileWriter(storagePath);
        fw.write(data);
        fw.close();
    }

    /**
     * Adds all tasks in the file located at storagePath into taskList.
     *
     * @param taskList the list where tasks loaded from the file at storagePath are added to
     * @throws FileNotFoundException if the file at storagePath does not exist
     */
    public void read(TaskList taskList) throws FileNotFoundException {
        File f = new File(storagePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String taskString = s.nextLine();
            String[] parts = taskString.split("/");

            try {
                if (parts[0].equals("D") && parts.length == 4) {
                    taskList.add(Deadline.parseDeadlineStringArray(parts));
                } else if (parts[0].equals("E") && parts.length == 5) {
                    taskList.add(Event.parseEventStringArray(parts));
                } else if (parts[0].equals("T") && parts.length == 3) {
                    taskList.add(ToDo.parseToDoStringArray(parts));
                } else {
                    System.out.println("Failed to load a task from " + storagePath);
                }
            } catch (DateTimeParseException e) {
                // Don't do anything
            }
        }
    }
}
