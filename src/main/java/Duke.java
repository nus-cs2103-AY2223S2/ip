import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(Path foldPath, Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath.toString());
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            createFile(foldPath, filePath);
            tasks = new TaskList();
        }
    }

    public void createFile(Path foldPath, Path filePath) {
        try {
            if (!Files.isDirectory(foldPath)) {
                Files.createDirectory(foldPath);
                Files.createFile(filePath);
            } else if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            ui.showFileError();
        }
    }

    public void run() throws DukeException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Parser.parse(fullCommand, ui, tasks, storage);
            isExit = ui.getIsExit();
        }
    }

    public static void main(String[] args) throws DukeException {
        String fileSep = System.getProperty("file.separator");
        String userDir = System.getProperty("user.dir");
        Path foldPath = Paths.get( userDir + fileSep + "data");
        Path filePath = Paths.get(foldPath + fileSep + "duke.txt");
        new Duke(foldPath, filePath).run();
    }
}
