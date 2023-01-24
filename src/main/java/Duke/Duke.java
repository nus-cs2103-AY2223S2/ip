package Duke;
import Duke.Command.Command;
import Duke.DukeException.DukeException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public Duke() {}

    public void run() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        Storage storage = new Storage();
        Parser parser = new Parser();
        storage.populate(tl);
        ui.showIntro();
        while (true){
            try {
                String str = sc.nextLine();
                Command command = parser.parse(str);
                if (Objects.equals(command, null)) {
                    break;
                }
                command.execute(tl, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }

        }
        ui.showOutro();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
