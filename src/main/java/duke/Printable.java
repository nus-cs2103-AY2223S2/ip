package duke;

import java.util.Scanner;

class Printable {

    static String INITIAL_GREETING = "Hello";
    static String FINAL_GREETING = "Bye";
    static String SHOW_TASKS = "list";
    static String TERMINATE = "exit";
    static String MARK = "mark";
    static String UNMARK = "unmark";
    static String TODO = "todo";
    static String DEADLINE = "deadline";
    static String EVENT = "event";
    static int DECREMENT = 1;

    static void greet() {
        System.out.println(INITIAL_GREETING);
    }

    static void exit() {
        System.out.println(FINAL_GREETING);
        System.exit(0);
    }

}
