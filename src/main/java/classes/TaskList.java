package classes;

import exceptions.IncorrectNoOfArgumentException;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private StringBuilder sb;
    private String message;

    public TaskList(ArrayList<String> fileTasks) {
        this.taskList = addAll(fileTasks);
        this.sb = new StringBuilder();
    }

    public String addTask(ArrayList<String> parsedTaskInfo, Storage file) {   // returns message that needs to be printed to user
        Task newTask = null;
        boolean hasIssue = false;
        switch (parsedTaskInfo.size()) {
        case 2:
            newTask = new ToDos(parsedTaskInfo.get(1));
            break;
        case 3:
            newTask = new ToDos(parsedTaskInfo.get(2));
            newTask.markAsDone();
            break;
        case 4:
            newTask = new Deadline(parsedTaskInfo.get(1), parsedTaskInfo.get(2), parsedTaskInfo.get(3));
            break;
        case 5:
            newTask = new Deadline(parsedTaskInfo.get(2), parsedTaskInfo.get(3), parsedTaskInfo.get(4));
            newTask.markAsDone();
            break;
        case 6:
            newTask = new Event(parsedTaskInfo.get(1), parsedTaskInfo.get(2), parsedTaskInfo.get(4),
                    parsedTaskInfo.get(3), parsedTaskInfo.get(5));
            break;
        case 7:
            newTask = new Event(parsedTaskInfo.get(2), parsedTaskInfo.get(3), parsedTaskInfo.get(5),
                    parsedTaskInfo.get(4), parsedTaskInfo.get(6));
            newTask.markAsDone();
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

    public String deleteTask(ArrayList<String> parsedTaskInfo, Storage file) {
        int taskNumber = Integer.parseInt(parsedTaskInfo.get(1));
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

    public String markTask(ArrayList<String> parsedTaskInfo, Storage file) {
        int taskNumber = Integer.parseInt(parsedTaskInfo.get(1));
        if ((taskNumber <= this.taskList.size()) && (taskNumber > 0)) {
            Task tempTask = this.taskList.get(taskNumber - 1);
            String oldTaskInfo = tempTask.getTaskInfo();
            sb.append("    ____________________________________________________________\n")
                    .append(tempTask.markAsDone())
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

    public String unmarkTask(ArrayList<String> parsedTaskInfo, Storage file) {
        int taskNumber = Integer.parseInt(parsedTaskInfo.get(1));
        if ((taskNumber <= this.taskList.size()) && (taskNumber > 0)) {
            Task tempTask = this.taskList.get(taskNumber - 1);
            String oldTaskInfo = tempTask.getTaskInfo();
            sb.append("    ____________________________________________________________\n")
                    .append(tempTask.markAsIncomplete())
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

    public String list() {  // returns message that needs to be printed to user
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

    private static ArrayList<Task> addAll(ArrayList<String> fileTasks) {
        String taskInfo;
        boolean hasCompleted = false;
        boolean hasIssue = false;
        ArrayList<Task> newTaskList = new ArrayList<>();
        for (int i = 0; i < fileTasks.size(); i++) {
            taskInfo = fileTasks.get(i);
            Task newTask = null;
            ArrayList<String> parsedTaskInfo;
            try {
                parsedTaskInfo = Parser.parse(taskInfo);
            } catch (IncorrectNoOfArgumentException e) {
                System.out.println(e);
                continue;
            }
            switch (parsedTaskInfo.size()) {
            case 2:
                newTask = new ToDos(parsedTaskInfo.get(1));
                break;
            case 3:
                newTask = new ToDos(parsedTaskInfo.get(2));
                newTask.markAsDone();
                break;
            case 4:
                newTask = new Deadline(parsedTaskInfo.get(1), parsedTaskInfo.get(2), parsedTaskInfo.get(3));
                break;
            case 5:
                newTask = new Deadline(parsedTaskInfo.get(2), parsedTaskInfo.get(3), parsedTaskInfo.get(4));
                newTask.markAsDone();
                break;
            case 6:
                newTask = new Event(parsedTaskInfo.get(1), parsedTaskInfo.get(2), parsedTaskInfo.get(4),
                        parsedTaskInfo.get(3), parsedTaskInfo.get(5));
                break;
            case 7:
                newTask = new Event(parsedTaskInfo.get(2), parsedTaskInfo.get(3), parsedTaskInfo.get(5),
                        parsedTaskInfo.get(4), parsedTaskInfo.get(6));
                newTask.markAsDone();
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
