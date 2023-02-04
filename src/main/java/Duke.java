import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(greet());

        ArrayList<Task> lst = new ArrayList<>();
        while (sc.hasNext()) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                exit(); break;
            } else if (cmd.equals("list")) {
                printList(lst);
            } else {
                print(add(cmd, lst));
            }
        }
        sc.close();
    }
    static String ownName() {
        String name = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return name;
    }
    static String greet() {
        return String.format("Hello I am: \n%sWhat can I do for you?", ownName());
    }
    static String add(String cmd, ArrayList<Task> lst) {
        lst.add(new Task(cmd)); return String.format("added: %s", cmd);
    }
    static void printList(ArrayList<Task> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            if (i != 0) {
                str += "\t";
            }
            str += String.format("%d. %s \n", i+1, arr.get(i));
        }
        print(str);
    }
    static void exit() {
        print("Bye. Hope to see you again soon!");
    }
    /**
     * Used to print out any reply with the correct formatting
     */
    static void print(String reply) {
        String topBottom = "~~~~~~~~~~~~~~~~~~~~\n";
        System.out.println(String.format("\t%s \t%s\n \t%s", topBottom, reply, topBottom));
    }

    /**
     * Used in Level-1 to echo
     */
    static String echo(String cmd) {
        return cmd;
    }
}
