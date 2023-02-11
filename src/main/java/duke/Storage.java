package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.commands.tasks.Deadline;
import duke.commands.tasks.Event;
import duke.commands.tasks.Task;
import duke.commands.tasks.ToDo;
import duke.dukeexception.DukeException;

/**
 * This class handles updating Duke's task list into a local file
 */
public class Storage {
    private final File fileDirectory;
    private final File file;
    private final Ui ui;
    private static final String INITIALIZATION_ERROR = "Unable to initialize file/directory, " +
            "see error message here: ";
    private static final String LOAD_ERROR = "Unable to load existing to-do list, " +
            "see error message here: ";
    private static final String UPDATE_ERROR = "Unable to update to-do list, " +
            "see error message here: ";

    public Storage(String filePath) {
        this.fileDirectory = new File(filePath);
        this.file = new File(filePath + "/text.txt");
        this.ui = new Ui();
    }

    public TaskList initialise() {
        try {
            fileDirectory.mkdirs();
            file.createNewFile();
        } catch (IOException ex) {
            this.ui.printExceptionMessage(new DukeException(Storage.INITIALIZATION_ERROR + ex.getMessage()));
            return new TaskList();
        }
        try {
            TaskList toDoList = loadListFromFile();
            return toDoList;
        } catch (IOException ex) {
            this.ui.printExceptionMessage(new DukeException(Storage.LOAD_ERROR + ex.getMessage()));
            return new TaskList();
        }
    }

    private TaskList loadListFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = br.readLine();
        TaskList toDoList = new TaskList();
        while (str != null) {
            toDoList.add(getTask(str));
            str = br.readLine();
        }
        br.close();
        return toDoList;
    }

    private Task getTask(String task) {
        // index 0 is task type
        // index 1 is task completion status (done or not)
        // index 2 is task description
        // index 3 to 4 are task times
        // todo: put those into constants
        String[] taskDescription = task.split("~");
        switch (taskDescription[0]) {
        case "T":
            return new ToDo(taskDescription[2], getCompletionStatus(taskDescription[1]));
        case "D":
            return new Deadline(taskDescription[2], getCompletionStatus(taskDescription[1]), taskDescription[3]);
        default:
            return new Event(taskDescription[2], getCompletionStatus(taskDescription[1]),
                    taskDescription[3], taskDescription[4]);
        }
    }

    private boolean getCompletionStatus(String status) {
        return status.equals("X") ? true : false;
    }

    public void update(TaskList tasks) {
        FileWriter fw;
        try {
            fw = new FileWriter(this.file);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).generateStorageText() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            this.ui.printExceptionMessage(new DukeException(Storage.UPDATE_ERROR + ex.getMessage()));
        }
    }
}
