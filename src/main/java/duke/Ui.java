package duke;

import java.util.Scanner;

import duke.tasks.Task;

/**
 * Represents all methods such as showLogo, showWelcome...
 */
public class Ui {
    private static final String Indentation = " ";
    private static final String Horizontal = "____________________________________________________________";

    /**
     * shows duke logo at the beginnig
     */
    public static void showLogo() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * shows welcome after printing logo
     */
    public static void showWelcome() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Hello! I'm Duke");
        System.out.println(Indentation + "What can I do for you?");
        System.out.println(Indentation + Horizontal);
    }

    /**
     * shows loading error
     */
    public void showLoadingError() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Loading error! please try again");
        System.out.println(Indentation + Horizontal);
    }

    /**
     * exits the program
     */
    public static void exit() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Bye. Hope to see you again soon!");
        System.out.println(Indentation + Horizontal);
    }

    /**
     * shows list details
     * @param task
     */
    public static void showList(TaskList task) {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Here are the tasks in your list:");

        for (int i = 0; i < task.size(); i++) {
            System.out.println(Indentation + (i + 1) + "." + task.get(i).toString());
        }

        System.out.println(Indentation + Horizontal);
    }

    /**
     * reads user's input
     * @return command
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * shows that task is marked as done
     * @param num
     * @param tasks
     */
    public static void done(String num, TaskList tasks) {
        int number = Integer.parseInt(num) - 1;
        //tasks.get(number).isDone = true;
        tasks.get(number).isDone = true;

        System.out.println(Indentation + Horizontal);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Indentation + tasks.get(number).toString());
        System.out.println(Indentation + Horizontal);
    }

    /**
     * shows that task is marked as not done
     * @param num
     * @param tasks
     */
    public static void undone(String num, TaskList tasks) {
        int number = Integer.parseInt(num) - 1;
        tasks.get(number).isDone = false;

        System.out.println(Indentation + Horizontal);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(Indentation + tasks.get(number).toString());
        System.out.println(Indentation + Horizontal);
    }

    /**
     * delete the tasks
     * @param num
     * @param tasks
     */
    public static void delete(String num, TaskList tasks) {
        int index = Integer.parseInt(num) - 1;
        try {
            if (!(tasks.get(index)).equals(null)) {
                System.out.println(Indentation + Horizontal);
                System.out.println(Indentation + "Noted. I've removed this task:");

                System.out.println(Indentation + tasks.get(index).toString());
                tasks.remove(index);
                Task.taskNum--;

                System.out.println(" Now you have " + Task.taskNum + " tasks in the list.");
                System.out.println(Indentation + Horizontal);
            }
        } catch (Exception e) {
            System.out.println(Indentation + Horizontal);
            System.out.println(" ☹ OOPS!!! I'm sorry, but the list is empty :-(");
            System.out.println(Indentation + Horizontal);
        }
    }
}
