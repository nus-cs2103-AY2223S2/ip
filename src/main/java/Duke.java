import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    static LinkedList<Task> todo = new LinkedList<>();
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    static String line = "---------------------------------";

    static void add(Task cur) {
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + cur.symbol + "] " +  "[" + cur.getStatusIcon() + "] " + cur.description);
        todo.add(cur);
        System.out.println("Now you have " + todo.size() + " tasks in the list.");
    }

    static void list(LinkedList<Task> list) {
        System.out.println("Here are the taks in your list:");
        for (Task cur : todo) {
            System.out.println(todo.indexOf(cur) + 1 + ". [" + cur.symbol + "] " +  "[" + cur.getStatusIcon() + "] " + cur.description);
        }
    }

    static void markTask(int index) {
        Task cur = todo.get(index);
        cur.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);

    }

    static void unmarkTask(int index) {
        Task cur = todo.get(index);
        cur.markUndone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + "  managed by Wesley Teo.\n\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        System.out.println(line);

        //switch statements for query
        while (!input.equals("bye")) {
            QueryType inputType = Query.queryType(input);
            String[] inputArr = input.split(" ");
            int index;

            switch (inputType) {
                case list:
                    list(todo);
                    break;
                case todo:
                    add(new Todo(input));
                    break;
                case deadline:
                    add(new Deadline(input));
                    break;
                case event:
                    add(new Event(input));
                    break;
                case mark:
                    index = Integer.parseInt(inputArr[1]) - 1;
                    markTask(index);
                    break;
                case unmark:
                    index = Integer.parseInt(inputArr[1]) - 1;
                    unmarkTask(index);
                    break;
                default:
                    System.out.println("Invalid command, please try again");
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
//
//        while (!input.equals("bye")) {
//            String[] inputArr = input.split(" ");
//            if (input.equals("list")) {
//                list(todo);
//            } else if (inputArr.length == 2 && inputArr[0].equals("mark") && isNumeric(inputArr[1]) ) {
//                markTask(Integer.parseInt(inputArr[1]) -1);
//            } else if (inputArr.length == 2 && inputArr[0].equals("unmark") && isNumeric(inputArr[1])){
//                unmarkTask(Integer.parseInt(inputArr[1]) -1);
//            }
//            else {
//                add(new Task(input));
//            }
//            System.out.println(line);
//            input = sc.nextLine();
//            System.out.println(line);
//        }
