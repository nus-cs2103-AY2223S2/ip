



public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui appInterface;

    public Duke(String filePath) {
        storage =  new Storage(filePath);
        appInterface = new Ui();

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            appInterface.showError(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        boolean isDone = false;

        appInterface.greetUser();
        while (!isDone) {
            try {
                String fullCommand = appInterface.readCommand();
                appInterface.showLine();
                String reply = Parser.understandInput(fullCommand, fullCommand.split(" "), taskList, storage);
                isDone = appInterface.replyUser(reply);
            } catch (DukeException e) {
                appInterface.showError(e);
            } finally {
                appInterface.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/data/duke.txt").run();


    }
}
