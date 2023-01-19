import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Mash \nWhat can I do for you?");

        Scanner myObj = new Scanner(System.in);
        String input;
        List<Task> list = new ArrayList<Task>();
        input = myObj.nextLine();
        char m = 'm';
        char a = 'a';
        char r = 'r';
        char k = 'k';
        char u = 'u';
        char n = 'n';

        while (!input.equals("bye")) {
            //print List
            if (input.equals("list")) {
                int counter = 1;
                for (Task task : list) {
                    System.out.println(counter + ". " + "[" + task.getStatusIcon() + "] " + task.description);
                    counter++;
                }
                input = myObj.nextLine();

                //mark a Task
            } else if (input.charAt(0) == m && input.charAt(1) == a && input.charAt(2) == r && input.charAt(3) == k) {
                //getting the index
                String indexString = input.substring(5);
                //converting the index from String to Int
                int indexInt = Integer.parseInt(indexString) - 1;

                //in case the Task index to mark exceeds current number of Tasks or neg number
                if (indexInt + 1 > list.size() || indexInt < 0) {
                    System.out.println("Task does not exist");
                } else {
                    Task t = list.get(indexInt);
                    t.markDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "[X] " + t.description);
                }
                input = myObj.nextLine();

                //unmark a Task
            } else if (input.charAt(0) == u && input.charAt(1) == n && input.charAt(2) == m && input.charAt(3) == a
                    && input.charAt(4) == r && input.charAt(5) == k) {
                String indexString = input.substring(7);
                //converting the index from String to Int
                int indexInt = Integer.parseInt(indexString) - 1;

                //in case the Task index to mark exceeds current number of Tasks or neg number
                if (indexInt + 1 > list.size() || indexInt < 0) {
                    System.out.println("Task does not exist");
                } else {
                    Task t = list.get(indexInt);
                    t.markUndone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] " + t.description);
                }
                input = myObj.nextLine();

                //normal case: create Task and add Task to list
            } else {
                Task t  = new Task(input);
                list.add(t);
                System.out.println("added: " + t.description);
                input = myObj.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
