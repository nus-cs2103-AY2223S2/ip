import java.util.Scanner;

public class Duke {

    private final Ui ui = new Ui();
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(this.taskList);

    private void start() {

        ui.greet();

        Scanner commandScanner = new Scanner(System.in);
        boolean toExit = false;

        while (true) {

            String userCommand = ui.getUserCommand(commandScanner);

            try {
                Command command = parser.parse(userCommand);
                command.execute();
                toExit = command.isExitCommand();

            } catch (DukeException e) {
                System.out.println(e.getMessage());

            } finally {
                ui.endCommand();

            }

            if (toExit) {
                break;
            }

        }

        commandScanner.close();

    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.start();

    }
}
