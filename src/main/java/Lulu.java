import java.util.Scanner;

public class Lulu {
    public static String LINE = "____________________________________________________________";

    public void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I am lulu");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void echo(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }

    public void exit() {
        System.out.println(LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(LINE);
    }
}
