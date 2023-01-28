import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Duke {
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private TaskList commandList;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private static final File savedFile = new File("savedFile.txt");

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public Duke() {
        ui = new Ui();
        commandList = new TaskList();
        parser = new Parser();
    }

    public void run() {
        //Creating/Loading from storage
        this.storage = new Storage();
        boolean isCreated = savedFile.exists();
        if (isCreated) {
            this.storage.loadFromFile(new File("savedFile.txt"));
            this.commandList.setTaskList(this.storage.getStorage());
        } else {
            try {
                savedFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Unable to create file" + e);
            }
        }

        ui.greetingMessage();
        Scanner scanner = new Scanner(System.in);
        boolean isOver = false;
        while (scanner.hasNext()) {
            String userCommands = scanner.nextLine();
            Command command;
            try {
                command = parser.parse(userCommands, commandList, storage, ui, savedFile);
                command.action();
            } catch (InvalidCmdValueException e) {
                System.out.println(Ui.HORIZONTAL_LINE + "\n" + e.getMessage() + "\n" + Ui.HORIZONTAL_LINE);
            }
        }
    }
}


