package seedu.duke;

import java.util.Scanner;

public class Ui {
    public static int COUNTER = 0;

    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n" + logo);
    }

    public static String getInput(TaskList tasks, String input) throws DukeException {
        String[] inputArgs = input.split(" ");

        if(input.equals("bye")) {
            return "    Bye. Hope to see you again soon!";
        } else {
            Parser parser = new Parser();
            String reply = parser.parse(input, inputArgs, tasks);
            return reply;
        }

    }





}
