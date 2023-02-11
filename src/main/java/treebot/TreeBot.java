package treebot;

import commands.Command;
import exception.TreeBotException;

import tasks.TaskFactory;
import tasks.TaskList;
import utils.Parser;
import utils.Storage;
import utils.Ui;

import java.io.FileNotFoundException;

public class TreeBot {
    private static final String EXIT_TOKEN = "bye";
    private TaskList taskList;
    private Ui ui = new Ui();
    private Storage storage;
    private Parser parser = new Parser(new TaskFactory());

    public TreeBot(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }

    }

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);

                if (c.equals(EXIT_TOKEN)) {
                    break;
                }
                System.out.println(c.execute(taskList, ui, storage));
            } catch (TreeBotException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (TreeBotException e) {
            return e.getMessage();
        }
    }


    public static void main(String[] args) {
        TreeBot treeBot = new TreeBot("data/treebot.txt");
        treeBot.run();
    }
}
