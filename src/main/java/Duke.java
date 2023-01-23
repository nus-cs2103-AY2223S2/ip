import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private TaskList list;
    private Parser parser;

    public Duke(Scanner scanner) {
        this.scanner = scanner;
        this.list = new TaskList();
        this.parser = new Parser();
    }

    public void start() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            try {
                Command command = this.parser.parse(input);
                command.execute(list);
                if(command.isExit()) {
                    scanner.close();
                    return;
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Duke duke = new Duke(scanner);
        duke.start();
    }
}
