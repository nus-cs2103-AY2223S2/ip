package duke;

import duke.customization.*;
import duke.exception.GeneralDukeException;
import duke.instruction.*;

import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final DisplayFormat format = new DisplayFormat(50, 4);

    public static void main(String[] args) throws GeneralDukeException {
        // display logo and greeting messages
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        String greetingMessage = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println("Hello from\n" + logo);
        format.displayWithBar(greetingMessage);

        // taking input from the user until receiving an exit instruction
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                ExitInstruction exit = new ExitInstruction();
                exit.run();
            } else {
                EchoInstruction echo = new EchoInstruction(input);
                echo.run();
            }
        }
    }
}
