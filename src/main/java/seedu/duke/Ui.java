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
//        Scanner input = new Scanner(System.in);
        String echo = input;
        String[] echoSplit = echo.split(" ");

        if(echo.equals("bye")) {
//            System.out.println("    -------------------------------------------\n"
//                    + "    Bye. Hope to see you again soon!\n    -------------------------------------------");
            return "    Bye. Hope to see you again soon!";
        } else {
            Parser parser = new Parser();
            String reply = parser.parse(echo, echoSplit, COUNTER, tasks);
            return reply;
        }

    }





}
