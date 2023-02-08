import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetings();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.nextLine();
            Parser.parse(str, tasks);
            storage.writeToFile(tasks.getDukeList());

            if (str.equals("bye")) {
                break;
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
