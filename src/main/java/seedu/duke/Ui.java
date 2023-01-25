package seedu.duke;

import seedu.duke.task.Task;
public class Ui {

    public Ui() {
    }

    public void welcome() {
        //Logo of Duke
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("File not found");
        System.out.println("Creating temporary Task List.");
    }

    public void markDisplay(TaskList tasks, Parser userParse) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).toString());
    }

    public void unmarkDisplay(TaskList tasks, Parser userParse) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).toString());
    }

    public void list(TaskList tasks) {
        int numbering = 1;
        for (int i = 0; i < tasks.counter; i++) {
            System.out.println(numbering + ". " + tasks.tasksList.get(i).toString());
            numbering++;
        }
    }

    public void addTodoMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.tasksList.get(tasks.counter - 1).toString());
        System.out.println("Now you have " + tasks.counter + " task(s) in the list.");
    }

    public void addDeadlineMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.tasksList.get(tasks.counter - 1).toString());
        System.out.println("Now you have " + tasks.counter + " task(s) in the list.");
    }

    public void addEventMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.tasksList.get(tasks.counter - 1).toString());
        System.out.println("Now you have " + tasks.counter + " task(s) in the list.");
    }

    public void deleteMessage(TaskList tasks, Task deleted) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deleted.toString());
        System.out.println("Now you have " + tasks.counter + " task(s) in the list.");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}
