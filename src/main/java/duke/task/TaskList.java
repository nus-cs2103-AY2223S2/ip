package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.WrongCommandException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void printList() {
        System.out.println("Here are some tasks in your list:");
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            System.out.println(i + "." + (taskList.get(i - 1)).toString());
        }
    }

    public void markTask(String taskStr) {
        int taskNum = Integer.parseInt(taskStr) - 1;
        Task originalTask =  taskList.get(taskNum);
        originalTask.markTask();
        System.out.println("Nice! I've marked this task as done: \n  " + originalTask);
    }

    public void unmarkTask(String taskStr) {
        int taskNum = Integer.parseInt(taskStr) - 1;
        Task originalTask =  taskList.get(taskNum);
        originalTask.unmarkTask();
        System.out.println("Ok, I've marked this task as not done yet: \n  " + originalTask);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void printAddComment(Task task) {
        System.out.println("Got it. I've added this task: \n  " + task +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public void deleteTask(String taskStr) {
        int taskNum = Integer.parseInt(taskStr) - 1;
        Task taskToRemove = taskList.get(taskNum);
        String removedTaskStr = taskToRemove.toString();
        taskList.remove(taskNum);
        System.out.println("Noted. I've removed this task: \n  " + removedTaskStr +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public void emptyDes(String input) throws EmptyDescriptionException {
        if ((input.equals("todo")) || (input.equals("deadline")) || (input.equals("event"))) {
            throw new EmptyDescriptionException();
        }
    }

    public void wrongCommand(String command) throws WrongCommandException {
        if (!(command.equals("mark") || command.equals("unmark") || command.equals("todo") ||
                command.equals("deadline") || command.equals("event") || command.equals("delete"))) {
            throw new WrongCommandException();
        }
    }



}
