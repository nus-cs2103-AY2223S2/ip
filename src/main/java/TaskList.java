//import java.util.HashMap;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
    private ArrayList<Task> taskList;
    private int taskCount;

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
    public String addTask(String str) {
        taskCount++;
        Task newTask = new ToDo(str);
        this.taskList.add(newTask);
        return addTaskMessage(newTask);
    }
    public String addTask(String task, String deadline) {
        taskCount++;
        Task newTask = new Deadline(task, deadline);
        this.taskList.add(newTask);
        return addTaskMessage(newTask);
    }

    public String addTask(String task, String from, String to) {
        taskCount++;
        Task newTask = new Event(task, from, to);
        this.taskList.add(newTask);
        return addTaskMessage(newTask);
    }
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
}
