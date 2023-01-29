package dukes.util;

import dukes.task.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The util class containing the list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor of TaskList class. Initialise an empty list.
     * Used when Duke is initialised in a directory for the first time.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructor of TaskList class. Set a task list for Duke.
     * Used when Duke is started up (except for the first time).
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> searchTaskList(String pattern) {
        List<Task> output = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            Pattern thePattern = Pattern.compile(
                    pattern, Pattern.CASE_INSENSITIVE
            );
            Matcher matcher = thePattern.matcher(currTask.getTaskName());
            boolean matchFound = matcher.find();
            if (matchFound) {
                output.add(currTask);
            }
        }
        return output;
    }
}
