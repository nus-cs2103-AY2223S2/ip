package sam;

import java.nio.file.Path;

import sam.command.Command;
import sam.command.ExitCommand;
import sam.parser.Parser;
import sam.storage.SamLoadFailedException;
import sam.storage.Storage;
import sam.task.TaskList;

public class Sam {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private boolean isLive;

    public Sam(Path savePath) {
        ui = new Ui();
        storage = new Storage(savePath);
        tasks = new TaskList();
        isLive = false;
    }

    public void run() {
        isLive = true;
        ui.showLogo();
        ui.talk("Hello, I am Sam!");
        try {
            storage.load(tasks);
        } catch (SamLoadFailedException e) {
            ui.talk(e.getMessage());
        }
        while (isLive) {
            String input = ui.acceptInput();
            try {
                Command c = Parser.parseCommand(input);
                c.execute(tasks, ui, storage);
                isLive = !(c instanceof ExitCommand);
            } catch (SamException e) {
                ui.talk(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Path savePath = Path.of("data", "sam.txt");
        new Sam(savePath).run();
    }
}
