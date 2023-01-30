package duke.storage;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public final String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Reads from txt file at path to extract duke.task.Task list.
     * @return array of duke.task.Task
     * @throws DukeException if failed to load duke.task.Task List from file
     */
    public ArrayList<Task> load() throws DukeException {
        //if file is found successfully
        try {
            File fileTxt = new File(this.path);
            Scanner scanner = new Scanner(fileTxt);
            ArrayList<Task> taskArrayList = new ArrayList<>();
            while (scanner.hasNext()) {
                String savedTaskLine = scanner.nextLine();
                Task savedTask = Parser.parseFromFile(savedTaskLine);
                taskArrayList.add(savedTask);
            }
            return taskArrayList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Failed to load task file");
        }
    }

    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task t : taskList.getList()) {
            fw.write(t.toTXT() + System.lineSeparator());
        }
        fw.close();
    }

    public void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(path,true);
        fw.write(task.toTXT() + System.lineSeparator());
        fw.close();
    }

}
