package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        dukeGreeting();
        scanner = new Scanner(System.in);
    }

    public void dukeGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public boolean hasNextInput() {
        return scanner.hasNext();
    }

    public String userInput() {
        return scanner.nextLine();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
