package duke;

import java.util.Scanner;

public class UI {

    private Scanner reader;

    public UI() {
        this.reader = new Scanner(System.in);
    }

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
