package dukes.util;

import dukes.command.*;
import dukes.task.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

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
