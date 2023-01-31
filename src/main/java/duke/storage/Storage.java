package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Class for handling storing of tasks.
 */
public class Storage {

    /**
     * Constructor method for Storage.
     */
    public Storage() {
    }

    /**
     * Method to load data from hard drive.
     *
     * @param taskList TaskList to contain list of tasks loaded from hard drive.
     * @throws IOException If there is an error retrieving the file.
     */
    public void loadData(TaskList taskList) throws IOException {
        try {
            Scanner in = new Scanner(Paths.get("data", "duke.txt"));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                assert line != null : "Line is null";
                parse_data(line, taskList);
            }
        } catch (IOException e) {
            File file = new File("data/duke.txt");
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(file);
            fw.close();
        }
    }

    /**
     * Method for writing String to file on hard drive.
     *
     * @param newData data to be written.
     * @throws IOException If there is an error retrieving the file.
     */
    public void writeToData(String newData) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        fw.write(newData);
        fw.close();
    }

    /**
     * Method for parsing the data from the file on hard drive.
     *
     * @param line line to be parsed.
     * @param taskList TaskList to contain list of tasks loaded.
     */
    public void parse_data(String line, TaskList taskList) {
        String[] parts = line.split("\\|");
        if (parts[0].trim().equals("[T]")) {
            taskList.initAdd(new ToDo(parts[2].trim(), parts[1].trim().equals("1")));
        } else if (parts[0].trim().equals("[D]")) {
            taskList.initAdd(new Deadline(parts[2].trim(), parts[3].trim(), parts[1].trim().equals("1")));
        } else if (parts[0].trim().equals("[E]")) {
            String[] fromAndTo = parts[3].trim().split("-");
            taskList.initAdd(new Event(parts[2].trim(), fromAndTo[0], fromAndTo[1], parts[1].trim().equals("1")));
        }
    }
}
