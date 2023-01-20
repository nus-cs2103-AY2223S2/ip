import presentation.controllers.DukeEventLoop;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        final Scanner scanner = new Scanner(System.in);
        final DukeEventLoop eventLoop = DukeEventLoop.createEventLoop(scanner);
        eventLoop.run();
        scanner.close();
    }
}
