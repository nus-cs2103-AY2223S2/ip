import java.io.*;
public class Genie {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Genie() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = storage.loadData();
        } catch(IOException e) {
            System.out.println(ui.showErrorMessage() + "\n" + e);
        }
    }

    public static void main(String[] args) {
        new Genie().activate();
    }
    public void activate() {
        ui.bootLogo();
        ui.greet();
        boolean isExit = false;
        ui.printLoadedTaskList(storage.getLoadedTaskList());
        while(!isExit) {
            try {
                String i = ui.readCommand();
                ui.printLine();
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
