import java.util.Scanner;
import java.io.IOException;

import storage.TaskList;
import ui.Ui;
import storage.Storage;
import parser.Parser;
import exception.DukeException;

public class Duke {
    private static TaskList list = new TaskList();
    private static Ui ui = new Ui();
    private static Storage storage = new Storage("duke.txt");
    private static Parser logic = new Parser();

    public static void main(String[] args) throws DukeException, IOException {
        ui.printWelcomeMessage();
        storage.loadData(list);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!logic.checkBye(input)) {
            if (logic.checkList(input)) {
                ui.printListMessage();
                list.list();
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
        storage.saveData(list);
        ui.printByeMessage();
        ui.showLine();
    }
}
