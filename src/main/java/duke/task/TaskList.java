package duke.task;//import java.util.HashMap;
import duke.exception.DukeException;

import java.util.ArrayList;


public class TaskList {
    protected final String SAVE_DIR = "./data";
    protected final String SAVE_NAME = "/duke.txt";
    protected ArrayList<Task> taskList;
    protected int taskCount;

    public TaskList() {
        this.taskCount = 0;
        this.taskList = new ArrayList<>();
    }

    public int getCount() {
        return taskCount;
    }

    private String addTaskMessage(Task task) {
        return "Got it. I've added this task:\n     " + task + "\nNow you have " + this.taskCount + " tasks in the list";
    }

    //==== OVERLOADED METHOD addTask =====
    public String addTask(Task task) {
        this.taskList.add(task);
        taskCount++;
        return addTaskMessage(task);
    }
//
//    public String addTask(String str) {
//        duke.Task newTask = new ToDo(str);
//        this.taskList.add(newTask);
//        taskCount++;
//        return addTaskMessage(newTask);
//    }
//    public String addTask(String task, String deadline) throws DukeException {
//        duke.Task newTask = new Deadline(task, deadline);
//        this.taskList.add(newTask);
//        taskCount++;
//        return addTaskMessage(newTask);
//    }
//
//    public String addTask(String task, String from, String to) throws DukeException {
//        duke.Task newTask = new Event(task, from, to);
//        this.taskList.add(newTask);
//        taskCount++;
//        return addTaskMessage(newTask);
//    }
    //======================================

    private String deleteTaskMessage(Task task) {
        return "Noted. I've removed this task:\n     " + task + "\nNow you have " + this.taskCount + " tasks in the list";
    }

    public String deleteTask(int taskNumber) throws DukeException {
        try {
            Task deletedTask = this.taskList.get(taskNumber - 1);
            this.taskList.remove(taskNumber - 1);
            taskCount--;
            return deleteTaskMessage(deletedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    public String markTask(int taskNumber) throws DukeException {
        try {
            Task task = this.taskList.get(taskNumber - 1);
            task.markDone();
            return "Nice! I've marked this task as done: \n" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    public String unmarkTask(int taskNumber) throws DukeException {
        try {
            Task task = this.taskList.get(taskNumber - 1);
            task.unmarkDone();
            return "OK, I've marked this task as not done yet: \n" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    public String listTasks() {
        String result = "";
        result += "Here are the tasks in your list:\n";
        for (int i = 0; i < this.taskCount; i++) {
            result += String.format("%d. %s \n", i + 1, this.taskList.get(i));
        }
        return result;
    }

    public String encode() {
        String result = "";
        for (int i = 0; i < this.taskCount; i++) {
            result += String.format("%s\n", this.taskList.get(i).encode());
        }
        return result;
    }

    public void loadTasks(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.taskCount = taskList.size();
    }
}
