import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        //echoing till user says bye
        Scanner myScan = new Scanner(System.in);
        String input = myScan.nextLine();

        while (!(input.equalsIgnoreCase("bye"))) {
            echo(input);
            input = myScan.nextLine();
        }

        exit();
    }

    public static void greet() {
        System.out.println("Hi there!\nWhat can I do for you on this fine day :)?");
        divider();
    }

    public static void exit() {
        System.out.println("YAY Thank GOD! BYEEEEE~");
    }

    public static void echo(String s) {
        divider();
        System.out.println(s);
        divider();
    }

    public static void divider() {
        System.out.println("-".repeat(50));
    }
}
