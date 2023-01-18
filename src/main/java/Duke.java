import java.util.ArrayList;
import java.util.Scanner;

import Task.Task;

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
                            + list_to_store.get(i).getStatusIcon() + " " 
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

            Task item = new Task(echo);
            list_to_store.add(item);

            System.out.println("    Duke says:");
            System.out.println("    Added " + item.toString());
            
        }
        scan.close();
        System.out.println("    Duke says:");
        System.out.println("    Bye. Hope you run this program again!");
    }
}
