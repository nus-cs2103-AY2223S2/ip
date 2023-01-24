public class Duke {

    public static void main(String[] args) {
        TaskList list = new TaskList();
        UI.greet();
        do {
            try {
                String input = UI.readCommand();
                Command cmd = Parser.parseCommand(input);
                cmd.execute(list);
                if (cmd.isExit()) {
                    break;
                }
            } catch (DukeException ex) {
                UI.echoError(ex);
            }
        } while (true);
    }
}
