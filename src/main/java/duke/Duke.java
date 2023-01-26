package duke;


public class Duke {
    public static void main(String[] args) {
        TextUi TextUi = new TextUi();
        Parser Parser = new Parser();
        Storage Storage = new Storage();
        TaskList taskList = Storage.readSavedFile(); // loads the list

        TextUi.getWelcomeMessage();
        String input;

        while (!(input = TextUi.in.nextLine()).equals("bye")) {
            try {
                Parser.parse(input, taskList, Storage, TextUi);
            } catch (DukeException dukeException) {
                TextUi.getCustomMessage(dukeException.getMessage());
            }
        }
        TextUi.getGoodbyeMessage();
    }
}
