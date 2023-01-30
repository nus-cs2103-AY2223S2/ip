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
        ui.welcomeMessage();
        storage.loadData(list);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (logic.isValidCommand(input)) {
            if (logic.checkList(input)) {
                ui.listMessage();
                list.list();
            } else if (logic.checkMark(input)) {
                int num = Integer.parseInt(input.split(" ")[1]);
                list.mark(num);
                ui.markMessage(list.get(num - 1));
            } else if (logic.checkUnmark(input)) {
                int num = Integer.parseInt(input.split(" ")[1]);
                list.unmark(num);
                ui.unmarkMessage(list.get(num - 1));
           } else if (logic.checkDelete(input)) {
                int num = Integer.parseInt(input.split(" ")[1]);
                list.delete(num);
                ui.deleteMessage(list.get(num - 1), list);
            } else if (logic.checkTask(input)) {
                list.add(input);
                ui.addMessage(list.getLast(), list);
            } else if (logic.checkBye(input)) {
                storage.saveData(list);
                ui.byeMessage();
                break;
            }
            input = sc.nextLine();
        }
        while (!logic.isValidCommand(input)) {
            ui.invalidCommandMessage();
            input = sc.nextLine();
        }
    }
}
