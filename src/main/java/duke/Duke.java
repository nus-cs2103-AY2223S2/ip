package duke;

import duke.command.Command;

public class Duke {

    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList list = storage.readTaskList();
        UI.greet();
        do {
            try {
                String input = UI.readCommand();
                Command cmd = Parser.parseCommand(input);
                cmd.execute(list);
                if (cmd.isExit()) {
                    break;
                }
            } catch (DukeRuntimeException ex) {
                UI.echoError(ex);
            }
        } while (true);
        storage.writeTaskList(list);
    }
}
