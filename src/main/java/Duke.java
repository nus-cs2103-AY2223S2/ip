import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String BYE = "bye";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(greet());

        ArrayList<Task> arr = new ArrayList<>();
        while (sc.hasNext()) {
            String cmd = sc.nextLine();
            if (cmd.equals(BYE)) {
                exit();
                break;
            }
            execute(cmd, arr);
//            echo(sc.nextLine());
        }
        sc.close();
    }
    static void execute(String cmd, ArrayList<Task> arr) {
        String[] words = cmd.split(" ");
        switch (words[0]) {
            case "list":
                printList(arr);
                break;
            case "mark":
                mark(words[1], arr);
                break;
            case "unmark":
                unmark(words[1], arr);
                break;
            default:
                print(add(cmd, arr));
        }
        /* if (words[0].equals("list")) {
            printList(arr);
        } else if (words[0].equals("mark")) {   //try catch if words have 2 elements
            mark(words[1], arr);
        } else if (words[0].equals("unmark")) { //try catch if words have 2 elements
            unmark(words[1], arr);
        } else {
            print(add(cmd, arr));
        }*/
    }
    static void mark(String num, ArrayList<Task> arr) {     //try catch, possibility of error if user enter wrong cmd
        int index = Integer.parseInt(num);
        arr.get(index - 1).markAsDone();
        print(String.format("Nice! I've marked this task as done: \n\t%s", arr.get(index - 1)));
    }
    static void unmark(String num, ArrayList<Task> arr) {   //try catch, possibility of error if user enter wrong cmd
        int index = Integer.parseInt(num);
        arr.get(index - 1).unmarkAsDone();
        print(String.format("Ok, I've marked this task as not done yet: \n\t%s", arr.get(index - 1)));
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
    static String add(String cmd, ArrayList<Task> arr) {
        String[] words = cmd.split(" ");
        String typeOfTask = words[0];
        Task task = new Task(cmd);
        switch(typeOfTask) {
            case "todo":
                task = new ToDo(cmd.replace("todo ", ""));
                break;
            case "deadline":
                task = new Deadline(cmd.replace("deadline ", ""));
                break;
            case "event":
                task = new Event(cmd.replace("event ", ""));
                break;
        }
        arr.add(task);
        return String.format("Got it. I've added this task: \n\t\t%s \n\tNow you have %d tasks in the list.", task, arr.size());
    }
    static void printList(ArrayList<Task> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            if (i != 0) {
                str += "\n\t";
            }
            str += String.format("%d. %s", i+1, arr.get(i));
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
    static void echo(String cmd) {
        System.out.println(cmd);
    }
}
