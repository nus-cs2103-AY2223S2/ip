package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm duke.\nWhat can I do for you?");
    }

    public void showLine() {
        System.out.println("======================================================================");
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void printText(String text) {
        System.out.println(text);
    }


}

