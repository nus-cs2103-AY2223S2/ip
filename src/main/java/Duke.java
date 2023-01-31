import java.util.Scanner;

public class Duke {

    public static void start() {
        Ui ui = new Ui();
        ui.greet();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.loadTaskList());

        // Ready for commands
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                ui.exit();
                break;
            }
            try {
                Parser.processCommand(input, storage, tasks);

            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        start();
    }
}
