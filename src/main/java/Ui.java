import java.util.Scanner;

public class Ui {
    private String LOGO_AND_GREETINGS = "Hello from\n" 
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greetings() {
        System.out.println(LOGO_AND_GREETINGS);
    }

    public String getLine() {
        String echo = "";
        if (sc.hasNext()) {
            echo = sc.nextLine();
        }
        return echo;
    }

    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
    }

    public void markMsg(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    public void markError() {
        System.out.println("☹ OOPS!!! A valid number has to follow the mark command");
    }

    public void unmarkMsg(TaskList tasks, int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index).toString());
    }

    public void unmarkError() {
        System.out.println("☹ OOPS!!! A valid number has to follow the unmark command");
    }

    public void addMsg(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    public void deleteMsg(TaskList tasks, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index).toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size() - 1);
    }

    public void todoError() {
        System.out.println("☹ OOPS!!! The description cannot be empty!");
    }

    public void eventError() {
        System.out.println("☹ OOPS!!! The timing was not specified or there was no description!");
    }

    public void deadlineError() {
        System.out.println("☹ OOPS!!! The timing needs to be in format yyyy-mm-dd hhmm"
                + " or there was no description!");
    }

    public void deleteError() {
        System.out.println("☹ OOPS!!! A valid number has to follow the delete command.");
    }

    public void unknownMsg() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void endUi() {
        sc.close();
    }
}
