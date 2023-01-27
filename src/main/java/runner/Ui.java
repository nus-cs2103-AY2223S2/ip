package runner;

import components.Task;

public class Ui {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );
        System.out.println(logo);
    }

    public static void ending() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(logo);
    }

    public static void markMSG(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + t.msg);
    }

    public static void unmarkMSG(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + t.msg);
    }

    public static void deleteMSG(Task t, int n) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + n + " tasks in the list.");
    }

    public static void addMSG(Task t, int n) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + n + " tasks in the list.");
    }
}
