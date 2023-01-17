import java.util.*;

public class Duke {

    static String line = "      -----------------------------------------------------------------";

    public static void main(String[] args) {
        greet();
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        //echo

        while(true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("        byebye! Have an exquisite day, cutiepatootie");
                break;
            }

            //echoes input
            System.out.println("\n" + line);
            echo(input);
            System.out.println(line);
        }
        sc.close();
        System.out.println(line);
    }

    public static void echo(String name) {
        System.out.println();
        System.out.println("        " + name);
    }

    static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.println("\nHello! I'm Oli\n" + "What can I do for you?");
    }

}
