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
            String input = scanner.nextLine();
            String[] arrStr = input.split(" ", 2);
            String command = arrStr[0];

            if (command.equals("bye")) {
                // Bye - exit program
                printResponse("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                // List = print list of task
                list();
            } else if (command.equals("mark")) {
                // Mark - mark a task as done
                int taskNum = Integer.parseInt(arrStr[1]);
                Task taskToMark = arr.get(taskNum - 1);
                taskToMark.mark();
                printResponse("Nice! I've marked this task as done: \n " + taskToMark);
            } else if (command.equals("unmark")) {
                // Unmark - unmark a task as undone
                int taskNum = Integer.parseInt(arrStr[1]);
                Task taskToMark = arr.get(taskNum - 1);
                taskToMark.unmark();
                printResponse("OK, I've marked this task as not done yet \n" + taskToMark);
            } else if (command.equals("todo")) {
                // todo - add a task with type todo
                add(arrStr[1], 'T');
            } else if (command.equals("deadline")) {
                add(arrStr[1], 'D');
            } else if (command.equals("event")) {
                add(arrStr[1], 'E');
            }

        }
    }

    public static void printResponse(String response) {
        System.out.println(str);
        System.out.println(response);
        System.out.println(str);
    }

    public static void add(String response, char type) {
       if(type == 'T') {
           Task newTask = new Task(response, 'T');
           arr.add(newTask);
           printResponse("Got it. I've added this task: \n" + newTask + "\nNow you have " + arr.size()
                   + " tasks in the list.");
       } else if(type == 'D') {
           String[] strArr = response.split(" /by ", 2);
           Task newTask = new Task(strArr[0], 'D', "(by: " + strArr[1] + ")");
           arr.add(newTask);
           printResponse("Got it. I've added this task: \n" + newTask + "\nNow you have " + arr.size()
                   + " tasks in the list.");
       } else if(type =='E') {
           String[] strArr = response.split(" /from ", 2);
           String[] timings = strArr[1].split(" /to ", 2);
           Task newTask = new Task(strArr[0], 'E', "(from: " + timings[0] + " to: " + timings[1] + ")");
           arr.add(newTask);
           printResponse("Got it. I've added this task: \n" + newTask + "\nNow you have " + arr.size()
                   + " tasks in the list.");
       }
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
