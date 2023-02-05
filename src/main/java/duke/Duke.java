package duke;

import java.util.Scanner;
import java.io.IOException;

import duke.storage.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.parser.Parser;
import duke.exception.DukeException;
import duke.command.ListCommand;
import duke.command.FindCommand;
import duke.command.DeleteCommand;
import duke.command.UnmarkCommand;
import duke.command.Command;
import duke.command.MarkCommand;

public class Duke {
    static TaskList list;
    private static final Ui ui = new Ui();
    private static final Parser logic = new Parser();

    Storage storage;


    public Duke(String data, String dataPath) {
        this.storage = new Storage(data, dataPath);
        try {
            list = storage.loadData();
        } catch (DukeException | IOException e) {
            // ui.showLoadingError();
            list = new TaskList();
        }
    }
    public static void main(String[] args) throws DukeException, IOException {
        ui.printWelcomeMessage();
        ui.showLine();
        list = Storage.loadData();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!logic.checkBye(input)) {
            if (logic.checkList(input)) {
                ui.printListMessage();
                list.list();
                ui.showLine();
            } else if (logic.checkFind(input)) {
                String word = input.split(" ")[1];
                ui.printFindMessage();
                list.find(word);
                ui.showLine();
            } else if (logic.checkMark(input)) {
                int num = Integer.parseInt(input.split(" ")[1]);
                list.mark(num);
                ui.printMarkMessage(list.get(num));
                ui.showLine();
            } else if (logic.checkUnmark(input)) {
                int num = Integer.parseInt(input.split(" ")[1]);
                list.unmark(num);
                ui.printUnmarkMessage(list.get(num));
                ui.showLine();
           } else if (logic.checkDelete(input)) {
                int num = Integer.parseInt(input.split(" ")[1]);
                ui.printDeleteMessage(list.get(num), list);
                list.removeFind(list.get(num));
                list.delete(num);
                ui.showLine();
            } else if (logic.checkTask(input)) {
                list.add(input);
                ui.printAddMessage(list.getLast(), list);
                ui.showLine();
            } else if (!logic.isValidCommand(input)) {
                ui.printInvalidCommandMessage();
                ui.showLine();
            }
            input = sc.nextLine();
        }
        Storage.saveData(list);
        ui.printByeMessage();
        ui.showLine();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        Command cmd;
        if (logic.checkList(input)) {
            cmd = new ListCommand();
        } else if (logic.checkFind(input)) {
            String word = input.split(" ")[1];
            cmd = new FindCommand(word);
        } else if (logic.checkMark(input)) {
            int num = Integer.parseInt(input.split(" ")[1]);
            cmd = new MarkCommand(num);
        } else if (logic.checkUnmark(input)) {
            int num = Integer.parseInt(input.split(" ")[1]);
            cmd = new UnmarkCommand(num);
        } else if (logic.checkDelete(input)) {
            int num = Integer.parseInt(input.split(" ")[1]);
            cmd = new DeleteCommand(num);
        } else if (logic.checkTask(input)) {
            list.add(input);
            return ui.printAddMessage(list.getLast(), list);
        } else if (!logic.isValidCommand(input)) {
            return ui.printInvalidCommandMessage();
        } else {
            return ui.printByeMessage();

        }
        return cmd.execute(list, ui);
    }

}
