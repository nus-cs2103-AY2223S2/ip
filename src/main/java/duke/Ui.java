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
    public static String showLogo() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\ \n"
                    + "| |_| | |_| |   <  __// \n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println(logo);
        return logo;
    }

    /**
     * shows welcome after printing logo
     */
    public static String showWelcome() {
        StringBuilder str = new StringBuilder();
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Hello! I'm Duke");
        System.out.println(Indentation + "What can I do for you?");
        System.out.println(Indentation + Horizontal);

        str.append(Indentation + "Hello! I'm Duke");
        str.append(System.getProperty("line.separator"));
        str.append(Indentation + "What can I do for you?");
        //str.append(System.getProperty("line.separator"));
        return str.toString();
    }

    /**
     * shows loading error
     */
    public String showLoadingError() {
        StringBuilder str = new StringBuilder();
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Loading error! please try again");
        System.out.println(Indentation + Horizontal);

        str.append(Indentation + "Loading error! please try again");
        return str.toString();
    }

    /**
     * exits the program
     */
    public static String exit() {
        StringBuilder str = new StringBuilder();
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Bye. Hope to see you again soon!");
        System.out.println(Indentation + Horizontal);

        str.append(Indentation + "Bye. Hope to see you again soon!");
        return str.toString();
    }

    /**
     * shows list details
     * @param task
     */
    public static String showList(TaskList task) {
        assert task == null : "Invalid task";
        StringBuilder str = new StringBuilder();
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Here are the tasks in your list:");
        //str.append(Indentation + Horizontal);
        str.append(Indentation + "Here are the tasks in your list: ");
        str.append(System.getProperty("line.separator"));


        for (int i = 0; i < task.size(); i++) {
            System.out.println(Indentation + (i + 1) + ". " + task.get(i).toString());
            str.append(Indentation + (i + 1) + ". " + task.get(i).toString());
            str.append(System.getProperty("line.separator"));
        }

        System.out.println(Indentation + Horizontal);
        return str.toString();
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
    public static String done(String num, TaskList tasks) {
        assert num == null : "Invalid num";
        assert tasks == null : "Invalid tasks";

        StringBuilder str = new StringBuilder();
        int number = Integer.parseInt(num) - 1;
        //tasks.get(number).isDone = true;
        tasks.get(number).isDone = true;

        System.out.println(Indentation + Horizontal);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Indentation + tasks.get(number).toString());
        System.out.println(Indentation + Horizontal);

        str.append("Nice! I've marked this task as done:");
        str.append(System.getProperty("line.separator"));
        str.append(Indentation + tasks.get(number).toString());
        //str.append(System.getProperty("line.separator"));

        return str.toString();
    }

    /**
     * shows that task is marked as not done
     * @param num
     * @param tasks
     */
    public static String undone(String num, TaskList tasks) {
        assert num == null : "Invalid num";
        assert tasks == null : "Invalid tasks";

        StringBuilder str = new StringBuilder();
        int number = Integer.parseInt(num) - 1;
        tasks.get(number).isDone = false;

        System.out.println(Indentation + Horizontal);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(Indentation + tasks.get(number).toString());
        System.out.println(Indentation + Horizontal);

        str.append("Nice! I've marked this task as done:");
        str.append(System.getProperty("line.separator"));
        str.append(Indentation + tasks.get(number).toString());
        //str.append(System.getProperty("line.separator"));
        return str.toString();
    }

    /**
     * delete the tasks
     * @param num
     * @param tasks
     */
    public static String delete(String num, TaskList tasks) {
        assert num == null : "Invalid num";
        assert tasks == null : "Invalid tasks";

        StringBuilder str = new StringBuilder();
        int index = Integer.parseInt(num) - 1;
        int originalSize = tasks.size();
        try {
            if (!(tasks.get(index)).equals(null)) {
                System.out.println(Indentation + Horizontal);
                System.out.println(Indentation + "Noted. I've removed this task:");
                System.out.println(Indentation + tasks.get(index).toString());

                str.append(Indentation + "Noted. I've removed this task:");
                str.append(System.getProperty("line.separator"));
                str.append(Indentation + tasks.get(index).toString());
                str.append(System.getProperty("line.separator"));

                tasks.remove(index);
                Task.taskNum--;
                assert tasks.size() == originalSize - 1;

                System.out.println(" Now you have " + Task.taskNum + " tasks in the list.");
                System.out.println(Indentation + Horizontal);

                str.append(" Now you have " + Task.taskNum + " tasks in the list.");
                str.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            System.out.println(Indentation + Horizontal);
            System.out.println(" ☹ OOPS!!! I'm sorry, but the list is empty :-(");
            System.out.println(Indentation + Horizontal);

            str.append(" ☹ OOPS!!! I'm sorry, but the list is empty :-(");
            str.append(System.getProperty("line.separator"));
        }
        return str.toString();
    }
}
