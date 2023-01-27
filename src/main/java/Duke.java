import java.io.*;
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = storage.loadData();
        } catch(IOException e) {
            System.out.println(ui.showErrorMessage() + "\n" + e);
        }
    }

    public static void main(String[] args) {
        new Duke().activate();
    }
    public void activate() {
        ui.bootLogo();
        ui.greet();
        boolean isExit = false;
        while(!isExit) {
            try {
                //ui.printList(taskList.getTasks());
                String i = ui.readCommand();
                Parser parser = new Parser();
                Command c = parser.parse(i);
                c.execute(taskList, ui, storage);
                isExit = c.isExitCommand();
            } catch(IOException e) {
                System.out.println(ui.showErrorMessage() + "\n" + e);
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
