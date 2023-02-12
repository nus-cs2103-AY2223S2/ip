package duke.duke;

public class Duke {
    private static Storage storage = new Storage();
    private static TaskList tasks = new TaskList();
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();
//    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ui.welcomeMessage();
        parser.getInput();
    }
}
