package duke;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Command command;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        command = new Command();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() throws DukeException {
        this.ui.sendGreetingsMessage();
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            this.command.executeCommand(this.parser.getCommand(userInput), userInput, tasks);
            myObj = new Scanner(System.in);
            userInput = myObj.nextLine();
        }
        storage.saveData(tasks);
        ui.sendGoodByeMessage();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/file.txt").run();
    }

}