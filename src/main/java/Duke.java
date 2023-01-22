import java.io.IOException;
import java.util.Scanner;

public class Duke {

    final static String LOGO = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";

    final static String BANNER = "____________________________________________________________";

    private final static String FILEPATH = "src/main/data/duke.txt";

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        CommandParser commandParser = new CommandParser();
        TaskList taskList = new TaskList();
        ui.displayWelcomeMessage();

        try {
            taskList = Storage.loadData(taskList, FILEPATH);
        } catch (IOException e) {
            System.out.println("Transmission error, I encountered! Jumping into hyperspace, it might be!");
        }
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String response = ui.readCommand(scanner);
                Command command = commandParser.parse(response);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showLoadingErrorMessage(e);
            } finally {
                System.out.println(BANNER);
            }
        }
        storage.saveData(FILEPATH, taskList);
        System.out.println(BANNER);
    }
}