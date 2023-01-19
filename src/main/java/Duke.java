import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(greet());
        while (sc.hasNext()) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                exit(); break;
            } else if (cmd.equals("list")) {

            } else {
                print(echo(cmd));
            }
        }
        sc.close();
    }
    public static void exit() {
        print("Bye. Hope to see you again soon!");
    }
    public static String ownName() {
        String name = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return name;
    }
    public static String greet() {
        return String.format("Hello I am: %sWhat can I do for you?", ownName());
    }
    public static String echo(String command) { return command; }
    public static void print(String reply) {
        String topBottom = "~~~~~~~~~~~~~~~~~~~~\n";
        System.out.println(String.format("\t%s \t%s\n \t%s", topBottom, reply, topBottom));
    }
}
