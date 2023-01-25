package duke;

import java.util.Scanner;

/**
 * Represents User Interface the deals with interaction with user
 */

public class UI {

    private Scanner reader;

    public UI() {
        this.reader = new Scanner(System.in);
    }

    /**
     * check if input string has less than 2 words *
     * 
     * @param toDoList TaskList which stores all tasks in programme
     * @param parser   Parser that executes relevant command for each input
     * @param storage  Storage object for read or store programme in hard drive
     */

    public void run(Parser parser, TaskList toDoList, Storage storage) {
        while (true) {

            String input = reader.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________");
                break;
            }

            boolean writeToFile = false;

            try {
                writeToFile = parser.parse(input, toDoList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("________________________________");
            }

            if (writeToFile) {
                storage.writeToFile(toDoList);
            }

            System.out.println("________________________________");

        }
        this.reader.close();

    }
}
