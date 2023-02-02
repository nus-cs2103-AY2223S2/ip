package duke;

import java.util.Scanner;
import Storage.Storage;
import Task.Task;
import TaskList.TaskList;
import Exception.*;
import Ui.Ui;
import Parser.Parser;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (dukeException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        String echo;
        Scanner scan = new Scanner(System.in);

        while (true) {
            echo = scan.nextLine();

            if (echo.equals("bye")) {
                break;
            }

            if(Parser.parseCommands(echo, tasks, ui)) {
                continue;
            }

            Task item;

            // Create a separate function in order to assign to item;
            try {
                item = Parser.parseEcho(echo);
            } catch (dukeException e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
                continue;
            }

            tasks.addTask(item);

            ui.showAddedMessage(item);
            ui.printListNumber(tasks.getList());
        }

        ui.showSavingMessage();

        storage.save(tasks.getList());


        ui.showSavedMessage();

        scan.close();

        ui.showClosingMessage();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
