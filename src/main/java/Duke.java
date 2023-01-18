import controllers.*;
import entities.TaskList;
import exceptions.DukeException;
import storage.Storage;
import parser.Parser;
import views.UI;

import java.util.Scanner;

public class Duke {
    private static TaskList taskList;
    private static Storage storage;
    private static Scanner in;
    private static UI ui;

    public Duke(String filename) {
        storage = new Storage(filename);
        in = new Scanner(System.in);
        ui = new UI();
        try {
            storage.connect();
            taskList = new TaskList(() -> storage.load());
        } catch (DukeException e) {
            System.out.println("There was an error loading the data. Storage will be reset.");
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (in.hasNext() && !isExit) {
            Command cmd = Parser.parse(in.nextLine());
            try {
                cmd.execute();
                if (cmd.isTerminating()) {
                    storage.write(taskList);
                    isExit = true;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
