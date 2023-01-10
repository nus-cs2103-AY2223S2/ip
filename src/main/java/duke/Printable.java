package duke;

import java.util.Scanner;

class Printable {
    
    static String INITIAL_GREETING = "Hello";
    static String FINAL_GREETING = "Bye";
    static String TERMINATE = "exit";

    static void greet() {
        System.out.println(INITIAL_GREETING);
    }

    static void exit() {
        System.out.println(FINAL_GREETING);
        System.exit(0);
    }
}
