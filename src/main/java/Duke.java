import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(greet());

        ArrayList<String> lst = new ArrayList<>();
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
        return String.format("Hello I am: \n%sWhat can I do for you?", ownName());
    }
    public static String echo(String cmd) { return cmd; }
    public static String add(String cmd, ArrayList<String> lst) {
        lst.add(cmd); return String.format("added: %s", cmd);
    }
    public static void printList(ArrayList<String> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            if (i != 0) { str += "\t"; }
            str += String.format("%d. %s \n", i+1, arr.get(i));
        }
        print(str);
    }
    public static void print(String reply) {
        String topBottom = "~~~~~~~~~~~~~~~~~~~~\n";
        System.out.println(String.format("\t%s \t%s\n \t%s", topBottom, reply, topBottom));
    }
}
