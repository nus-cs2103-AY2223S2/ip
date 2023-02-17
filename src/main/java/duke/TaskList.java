package duke;

import java.util.ArrayList;

/**
 * Represents a list used for storing all task information on Duke.
 * <p></p>
 * Represents a Central Processing Unit (CPU) as well to perform the various commands by user.
 *
 * @author MrTwit99
 * @since 2023-02-02
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Returns a TaskList object that stores all tasks in an ArrayList of type Task
     *
     * @param fileTasks ArrayList of String type that contains all task information stored in the file that needs to
     *                  be added into Duke's TaskList upon boot up.
     */
    public TaskList(ArrayList<String> fileTasks) {
        this.taskList = addAll(fileTasks);
    }

    /**
     * Returns a string message of all the tasks that got added into the taskList from the hard disk.
     * <p></p>
     * This method <b>must</b> only be called during Duke's boot up.
     *
     * @param fileTasks ArrayList of String type that stores all tasks' descriptions and statuses obtained from the
     *                  hard disk.
     * @return String message of all tasks' descriptions and statues that got added into taskList from hard disk.
     */
    private static ArrayList<Task> addAll(ArrayList<String> fileTasks) {
        String taskInfo;
        boolean hasIssue = false;
        ArrayList<Task> newTaskList = new ArrayList<>();
        for (int i = 0; i < fileTasks.size(); i++) {
            taskInfo = fileTasks.get(i);
            Task newTask;
            ArrayList<String> parsedInfoList;
            parsedInfoList = Parser.parse(taskInfo);
            newTask = getTask(parsedInfoList); // creates a new task
            if (newTask == null) {
                hasIssue = true;
            }
            if (!hasIssue) {
                newTaskList.add(newTask);
            }
        }
        return newTaskList;
    }

    /**
     * Creates a new Task object (ToDos, Deadline or Event) based on the parsed command information.
     *
     * @param parsedCmd ArrayList of String type containing the parsed command.
     * @return Task object which can be one of the following types: ToDos, Deadline or Event.
     */
    private static Task getTask(ArrayList<String> parsedCmd) {
        Task newTask = null;
        switch (parsedCmd.size()) {
        case 2: // new ToDos task
            newTask = new ToDo(parsedCmd.get(1));
            break;
        case 3: // new ToDos (COMPLETED) task
            newTask = new ToDo(parsedCmd.get(2));
            newTask.setDone();
            break;
        case 4: // new Deadline task
            newTask = new Deadline(parsedCmd.get(1), parsedCmd.get(2), parsedCmd.get(3));
            break;
        case 5: // new Deadline (COMPLETED) task
            newTask = new Deadline(parsedCmd.get(2), parsedCmd.get(3), parsedCmd.get(4));
            newTask.setDone();
            break;
        case 6: // new Event task
            newTask = new Event(parsedCmd.get(1), parsedCmd.get(2), parsedCmd.get(4),
                    parsedCmd.get(3), parsedCmd.get(5));
            break;
        case 7: // new Event (COMPLETED) task
            newTask = new Event(parsedCmd.get(2), parsedCmd.get(3), parsedCmd.get(5),
                    parsedCmd.get(4), parsedCmd.get(6));
            newTask.setDone();
            break;
        default:
            break;
        }
        return newTask;
    }

    /**
     * Returns an ArrayList of Task type, the taskList, containing all tasks in Duke. Getter function for taskList.
     *
     * @return ArrayList of Task type, containing all tasks in Duke.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Updates Duke's taskList to the given input taskList. Setter function for taskList.
     *
     * @param taskList ArrayList of Task type, containing tasks.
     */
    public void updateTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
