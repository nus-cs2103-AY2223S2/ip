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
        List list = new List();
        while(active) {
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                    end();
                    active = false;
                    break;
                case "list":
                    list.print();
                    break;
                default:
                    list.add(input);
            }
        }
    }

    public static void greeting() {
        System.out.println(line);
        System.out.println(
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void end() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
