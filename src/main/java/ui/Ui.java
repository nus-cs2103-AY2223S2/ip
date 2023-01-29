package ui;


public class Ui {
    private static String logo = " ____        _        \n"
    + "|  _ \\ _   _| | _____ \n"
    + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n"
    + "|____/ \\__,_|_|\\_\\___|\n";
    
    
    public static void showWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showToUser(String message) {
        System.out.println(message);
    }
}
