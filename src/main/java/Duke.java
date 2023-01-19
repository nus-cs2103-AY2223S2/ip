import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> arr = new ArrayList<Task>();
    static String str = "------------------------------------------------------------";


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String word = scanner.nextLine();
            if (word.equals("bye")) {
                printResponse("Bye. Hope to see you again soon!");
                break;
            } else if (word.equals("list")) {
                list();
            } else if (word.split(" ")[0].equals("mark")) {
                int taskNum = Integer.parseInt(word.split(" ")[1]);
                Task taskToMark = arr.get(taskNum - 1);
                taskToMark.mark();
                printResponse("Nice! I've marked this task as done: \n " + taskToMark);
            } else if (word.split(" ")[0].equals("unmark")) {
                int taskNum = Integer.parseInt(word.split(" ")[1]);
                Task taskToMark = arr.get(taskNum - 1);
                taskToMark.unmark();
                printResponse("OK, I've marked this task as not done yet \n" + taskToMark);
            } else {
                add(word);
            }
        }
    }

    public static void printResponse(String response) {
        System.out.println(str);
        System.out.println(response);
        System.out.println(str);
    }

    public static void add(String response) {
       arr.add(new Task(response));
       printResponse("Added: " + response);
    }

    public static void list() {
        int x = 1;
        String lst = "";
        for(int i = 0; i < arr.size(); i++) {
            if(!lst.equals("")) {
                lst = lst + "\n";
            }
            lst = lst + x + ". " + arr.get(i);
            x++;
        }
        printResponse("Here are the task in your list: \n" + lst);
    }
}
