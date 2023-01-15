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

        //Storage for the list function.
        String[] storage = new String[100];

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
            input = sc.nextLine();
            System.out.println("____________________________________________________________");
            if(input.equals("bye")) { //User input bye to quit.
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if(input.equals("list")) { //User input list to display list of items added.
                int numbering = 1;
                for(int i = 0; i < counter; i++) {
                    System.out.println(numbering + ". " + storage[i]);
                    numbering++;
                }
                System.out.println("____________________________________________________________");
            } else { //User input added to the list.
                System.out.println("added: " + input);
                storage[counter] = input;
                counter++;
                System.out.println("____________________________________________________________");
            }
        }
    }
}
