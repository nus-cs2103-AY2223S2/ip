package duke;

import java.util.Scanner;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.TaskList;

public class Ui {
    public void showLine() {
        System.out.println("--------------------------------");
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    public void showExit() {
        System.out.println("Bye~ Hope to see you next time! >v<");
    }

    public void showLoadingError() {
        System.out.println("Loading error: No saved task list found.");
    }

    // read in keyboard input
    public String readCommand() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }

    public void showError(String msg) {
        if (!msg.equals("Failed Command Generation")) {
            System.out.println(msg);
        }
    }

    public void addTodo(Todo t) {
        System.out.println("This todo has been added!");
        System.out.println("  " + t);
    }

    public void addDeadline(Deadline d) {
        System.out.println("This deadline had been added! Try to finish it early 0v0");
        System.out.println("  " + d);
    }

    public void addEvent(Event e) {
        System.out.println("This event has been added! Hope you will enjoy it :D");
        System.out.println("  " + e);
    }

    public void showCurrentTaskNo(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
    public void showList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println("Here are the current tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print((i + 1) + ".");
                System.out.println(tasks.get(i).toString());
            }
        }
    }

    // for update command
    public void showIdExceedsList(int index) {
        System.out.println("I cannot find task " + (index) + " as it exceeds the total tasks number");
    }

    public void markTask(TaskList tasks, int index) {
        System.out.println("Nice! Great job for completing this task:");
        System.out.println((tasks.get(index).toString()));
    }

    public void unmarkTask(TaskList tasks, int index) {
        System.out.println("This item is marked as not done yet");
        System.out.println((tasks.get(index).toString()));
    }

    public void deleteTask(TaskList tasks, int index) {
        System.out.println("This task is deleted from the list:");
        System.out.println("  " + (tasks.get(index).toString()));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list");
    }

    public void todoFormatAlert() {
        System.out.println("Adding new todo failed");
        System.out.println("The task name cannot be empty");
    }

    public void deadlineFormatAlert() {
        System.out.println("Adding new deadline failed");
        System.out.println("Please enter the deadline with format [name /ddmmyyyy time]");
    }

    public void eventFormatAlert() {
        System.out.println("Adding new event failed");
        System.out.println("Please enter the task with the format [name /ddMMyyyy HHmm /ddMMyyyy HMmm]");
    }

}