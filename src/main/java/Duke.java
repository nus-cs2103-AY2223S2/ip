import java.util.*;

public class Duke {
    protected static String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    protected static String line = "____________________________________________________________";
    protected static LinkedList<String> lst = new LinkedList<>();
    protected static boolean cont = true;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you? :)");
        printLine();
        String in = input.nextLine();
        while(cont) {
            switch (in) {
                case "bye":
                    end();
                    cont = false;
                    break;
                case "list":
                    printList();
                    in = input.nextLine();
                    break;
                default:
                    add(in);
                    in = input.nextLine();
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

    public static void add(String s) {
        if (s.trim().isEmpty()) {
            printLine();
            printLine();
        } else {
            printLine();
            lst.add(s);
            System.out.println("added: " + s);
            printLine();
        }
    }

    public static void printList() {
        printLine();
        for(int i = 0; i < lst.size(); i++) {
            String elem = lst.get(i);
            System.out.println(String.format("%d. %s", i + 1, elem));
        }
        printLine();
    }

    public static void end() {
        printLine();
        System.out.println("Bye bye! Hope to see you again soon!!");
        printLine();
    }

}
