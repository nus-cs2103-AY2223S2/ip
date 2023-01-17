/**
 * DUKE
 * CS2103 project
 * @author EDWIN LIM
 * @version 0.01
 */

import java.util.Scanner;
public class Duke {

    /**
     * Main method for the program
     * @param args UNUSED
     */
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        Boolean QUIT = false;
        while (!QUIT) {
            String userIn = sc.nextLine();
            switch (userIn) {
                case "bye" :
//                case "exit" :
                    QUIT = true;
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                default:
                    System.out.println(userIn);
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
        System.out.println("Hello from\n" + logo);
        System.out.println("");
        System.out.println("Hello! I'm " + "Waffles");
        System.out.println("What can I do for you?");

    }

}

