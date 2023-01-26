import javax.swing.*;
import java.util.Scanner;
import java.lang.String;
public class Ui {
    private final static String UNDERLINE = "________________________________________________________________";
    private final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner text = new Scanner(System.in);
    public static void Underline(){
        System.out.println((UNDERLINE));
    }
    public String  readIn(){
        return this.text.nextLine();
    }
    public void greet() {
        System.out.println(logo);
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        Underline();
    }
    public static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void markedMessage() {
        System.out.println("\tNice! I've marked this task as done:");

    }
    public static void unMarkedMessage() {
        System.out.println("\tOK, I've marked this task as not done yet:");
    }
    public static void showDeleteMessage() {
        System.out.println("\tNoted. I've removed this task:");
    }
    public static void addOnTaskMsg(TaskList list, int num) {
        System.out.println("\tGot it. I've added this task:");
    }
    public static void listMsg() {
        System.out.println("\tHere are the tasks in your list:");

    }

    public void DukeExceptionMsg(DukeException e) {
        System.out.printf("\t%s\n", e);
    }
}
