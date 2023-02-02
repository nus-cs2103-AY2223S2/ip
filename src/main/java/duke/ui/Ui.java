package duke.ui;

import duke.TaskList;
import duke.Task;

public class Ui {

    public void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void displayLine() {
        System.out.println(" _______________________________________________________________");
    }
    
    public void displayTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    public void displayAddTaskMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    public void displayDeleteTaskMessage(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    public void displayMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void displayUnmarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void displayGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void displayMsg(String output) {
        System.out.println(output);
    }

}
