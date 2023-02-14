package util;

import duke.DeadlineTask;
import duke.Event;
import duke.Task;
import duke.ToDo;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * TaskList class to help manage Tasks.
 * @author Merrick
 */
public class TaskList {
    private static final int TASK_TYPE = 0;
    private static final int TASK_NAME = 1;
    private static final int IS_DONE = 2;
    protected ArrayList<Task> taskList = new ArrayList<>();
    protected BufferedReader br;

    /**
     * Empty constructor for TaskList.
     */
    public TaskList() {}

    /**
     * Constructor of TaskList with tasks loaded from storage.
     * @param br Saved-data loaded stored in BufferedReader object.
     * @throws DukeException If data is unable to be read from BufferedReader object br.
     */
    public TaskList(BufferedReader br) throws DukeException {
        this.br = br;
        this.load();
    }

    /**
     * Adds a new task to be tracked by TaskList.
     * <br>
     * Displays the number of tasks after addition of task.
     * @param task Task object to be tracked.
     */
    public String addTask(Task task) {
        taskList.add(task);
        return String.format("Got it. I've added this task:\n" + "       %s\n" + this.numTasks() + "%n", task);
    }

    /**
     * Displays the tasks being tracked by TaskList.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int counter = 1;
        for (Task t : taskList) {
            sb.append(counter + ". " + t.toString() + "\n");
            counter++;
        }
        if (counter == 1) {
            return "Nothing at the moment!";
        }
        return sb.toString();
    }

    /**
     * Displays the tasks in the TaskList that contains the keyword specified.
     * @param command Keyword to check with the tasks in the TaskList.
     */
    public String findTasks(String command) {
        String[] keyword = command.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (Task t: taskList) {
            // Iterate through the ArrayList to find if a Task matches
            if (t.containsKeyword(keyword[1])) {
                if (counter == taskList.size()) {
                    sb.append(counter + ". " + t.toString());
                    break;
                }
                sb.append(counter + ". " + t.toString() + "\n");
                counter++;
            }
        }
        return sb.toString();
    }

    /**
     * Loads the tasks stored in the BufferedReader object into the task list.
     * @throws DukeException If IOException from the BufferedReader object is encountered.
     */
    @SuppressWarnings({"checkstyle:RightCurly", "checkstyle:MissingSwitchDefault"})
    public void load() throws DukeException {
        try {
            while (true) {
                String taskLine = br.readLine();
                if (taskLine == null) {
                    break;
                }
                String[] taskArr = taskLine.split("\\|");
                String taskType = taskArr[TASK_TYPE];
                switch (taskType) {
                case "T":
                    boolean completion = Boolean.parseBoolean(taskArr[IS_DONE]);
                    ToDo t = new ToDo(taskArr[TASK_NAME], completion);
                    this.taskList.add(t);
                    break;
                case "D":
                    LocalDateTime deadline = LocalDateTime.parse(taskArr[3]);
                    DeadlineTask d = new DeadlineTask(taskArr[TASK_NAME], deadline,
                            Boolean.parseBoolean(taskArr[IS_DONE]));
                    this.taskList.add(d);
                    break;
                case "E":
                    LocalDateTime start = LocalDateTime.parse(taskArr[3]);
                    LocalDateTime end = LocalDateTime.parse(taskArr[4]);
                    Event e = new Event(taskArr[TASK_NAME], start, end, Boolean.parseBoolean(taskArr[IS_DONE]));
                    this.taskList.add(e);
                    break;
                default:
                    throw new DukeException("Not a valid Task Type!");
                }
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException("Unable to read from file, creating a new file");
        }
    }

    /**
     * Outputs the String to be saved in storage duke.txt file.
     * @return String representation of the tasks currently being tracked to be saved.
     */
    public String saveTaskList() {
        StringBuilder sb = new StringBuilder();
        for (Task t: taskList) {
            sb.append(t.saveTaskString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Sets completion status or delete tasks for Tasks being tracked by TaskList.
     * @param command Input from the User.
     * @throws DukeException If the input command or task number is invalid.
     */
    public String manageTask(String command) throws DukeException {
        String[] input = command.split(" ");
        if ((input.length != 2)) {
            throw new DukeException(input[0]);
        }
        int taskNumber = Integer.parseInt(input[1]) - 1;
        if (taskNumber >= taskList.size() || (taskNumber < 0)) { //negative and out of range is invalid
            throw new DukeException("bounds");
        }
        if (input[0].equals("delete")) {
            Task task = taskList.remove(taskNumber);
            return String.format("Noted. I've removed this task:\n       %s\n%s%n", task, numTasks());
        } else {
            boolean completion = input[0].equals("mark");
            Task task = taskList.get(taskNumber);
            return task.setCompletion(completion);
        }
    }

    /**
     * Outputs the number of tasks being tracked.
     * @return String representation the tasks tracked by TaskList.
     */
    public String numTasks() {
        return String.format("Now you have %d tasks in the list", taskList.size());
    }

    /**
     * Snoozes the task specified.
     * @param command Action input from the user.
     * @return New deadline of the task specified.
     * @throws DukeException Index of task is invalid
     */
    @SuppressWarnings("checkstyle:OperatorWrap")
    public String snoozeTask(String command) throws DukeException {
        // "snooze 1 /day 10 /hour 10 /minutes 20"
        ArrayList<String> input = new ArrayList<>(Arrays.asList(command.split(" ")));
        int dayIndex = input.indexOf("/day");
        int hourIndex = input.indexOf("/hour");
        int minutesIndex = input.indexOf("/minutes");

        if (dayIndex == -1 || hourIndex == -1 || minutesIndex == -1) {
            String output = "Usage should be:\n snooze {taskNumber} /day {days} /hour {hour} /minutes {minutes}\n";
            throw new DukeException(output);
        }
        int taskNumber = Integer.parseInt(input.get(1)) - 1;
        int days = Integer.parseInt(input.get(dayIndex + 1));
        int hours = Integer.parseInt(input.get(hourIndex + 1));
        int minutes = Integer.parseInt(input.get(minutesIndex + 1));

        Task task = taskList.get(taskNumber);

        return task.snoozeDeadline(days, hours, minutes);
    }
}
