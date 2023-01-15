/**
 * Project name: Duke
 * Author: Tan Jun Da
 * Student Number: A0234893U
 *
 * This class is the main class for the duke ip.
 */

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        //Scanner to scan user input.
        Scanner sc = new Scanner(System.in);

        // User input.
        String input = "";

        // Description of the task.
        String description = "";

        //Storage for the list function.
        Task[] storage = new Task[100];

        //Counter to count the number of items in the list.
        int counter = 0;

        //Logo of Duke
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Start of program.
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        while(true) {
            description = "";
            input = sc.nextLine();
            String[] inputArr = input.split(" ");
            System.out.println("____________________________________________________________");
            if (input.equals("bye")) { // User input bye to quit.
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (inputArr[0].equals("mark")) { // User input mark to mark the task.
                System.out.println("Nice! I've marked this task as done:");
                storage[Integer.parseInt(inputArr[1]) - 1].mark();
                System.out.println(storage[Integer.parseInt(inputArr[1]) - 1].toString());
            } else if (inputArr[0].equals("unmark")) { // User input unmark to unmark the task.
                System.out.println("OK, I've marked this task as not done yet:");
                storage[Integer.parseInt(inputArr[1]) - 1].unmark();
                System.out.println(storage[Integer.parseInt(inputArr[1]) - 1].toString());
            } else if (input.equals("list")) { // User input list to display list of items added.
                int numbering = 1;
                for (int i = 0; i < counter; i++) {
                    System.out.println(numbering + ". " + storage[i].toString());
                    numbering++;
                }
            } else if(inputArr[0].equals("todo")) { // User input todos to add todos task into list.
                System.out.println("Got it. I've added this task:");
                for(int i = 1; i < inputArr.length; i++) {
                    description = description + inputArr[i];
                    if(i != inputArr.length - 1) description += " ";
                }
                storage[counter] = new Todo(description);
                System.out.println(storage[counter].toString());
                counter++;
                System.out.println("Now you have " + counter + " task(s) in the list.");
            } else if(inputArr[0].equals("deadline")) { // User input deadline to add deadline task into list.
                System.out.println("Got it. I've added this task:");
                String deadline = "";
                for(int i = 1; i < inputArr.length; i++) {
                    if(inputArr[i].charAt(0) == '/') {
                        i++;
                        while(i < inputArr.length) {
                            deadline += inputArr[i];
                            if(i != inputArr.length - 1) deadline += " ";
                            i++;
                        }
                        break;
                    }
                    description = description + inputArr[i] + " ";
                }
                storage[counter] = new Deadline(description, deadline);
                System.out.println(storage[counter].toString());
                counter++;
                System.out.println("Now you have " + counter + " task(s) in the list.");
            } else if(inputArr[0].equals("event")) { // User input event to add event task into list.
                System.out.println("Got it. I've added this task:");
                String from = "";
                String to = "";
                for(int i = 1; i < inputArr.length; i++) {
                    if(inputArr[i].charAt(0) == '/') {
                        i++;
                        while(i < inputArr.length) {
                            if(inputArr[i].charAt(0) == '/') {
                                i++;
                                while(i < inputArr.length) {
                                    to += inputArr[i];
                                    if(i != inputArr.length - 1) to += " ";
                                    i++;
                                }
                                break;
                            }
                            from += inputArr[i] + " ";
                            i++;
                        }
                        break;
                    }
                    description = description + inputArr[i] + " ";
                }
                storage[counter] = new Event(description, from, to);
                System.out.println(storage[counter].toString());
                counter++;
                System.out.println("Now you have " + counter + " task(s) in the list.");
            } else {
                System.out.println("Please input a valid Command!");
            }
            System.out.println("____________________________________________________________");
        }
    }
}
