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
        LinkedList<String> list = new LinkedList<>();
        boolean QUIT = false;
        while (!QUIT) {
            String userIn = sc.nextLine();
            switch (userIn) {
                case "list" :
                    Iterator<String> list_entries = list.iterator();
                    int number = 1;
                    while (list_entries.hasNext()) {
                        System.out.printf("%d. %s%n", number++, list_entries.next());
                    }
                    break;
                case "bye" :
                    QUIT = true;
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                default:
                    list.add(userIn);
                    System.out.println("added: " + userIn);
                    break;
            }
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
                + " \\ V  V / (_| | | | | | |  __/\\__ \\n"
                + "  \\_/\\_/ \\__,_|_| |_| |_|\\___||___/";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("Hello! I'm " + "Waffles");
        System.out.println("What can I do for you?");
    }
}

