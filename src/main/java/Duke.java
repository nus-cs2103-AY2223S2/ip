import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Mash \nWhat can I do for you?");

        Scanner myObj = new Scanner(System.in);
        String input;
        List<Task> list = new ArrayList<Task>();
        input = myObj.nextLine();


        while (!input.equals("bye")) {
            //print List
            if (input.equals("list")) {
                int counter = 1;
                for (Task task : list) {
                    System.out.println(counter + ". " + task.toString());
                    counter++;
                }
                input = myObj.nextLine();

                //mark a Task
            } else if (input.length() > 4 && input.substring(0, 4).equals("mark")) {
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
                    System.out.println("Nice! I've marked this task as done:\n" + t.toString());
                }
                input = myObj.nextLine();

                //unmark a Task
            } else if (input.length() > 6 && input.substring(0, 6).equals("unmark")) {
                String indexString = input.substring(7);
                //converting the index from String to Int
                int indexInt = Integer.parseInt(indexString) - 1;

                //in case the Task index to mark exceeds current number of Tasks or neg number
                if (indexInt + 1 > list.size() || indexInt < 0) {
                    System.out.println("Task does not exist");
                } else {
                    Task t = list.get(indexInt);
                    t.markUndone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + t.toString());
                }
                input = myObj.nextLine();

                //delete
            } else if (input.length() > 6 && input.substring(0, 6).equals("delete")) {
                String indexString = input.substring(7);
                int indexInt = Integer.parseInt(indexString) - 1;

                //in case the Task index to mark exceeds current number of Tasks or neg number
                if (indexInt + 1 > list.size() || indexInt < 0) {
                    System.out.println("Task does not exist");
                } else {
                    String task = list.get(indexInt).toString();
                    list.remove(indexInt);
                    System.out.println("Noted. I've removed this task: \n"
                            + task
                            + "\nNow you have " + list.size() + " tasks in the list.");
                }
                input = myObj.nextLine();

                //normal case: create Task and add Task to list
            } else {

                //if the task is a todo
                if (input.length() > 4 && input.substring(0, 4).equals("todo")) {

                    Task t  = new Todo(input);
                    list.add(t);
                    System.out.println("Got it. I've added this task: \n"
                            + t.toString()
                            + "\nNow you have " + list.size() + " tasks in the list.");

                    //if task is a deadline
                } else if (input.length() > 8 && input.substring(0, 8).equals("deadline")) {
                    int slashIndex = input.lastIndexOf("/");
                    String date = input.substring(slashIndex + 4);
                    Task t  = new Deadline(input.substring(9, slashIndex-1), date);
                    list.add(t);
                    System.out.println("Got it. I've added this task: \n"
                            + t.toString()
                            + "\nNow you have " + list.size() + " tasks in the list.");

                    //if task is an Event
                } else if (input.length() > 5 && input.substring(0, 5).equals("event")) {
                    int fromIndex = input.lastIndexOf("from");
                    int toIndex = input.lastIndexOf("to");
                    String startDate = input.substring(fromIndex + 5, toIndex - 2);
                    String endDate = input.substring(toIndex + 3);
                    Task t  = new Event(input.substring(6, fromIndex - 2), startDate, endDate);
                    list.add(t);
                    System.out.println("Got it. I've added this task: \n"
                            + t.toString()
                            + "\nNow you have " + list.size() + " tasks in the list.");
                } else {

                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    Task t = new Task(input);
//                    list.add(t);
//                    System.out.println("Got it. I've added this task: \n"
//                            + t.toString()
//                            + "\nNow you have " + list.size() + " tasks in the list.");
                }

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
