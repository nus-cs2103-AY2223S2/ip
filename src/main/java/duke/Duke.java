package duke;

import task.TaskList;

import java.io.IOException;
import java.util.Scanner;
public class Duke {

    private Storage storage;
    private Parser parser;
    private UI ui;
    private TaskList list;

    public Duke () {
        ui = new UI();
        list = new TaskList();
        storage = new Storage(list);
        parser = new Parser(storage);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printWelcome();
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            try {
                parser.parseAndExecute(userInput, list);
            } catch (IOException e) {
                ui.printInvalidDateFormatMessage();
                userInput = scanner.nextLine();
                continue;
            }
            ui.printNextCommandMessage();
            userInput = scanner.nextLine();

        }
        System.out.println("Thanks and have a great day ahead!");

    }
    public static void main(String[] args) {
        new Duke().run();

    }
}




