import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Task.Task;
import todo.todo;
import Event.Event;
import Deadline.Deadline;
import Exception.*;

public class Duke {

    public static Task parseEcho(String echo) throws noTaskDescriptionException {
        if (echo.startsWith("todo")) {
            if (echo.substring(4).trim().isEmpty()) {
                throw(new noTaskDescriptionException("     ☹ OOPS!!! The description of a todo cannot be empty."));
            }
            return new todo(echo.substring(4).trim());
    
        } else if (echo.startsWith("deadline")) {
            String deadlineArguments = echo.substring(8).trim();
            if (deadlineArguments.isEmpty()) {
                throw(new noTaskDescriptionException("     ☹ OOPS!!! The description of a deadline cannot be empty."));
            }
            String splitArguments[] = deadlineArguments.split("/");
            
            return new Deadline(splitArguments[0], splitArguments[1].substring(2).trim());
    
        } else if (echo.startsWith("event")) {
            String eventArguments = echo.substring(5).trim();
            if (eventArguments.isEmpty()) {
                throw(new noTaskDescriptionException("     ☹ OOPS!!! The description of a event cannot be empty."));
            }
            String splitArguments[] = eventArguments.split("/");
            return new Event(splitArguments[0], splitArguments[1].substring(4).trim(), splitArguments[2].substring(2).trim());
    
        } else {
            // System.out.println("Placeholder");
            throw(new invalidInputException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    static void printListNumber(ArrayList<Task> list) {
        System.out.println("    Now you have " + list.size() + " task(s) in the list.");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean program_running_status = true;

        String echo;
        Scanner scan = new Scanner(System.in);

        ArrayList<Task> listToStore = new ArrayList<Task>();


        while (program_running_status) {
            echo = scan.nextLine();

            if (echo.equals("bye")) {
                break;
            }

            if (echo.equals("list")) {
                System.out.println("    OK, Here are the items in your list: ");
                for (int i = 0; i < listToStore.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " 
                            + listToStore.get(i).toString());
                }
                // put in loop to read the list
                continue;
            }

            if (echo.startsWith("mark")) {
                try {
                    int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                    listToStore.get(taskToModify-1).mark_done();   
                } catch (Exception e) {
                    // TODO: handle exception
                }
                continue;
            }

            if (echo.startsWith("unmark")) {
                try {
                    int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                    listToStore.get(taskToModify-1).mark_undone();   
                } catch (Exception e) {
                    // TODO: handle exception
                }
                continue;
            }

            if (echo.startsWith("delete") || echo.startsWith("remove")) {
                try {
                    int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                    System.out.println("    Noted. I have removed this task:");
                    System.out.println("    " + listToStore.get(taskToModify-1).toString());
                    listToStore.remove(taskToModify-1);   
                    printListNumber(listToStore);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                continue;
            }

            Task item;

            // Create a separate function in order to assign to item;
            try {
                item = parseEcho(echo);

            } catch (dukeException e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
                continue;
            }
            //


            // if (echo.startsWith("todo")) {
            //     item = new todo(echo.substring(4).trim());

            // } else if (echo.startsWith("deadline")) {
            //     String deadlineArguments = echo.substring(8).trim();
            //     String splitArguments[] = deadlineArguments.split("/");
                
            //     item = new Deadline(splitArguments[0], splitArguments[1].substring(2).trim());

            // } else if (echo.startsWith("event")) {
            //     String eventArguments = echo.substring(5).trim();
            //     String splitArguments[] = eventArguments.split("/");
            //     item = new Event(splitArguments[0], splitArguments[1].substring(4).trim(), splitArguments[2].substring(2).trim());

            // } else {
            //     System.out.println("Placeholder");
            //     continue;
            // }


            listToStore.add(item);

            System.out.println("    Duke says:");
            System.out.println("    Added");
            System.out.println("    " + item.toString());
            printListNumber(listToStore);
            // System.out.println("    Now you have " + listToStore.size() + " task(s) in the list.");
            
        }
        scan.close();
        System.out.println("    Duke says:");
        System.out.println("    Bye. Hope you run this program again!");
    }
}
