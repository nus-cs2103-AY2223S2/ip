package Duke;

import Duke.Commands.Tasks.Deadline;
import Duke.Commands.Tasks.Task;
import Duke.Commands.Tasks.ToDo;
import Duke.Commands.Tasks.Event;
import Duke.DukeException.DukeException;

import java.io.*;

/**
 * This class handles updating Duke's task list into a local file
 */
public class Storage {
    private final String filePath;
    private final File fileDirectory;
    private final File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileDirectory = new File(filePath);
        this.file = new File(filePath + "/text.txt");
    }

    public TaskList initialise() throws DukeException {
        try {
            fileDirectory.mkdirs();
            file.createNewFile();
        } catch (IOException ex) {
            throw new DukeException("Cannot initialise directory/file");
        }
        TaskList toDoList = new TaskList();
        try {
            toDoList = loadListFromFile();
        } catch (IOException ex) {
            throw new DukeException("Error loading toDoList from storage!");
        }
        return toDoList;
    }

    private TaskList loadListFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = br.readLine();
        TaskList toDoList = new TaskList();
        while (str != null){
            toDoList.add(getTask(str));
        }
        return toDoList;
    }

    private Task getTask(String task) {
        // index 0 is task type
        // index 1 is task completion status (done or not)
        // index 2 is task description
        // index 3 to 4 are task times
        String[] taskDescription = task.split("|");
        switch (taskDescription[0]) {
        case "T":
            return new ToDo(taskDescription[2], getCompletionStatus(taskDescription[1]));
        case "D":
            return new Deadline(taskDescription[2], getCompletionStatus(taskDescription[1]), taskDescription[3]);
        }
        return new Event(taskDescription[2], getCompletionStatus(taskDescription[1]),
                taskDescription[3], taskDescription[4]);
    }

    private boolean getCompletionStatus(String status) {
        return status.equals("1") ? true : false;
    }

    /*
    public void update() {}

    public TaskList load() {}

    void filter(String ... keywords) {

        String x = keywords[0];

    }
     */
}
