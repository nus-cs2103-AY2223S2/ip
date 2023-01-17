import java.util.Scanner;

public class Duke {
    protected static String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    protected static String line = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you? :)");
        printLine();
        String in = input.nextLine();
        while(true) {
            if (in.equals("bye")) {
                end();
                break;
            } else {
                repeat(in);
                in = input.next();
            }
        }
    }

    public static void printLine(){
        System.out.println(line);
    }

    public static void repeat(String s) {
        printLine();
        System.out.println(s);
        printLine();
    }

    public static void end() {
        printLine();
        System.out.println("Bye! Hope to see you again soon!!");
        printLine();
    }

}
