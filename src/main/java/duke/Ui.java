package duke;

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

    public boolean getInput(TaskList tasks) throws DukeException {
        Scanner input = new Scanner(System.in);
        String echo = input.nextLine();
        String echoSplit[] = echo.split(" ");

        if(echo.equals("bye")) {
            System.out.println("    -------------------------------------------\n    Bye. Hope to see you again soon!\n    -------------------------------------------");
            return false;
        } else {
            Parser parser = new Parser();
            parser.parse(echo, echoSplit, COUNTER, tasks);
            return true;
        }

    }





}
