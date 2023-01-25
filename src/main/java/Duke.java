import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

import Task.Task;
import todo.todo;
import Event.Event;
import Deadline.Deadline;
import Exception.*;

public class Duke {

    /*
     * 
     */
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

    /*
     * 
     */
    static void printListNumber(ArrayList<Task> list) {
        System.out.println("    Now you have " + list.size() + " task(s) in the list.");
    }


    public static Task parseFileReader(String echo, boolean isDone) {
        if (echo.startsWith("todo")) {
            return new todo(isDone, echo.substring(4).trim());
        } else if (echo.startsWith("deadline")) {
            String deadlineArguments = echo.substring(8).trim();
            String splitArguments[] = deadlineArguments.split("/");
            return new Deadline(isDone, splitArguments[0], splitArguments[1].substring(2).trim());
        } else if (echo.startsWith("event")) {
            String eventArguments = echo.substring(5).trim();
            String splitArguments[] = eventArguments.split("/");
            return new Event(isDone, splitArguments[0], splitArguments[1].substring(4).trim(), splitArguments[2].substring(2).trim());
        } else {
            throw(new invalidInputException("      ☹ OOPS!!! File format seems weird"));
        }
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

        /*
         * Checks if the filepath exists, else creates it.
         */

        File createFolder = new File("data");
        createFolder.mkdirs();

        try {
            File mySaveFile = new File("data/duke.txt");
            //../../../
            if (!mySaveFile.exists()) {
                mySaveFile.createNewFile();
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error has occurred");
            e.printStackTrace();
            return;
        }

        ArrayList<Task> listToStore = new ArrayList<Task>();

        /*
         * Attempt at loading the file into the listToStore
         */

        try {
            File mySaveFile = new File("data/duke.txt");
            Scanner s = new Scanner(mySaveFile);
            while (s.hasNext()) {

                Task task;
                String nextLine = s.nextLine();
                if (nextLine.charAt(0) == '1') {
                    task = parseFileReader(nextLine.substring(2), true);
                } else {
                    task = parseFileReader(nextLine.substring(2), false);
                }
                listToStore.add(task);
            }
            s.close();
        } catch (dukeException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        String echo;
        Scanner scan = new Scanner(System.in);

        while (true) {
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

            listToStore.add(item);

            System.out.println("    Duke says:");
            System.out.println("    Added");
            System.out.println("    " + item.toString());
            printListNumber(listToStore);
            
        }

        System.out.println("    Saving:");

        try {
                FileWriter fw = new FileWriter("data/duke.txt");
                fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        while (!listToStore.isEmpty()) {
            try {
                
                FileWriter fw = new FileWriter("data/duke.txt", true);
                fw.write(listToStore.get(0).getCommand());
                listToStore.remove(0);
                fw.write(System.lineSeparator());
                fw.close();
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("    An Error has occured");
                break;
            }
        }
        

        System.out.println("    Save complete");

        scan.close();
        System.out.println("    Duke says:");
        System.out.println("    Bye. Hope you run this program again!");
    }
}
