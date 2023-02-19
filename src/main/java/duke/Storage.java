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
    private static final String SPECIFIC_FILE_PATH = "/text.txt";
    private static final String REGEX = "~";
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_COMPLETION_STATUS_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int TASK_DEADLINE_INDEX = 3;
    private static final int TASK_START_TIME_INDEX = 3;
    private static final int TASK_END_TIME_INDEX = 4;

    public Storage(String filePath) {
        this.fileDirectory = new File(filePath);
        this.file = new File(filePath + Storage.SPECIFIC_FILE_PATH);
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
        assert !task.isEmpty() : "Empty task!";
        String[] taskDescription = task.split(Storage.REGEX);
        switch (taskDescription[Storage.TASK_TYPE_INDEX]) {
        case "T":
            return new ToDo(taskDescription[Storage.TASK_DESCRIPTION_INDEX],
                    getCompletionStatus(taskDescription[Storage.TASK_COMPLETION_STATUS_INDEX]));
        case "D":
            return new Deadline(taskDescription[Storage.TASK_DESCRIPTION_INDEX],
                    getCompletionStatus(taskDescription[Storage.TASK_COMPLETION_STATUS_INDEX]),
                    taskDescription[Storage.TASK_DEADLINE_INDEX]);
        default:
            return new Event(taskDescription[Storage.TASK_DESCRIPTION_INDEX],
                    getCompletionStatus(taskDescription[Storage.TASK_COMPLETION_STATUS_INDEX]),
                    taskDescription[Storage.TASK_START_TIME_INDEX],
                    taskDescription[Storage.TASK_END_TIME_INDEX]);
        }
        //todo: better logic here
    }

    private boolean getCompletionStatus(String status) {
        return status.equals("X") ? true : false;
    }

    public void update(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).generateStorageText() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            this.ui.printExceptionMessage(new DukeException(Storage.UPDATE_ERROR + ex.getMessage()));
        }
    }
}
