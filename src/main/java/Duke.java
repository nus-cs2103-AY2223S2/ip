/**
 * DUKE
 * CS2103 project
 * @author EDWIN LIM
 * @version 0.01
 */

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;
public class Duke {

    /**
     * Main method for the program
     * @param args UNUSED
     */
    public static void main(String[] args) {

        greet();
        Scanner sc = new Scanner(System.in);

        LinkedList<Task> list = new LinkedList<>();
        String userIn;
        int entry;

        boolean QUIT = false;
        while (!QUIT) {
            userIn = sc.nextLine();
            System.out.println("____________________________________________________________");
            switch (userIn.split(" ")[0]) {
                case "list" :
                    // Perhaps look into not creating Iterators
                    Iterator<Task> list_entries = list.iterator();
                    int number = 1;
                    while (list_entries.hasNext()) {
                        System.out.println(String.format("%d.%s", number++, list_entries.next()));
                    }
                    break;

                case "mark" :
                    entry = Integer.parseInt(userIn.split(" ")[1]) - 1;
                    // User input expect index counting from 1
                    list.get(entry).setStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(entry));
                    break;

                case "unmark":
                    entry = Integer.parseInt(userIn.split(" ")[1]) - 1;
                    list.get(entry).setStatus(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(entry));
                    break;

                case "bye" :
                    QUIT = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                default:
                    list.add(new Task(userIn));
                    System.out.println(String.format("added: " + userIn));
                    break;
            }
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Prints standard welcome message.
     */
    public static void greet() {
        String logo = "                __  __ _           \n"
                + "               / _|/ _| |          \n"
                + "__      ____ _| |_| |_| | ___  ___ \n"
                + "\\ \\ /\\ / / _` |  _|  _| |/ _ \\/ __|\n"
                + " \\ V  V / (_| | | | | | |  __/\\__ \\\n"
                + "  \\_/\\_/ \\__,_|_| |_| |_|\\___||___/\n";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("Hello! I'm " + "Waffles");
        System.out.println("What can I do for you?");
    }
}

