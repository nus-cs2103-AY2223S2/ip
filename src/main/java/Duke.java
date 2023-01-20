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
            try {
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
                    try {
                        int taskNum = Integer.parseInt(arrStr[1]);
                        Task taskToMark = arr.get(taskNum - 1);
                        taskToMark.mark();
                        printResponse("Nice! I've marked this task as done: \n " + taskToMark);
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! Please choose a Task to mark.");
                    }
                } else if (command.equals("unmark")) {
                    // Unmark - unmark a task as undone
                    try {
                        int taskNum = Integer.parseInt(arrStr[1]);
                        Task taskToMark = arr.get(taskNum - 1);
                        taskToMark.unmark();
                        printResponse("OK, I've marked this task as not done yet \n" + taskToMark);
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! Please choose a Task to unmark.");
                    }
                } else if (command.equals("todo")) {
                    // todo - add a task with type todo
                    try {
                        add(arrStr[1], 'T');
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! The description of this todo is incomplete.");
                    }
                } else if (command.equals("deadline")) {
                    try {
                        add(arrStr[1], 'D');
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! The description of this deadline is incomplete.");
                    }
                } else if (command.equals("event")) {
                    try {
                        add(arrStr[1], 'E');
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! The description of this event is incomplete.");
                    }
                } else if (command.equals("delete")) {
                    try {
                        Task deleteTask = arr.remove(Integer.parseInt(arrStr[1]) - 1);
                        printResponse("Noted. I've removed this task: \n" + deleteTask + "\nNow you have " + arr.size() + " " +
                                "tasks in the list.");
                    } catch (ArrayIndexOutOfBoundsException e1) {
                        printResponse("OOPS!!! Please choose a Task to delete.");
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void printResponse(String response) {
        System.out.println(str);
        System.out.println(response);
        System.out.println(str);
    }

    public static void add(String response, char type) throws ArrayIndexOutOfBoundsException {
       if(type == 'T') {
           Todo newTask = new Todo(response);
           arr.add(newTask);
           printResponse("Got it. I've added this task: \n" + newTask + "\nNow you have " + arr.size()
                   + " tasks in the list.");
       } else if(type == 'D') {
           String[] strArr = response.split(" /by ", 2);
           Deadline newTask = new Deadline(strArr[0], strArr[1]);
           arr.add(newTask);
           printResponse("Got it. I've added this task: \n" + newTask + "\nNow you have " + arr.size()
                   + " tasks in the list.");
       } else if(type =='E') {
           String[] strArr = response.split(" /from ", 2);
           String[] timings = strArr[1].split(" /to ", 2);
           Event newTask = new Event(strArr[0], timings[0], timings[1]);
           arr.add(newTask);
           printResponse("Got it. I've added this task: \n" + newTask + "\nNow you have " + arr.size()
                   + " tasks in the list.");
       }
    }

    public static void list() {
        if (arr.isEmpty()) {
            printResponse("You have 0 task to complete at the moment!");
        } else {
            int x = 1;
            String lst = "";
            for (int i = 0; i < arr.size(); i++) {
                if (!lst.equals("")) {
                    lst = lst + "\n";
                }
                lst = lst + x + ". " + arr.get(i);
                x++;
            }
            printResponse("Here are the task in your list: \n" + lst);
        }
    }
}
