import java.util.ArrayList;
import java.util.Scanner;

import Task.Task;
import todo.todo;
import Event.Event;
import Deadline.Deadline;

public class Duke {
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

        ArrayList<Task> list_to_store = new ArrayList<Task>();


        while (program_running_status) {
            echo = scan.nextLine();

            if (echo.equals("bye")) {
                break;
            }

            if (echo.equals("list")) {
                System.out.println("    OK, Here are the items in your list: ");
                for (int i = 0; i < list_to_store.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " 
                            + list_to_store.get(i).toString());
                }
                // put in loop to read the list
                continue;
            }

            if (echo.startsWith("mark")) {
                try {
                    int task_to_mark = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                    list_to_store.get(task_to_mark-1).mark_done();   
                } catch (Exception e) {
                    // TODO: handle exception
                }
                continue;
            }

            if (echo.startsWith("unmark")) {
                try {
                    int task_to_mark = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                    list_to_store.get(task_to_mark-1).mark_undone();   
                } catch (Exception e) {
                    // TODO: handle exception
                }
                continue;
            }

            Task item;

            if (echo.startsWith("todo")) {
                item = new todo(echo.substring(4).trim());

            } else if (echo.startsWith("deadline")) {
                String deadlineArguments = echo.substring(8).trim();
                String splitArguments[] = deadlineArguments.split("/");
                
                item = new Deadline(splitArguments[0], splitArguments[1].substring(2).trim());

            } else if (echo.startsWith("event")) {
                String eventArguments = echo.substring(5).trim();
                String splitArguments[] = eventArguments.split("/");
                item = new Event(splitArguments[0], splitArguments[1].substring(4).trim(), splitArguments[2].substring(2).trim());

            } else {
                System.out.println("Placeholder");
                continue;
            }


            list_to_store.add(item);

            System.out.println("    Duke says:");
            System.out.println("    Added");
            System.out.println("    " + item.toString());
            System.out.println("    Now you have " + list_to_store.size() + " task(s) in the list.");
            
        }
        scan.close();
        System.out.println("    Duke says:");
        System.out.println("    Bye. Hope you run this program again!");
    }
}
