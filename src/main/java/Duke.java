import java.util.Scanner;

public class Duke {
    static String line = "______________________________________";
    public static void main(String[] args) {
        startDuke();
    }

    public static void startDuke() {
        greeting();
        Scanner sc = new Scanner(System.in);
        boolean active = true;
        while(active) {
            String input = sc.next();
            switch(input) {
                case "bye":
                    end();
                    active = false;
                    break;
                default:
                    echo(input);
            }
        }
    }

    public static void greeting() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void echo(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public static void end() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
