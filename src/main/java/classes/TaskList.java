package classes;

import exceptions.IncorrectNoOfArgumentException;

import java.io.IOException;

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
    private StringBuilder sb;
    private String message;

    /**
     * Returns a TaskList object that stores all tasks in an ArrayList of type Task
     *
     * @param fileTasks ArrayList of String type that contains all task information stored in the file that needs to
     *                  be added into Duke's TaskList upon boot up.
     */
    public TaskList(ArrayList<String> fileTasks) {
        this.taskList = addAll(fileTasks);
        this.sb = new StringBuilder();
    }

    /**
     * Returns a string message generated by the creation of the new task.
     * <p></p>
     * This method helps to create a new task and add its information into the hard disk.
     *
     * @param parsedInfoList ArrayList of String type that contains parsed information about the task description and
     *                       status generated by Parser.parse().
     * @param file Duke's Storage object to allow file access.
     * @return String message of the new task's description and status.
     */
    public String addTask(ArrayList<String> parsedInfoList, Storage file) {   // returns message that needs to be printed to user
        Task newTask = null;
        boolean hasIssue = false;
        switch (parsedInfoList.size()) {
        case 2: // new ToDos task
            newTask = new ToDos(parsedInfoList.get(1));
            break;
        case 3: // new ToDos (COMPLETED) task
            newTask = new ToDos(parsedInfoList.get(2));
            newTask.setDone();
            break;
        case 4: // new Deadline task
            newTask = new Deadline(parsedInfoList.get(1), parsedInfoList.get(2), parsedInfoList.get(3));
            break;
        case 5: // new Deadline (COMPLETED) task
            newTask = new Deadline(parsedInfoList.get(2), parsedInfoList.get(3), parsedInfoList.get(4));
            newTask.setDone();
            break;
        case 6: // new Event task
            newTask = new Event(parsedInfoList.get(1), parsedInfoList.get(2), parsedInfoList.get(4),
                    parsedInfoList.get(3), parsedInfoList.get(5));
            break;
        case 7: // new Event (COMPLETED) task
            newTask = new Event(parsedInfoList.get(2), parsedInfoList.get(3), parsedInfoList.get(5),
                    parsedInfoList.get(4), parsedInfoList.get(6));
            newTask.setDone();
            break;
        default:
            hasIssue = true;
            break;
        }
        if (!hasIssue) {
            this.taskList.add(newTask);
            try {
                file.writeToFile(newTask.getTaskInfo() + "\n", this.taskList);
                sb.append("    ____________________________________________________________\n")
                        .append("    Got it. I've added this task to the list:\n")
                        .append("      ").append(newTask.getTaskInfoStatus())
                        .append("\n    Now you have ").append(this.taskList.size()).append(" tasks in the list.\n")
                        .append("    ____________________________________________________________\n");
                this.message = sb.toString();
                sb.setLength(0);
            } catch (IOException e) {
                this.message = "An unexpected error has occurred: " + e.getMessage();
            }
        }
        return this.message;
    }

    /**
     * Returns a string message generated by the deletion of a task.
     * <p></p>
     * This method aids in the removal of task from the taskList and the hard disk.
     *
     * @param parsedInfoList ArrayList of String type that contains parsed information about the task description and
     *                       status generated by Parser.parse().
     * @param file Duke's Storage object to allow file access.
     * @return String message of the deleted task's description and status.
     */
    public String deleteTask(ArrayList<String> parsedInfoList, Storage file) {
        int taskNumber = Integer.parseInt(parsedInfoList.get(1));
        if ((taskNumber <= this.taskList.size()) && (taskNumber > 0)) {
            Task tempTask = this.taskList.remove(taskNumber - 1);
            sb.append("    ____________________________________________________________\n")
                    .append("    Noted. I've removed this task:\n")
                    .append("      ").append(tempTask.getTaskInfoStatus())
                    .append("\n    Now you have ").append(this.taskList.size()).append(" tasks in the list.\n")
                    .append("    ____________________________________________________________\n");
            this.message = sb.toString();
            sb.setLength(0);
            try {
                file.writeToFile(tempTask.getTaskInfo(), "", taskNumber - 1, this.taskList);
            } catch (IOException e) {
                this.message = "An unexpected error has occurred: " + e.getMessage();
            }
        } else {
            sb.append("    ____________________________________________________________\n")
                    .append("    The task you are trying to delete is out of range! Try again!\n")
                    .append("    ____________________________________________________________\n");
            this.message = sb.toString();
            sb.setLength(0);
        }
        return this.message;
    }

    /**
     * Returns a string message of the task that was marked as complete.
     *
     * @param parsedInfoList ArrayList of String type that contains parsed information about the task description and
     *                       status generated by Parser.parse().
     * @param file Duke's Storage object to allow file access.
     * @return String message of the completed task's description and status.
     */
    public String markTask(ArrayList<String> parsedInfoList, Storage file) {
        int taskNumber = Integer.parseInt(parsedInfoList.get(1));
        if ((taskNumber <= this.taskList.size()) && (taskNumber > 0)) {
            Task tempTask = this.taskList.get(taskNumber - 1);
            String oldTaskInfo = tempTask.getTaskInfo();
            sb.append("    ____________________________________________________________\n")
                    .append(tempTask.setDone())
                    .append("\n    ____________________________________________________________\n");
            taskList.set(taskNumber - 1, tempTask);
            this.message = sb.toString();
            sb.setLength(0);

            try {
                file.writeToFile(oldTaskInfo, tempTask.getTaskInfo(), taskNumber - 1, taskList);
            } catch (IOException e) {
                this.message = "An unexpected error has occurred: " + e.getMessage();
            }
        } else {
            sb.append("    ____________________________________________________________\n")
                    .append("    The task you are trying to mark is out of range! Try again!\n")
                    .append("    ____________________________________________________________\n");
            this.message = sb.toString();
            sb.setLength(0);
        }
        return this.message;
    }

    /**
     * Returns a string message of the task that was marked as incomplete.
     *
     * @param parsedInfoList ArrayList of String type that contains parsed information about the task description and
     *                       status generated by Parser.parse().
     * @param file Duke's Storage object to allow file access.
     * @return String message of the incomplete task's description and status.
     */
    public String unmarkTask(ArrayList<String> parsedInfoList, Storage file) {
        int taskNumber = Integer.parseInt(parsedInfoList.get(1));
        if ((taskNumber <= this.taskList.size()) && (taskNumber > 0)) {
            Task tempTask = this.taskList.get(taskNumber - 1);
            String oldTaskInfo = tempTask.getTaskInfo();
            sb.append("    ____________________________________________________________\n")
                    .append(tempTask.setIncomplete())
                    .append("\n    ____________________________________________________________\n");
            taskList.set(taskNumber - 1, tempTask);
            this.message = sb.toString();
            sb.setLength(0);
            try {
                file.writeToFile(oldTaskInfo, tempTask.getTaskInfo(), taskNumber - 1, taskList);
            } catch (IOException e) {
                this.message = "An unexpected error has occurred: " + e.getMessage();
            }
        } else {
            sb.append("    ____________________________________________________________\n")
                    .append("    The task you are trying to unmark is out of range! Try again!\n")
                    .append("    ____________________________________________________________\n");
            this.message = sb.toString();
            sb.setLength(0);
        }
        return this.message;
    }

    /**
     * Returns a string message of all the tasks stored in the taskList.
     *
     * @return String message of all the tasks' descriptions and statuses in the taskList.
     */
    public String list() {
        sb.append("    ____________________________________________________________\n")
                .append("    Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            sb.append("    ").append(i + 1).append(".").append(this.taskList.get(i).getTaskInfoStatus())
                    .append("\n");
        }
        sb.append("    ____________________________________________________________\n");
        this.message = sb.toString();
        sb.setLength(0);
        return this.message;
    }

    /**
     * Returns a string message of all the tasks that have the corresponding substring as requested.
     *
     * @param subString String that is to be checked with all tasks and find tasks with this string.
     * @return String message of all task' descriptions and statuses in the taskList that have the corresponding
     * substring in its task information.
     */
    public String find(String subString) {
        int taskIndex = 1;
        sb.append("    ____________________________________________________________\n")
                .append("    Here are the matching tasks in your list:\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getTaskInfo().toLowerCase().contains(subString)) {
                sb.append("    ").append(taskIndex++).append(".").append(this.taskList.get(i).getTaskInfoStatus())
                        .append("\n");
            }
        }
        sb.append("    ____________________________________________________________\n");
        this.message = sb.toString();
        sb.setLength(0);
        return this.message;
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
            Task newTask = null;
            ArrayList<String> parsedInfoList;
            try {
                parsedInfoList = Parser.parse(taskInfo);
            } catch (IncorrectNoOfArgumentException e) {
                System.out.println(e);
                continue;
            }
            switch (parsedInfoList.size()) {
            case 2: // new ToDos task
                newTask = new ToDos(parsedInfoList.get(1));
                break;
            case 3: // new ToDos (COMPLETED) task
                newTask = new ToDos(parsedInfoList.get(2));
                newTask.setDone();
                break;
            case 4: // new Deadline task
                newTask = new Deadline(parsedInfoList.get(1), parsedInfoList.get(2), parsedInfoList.get(3));
                break;
            case 5: // new Deadline (COMPLETED) task
                newTask = new Deadline(parsedInfoList.get(2), parsedInfoList.get(3), parsedInfoList.get(4));
                newTask.setDone();
                break;
            case 6: // new Event task
                newTask = new Event(parsedInfoList.get(1), parsedInfoList.get(2), parsedInfoList.get(4),
                        parsedInfoList.get(3), parsedInfoList.get(5));
                break;
            case 7: // new Event (COMPLETED) task
                newTask = new Event(parsedInfoList.get(2), parsedInfoList.get(3), parsedInfoList.get(5),
                        parsedInfoList.get(4), parsedInfoList.get(6));
                newTask.setDone();
                break;
            default:
                hasIssue = true;
                break;
            }
            if (!hasIssue) {
                newTaskList.add(newTask);
            }
        }
        return newTaskList;
    }
}
