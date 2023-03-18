package seedu.duke;

import java.util.Scanner;

public class Ui {
    public static int COUNTER = 0;

    public Ui() {

    }

    public static String getInput(TaskList tasks, String input) throws DukeException {

            Parser parser = new Parser();
            String reply = parser.parse(input, tasks);
            return reply;

    }

    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello! I'm Duke\n" +
                "What can I do for you?\n" + logo;
    }





}
