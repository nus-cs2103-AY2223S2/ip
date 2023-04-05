package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class handles the reading from and writing to of hard disk storage for the chatbot. This class is responsible
 * for creating a directory path and file location to write the data stored by the bot should it not exist on startup.
 */
public class Storage {
    private final String PATHNAME;

    /**
     * Standard constructor for an instance of Storage.
     * @param path A string path indicating the directory to create the data file at.
     * @param taskList An instance of <code>TaskList</code> associated with the instance of <code>Duke</code> that
     *                 will be using this instance of Storage.
     */
    Storage(String path, TaskList taskList) {
        PATHNAME = path;
        try {
            Scanner sc = new Scanner(new File(PATHNAME));
            while (sc.hasNextLine()) {
                String[] tokens = sc.nextLine().split(",");
                taskList.parseEventFromFile(tokens);
            }
        } catch (FileNotFoundException ignored) {}
    }

    /**
     * Writes the current data in <code>taskList</code> instance to the file specified in PATHNAME.
     * This method overwrites all content in the destination file.
     * @param tasks An instance of <code>TaskList</code> that is associated with the instance of <code>Duke</code>
     *              calling this method.
     */
    void updateData(TaskList tasks) {
        assert(tasks != null);
        File file = new File(PATHNAME);
        file.getParentFile().mkdirs();
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                sb.append(task.asTokens()).append('\n');
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
