package duke;//package duke;

import exceptions.TaskException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import duke.parser.Parser;
import duke.command.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, this.ui);
        this.tasks = new TaskList(this.storage, this.ui);
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").userInputs();
    }

    // Allow users to add, mark and un-mark, delete, add tasks (to-do, deadline,
    // event) or show items in a list
    public void userInputs() {

        this.ui.welcomeMessage();
        this.storage.loadFileData();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command c = this.parser.parse(input);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
                this.storage.writeToFile();

            } catch (TaskException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Object pointing to null, please check code");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Check if the index is within the size of the array");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Nothing to mark/unmark!");
            } finally {
                ui.showLine();
            }
        }
    }
}
