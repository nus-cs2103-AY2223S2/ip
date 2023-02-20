package duke.duke;

import java.util.Scanner;

public class Ui {
    private Parser parser;

    public Ui(Parser parser) {
        this.parser = parser;
    }

    public void readInput() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Greetings");

        Scanner user = new Scanner(System.in);
        String responseMsg = "";
        while (true) {
            String input = user.nextLine();
            responseMsg = this.parser.parse(input);
            System.out.println(responseMsg);
            if (responseMsg.equals("Bye!")) {
                break;
            }
        }
    }
}
