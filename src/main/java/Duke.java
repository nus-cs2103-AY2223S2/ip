import java.util.Scanner;

public class Duke {
    static String line = "    ______________________________________";

    public static void main(String[] args) {
        startDuke();
    }

    public static void startDuke() {
        greeting();
        Scanner sc = new Scanner(System.in);
        boolean active = true;
        List list = new List();
        while (active) {
            String raw = sc.nextLine();
            System.out.println(line);
            String[] input = raw.split("\\s+");
            switch (input[0]) {
                case "bye":
                    end();
                    active = false;
                    break;
                case "list":
                    list.print();
                    break;
                case "mark":
                    list.get(Integer.parseInt(input[1])).markDone();
                    break;
                case "unmark":
                    list.get(Integer.parseInt(input[1])).unmark();
                    break;
                default:
                    list.add(raw);
            }
            System.out.println(line + "\n");
        }
        sc.close();
    }

    public static void greeting() {
        System.out.println(line);
        System.out.println("    ____        _        \n" +
                "   |  _ \\ _   _| | _____ \n" +
                "   | | | | | | | |/ / _ \\\n" +
                "   | |_| | |_| |   <  __/\n" +
                "   |____/ \\__,_|_|\\_\\___|");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(line + "\n");
    }

    public static void end() {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
