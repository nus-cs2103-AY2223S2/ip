package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {

    private BufferedReader reader;

    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String input() throws IOException {
        return this.reader.readLine();
    }

    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        indent("Hi, I'm Duke ");
        indent("What can I do for you :) ?\n");
        line();
    }

    public void end() {
        line();
        indent("Bye. Hope to hear from you again! :)");
        line();
    }

    public static void emptyError() {
        indent("Sorry! You provided an empty description. Pls provide a correct input :)");
        line();
    }

    public static void invalidInputError() {
        indent("Sorry! I did not quite understand what you meant :( Pls try again!");
        line();
    }

    public static void printUnmark(TaskList taskList, int i) {
        line();
        indent("Alright! I've unmarked this task :(\n");
        indent("  " + taskList.get(i - 1));
        line();
    }

    public static void printMark(TaskList taskList, int i) {
        line();
        indent("Alright! I've marked this task as done :) !\n");
        indent("  " + taskList.get(i - 1));
        line();
    }

    public static void printDelete(TaskList taskList, int i, int size) {
        line();
        indent("OK! I've deleted this task :)\n");
        indent("  " + taskList.get(i - 1));
        indent(String.format("Now you have %d tasks in the list", size - 1));
        line();
    }

    public static void printListCommand(TaskList taskList) {
        line();
        indent("Here are the remaining tasks in your list:\n");
        taskList.printList();
        line();
    }

    public static void printAddTask(Task task, int size) {
        line();
        indent("Roger! I've added this task to the list:\n");
        indent(task + "\n");
        indent(String.format("Now you have %d tasks left in the list", size));
        line();
    }

    public static void indent(String txt) {
        System.out.println("     " + txt);
    }

    public static void line() {
        System.out.println("____________________________________________________________________________________");
    }
}
