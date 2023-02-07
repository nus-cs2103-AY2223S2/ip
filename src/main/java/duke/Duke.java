package duke;

import duke.command.Command;
import duke.command.CommandResult;

public class Duke {

    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList list = storage.readTaskList();
        UI.greet();
        do {
            String input = UI.readCommand();
            Command cmd = Parser.parseCommand(input);
            CommandResult cmdResult = cmd.execute(list);
            UI.echo(cmdResult.getResult());
            if (cmd.isExit()) {
                break;
            }
        } while (true);
        storage.writeTaskList(list);
    }
}
