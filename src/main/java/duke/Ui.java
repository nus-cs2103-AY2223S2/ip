package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        System.out.println("Hello from\n" + logo);

        System.out.println(new TextBorder("Waddup the name's Duncan. Sorry but Duke couldn't make it, had a pretty bad stomach-ache."));
        System.out.println(new TextBorder("So what do you need bro?"));
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
