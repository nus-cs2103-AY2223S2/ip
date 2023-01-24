import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private Parser parser;

    public UI(TaskList tasks) {
        this.scanner = new Scanner(System.in);
        this.parser = new Parser(tasks);
    }

    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello i'm\n" + logo + "\nWhat can I do for you?";
        System.out.println(Span.format(greeting));
    }

    public void byeUser() {
        System.out.println(Span.format("Bye. Hope to see you again soon!"));
    }

    public boolean processInput() throws DukeException {
        String echo = scanner.nextLine().trim(); // get user input and trim trailing white sp
        String firstWord = echo.split(" ")[0];
        Command command = Command.get(firstWord);
        boolean exit = this.parser.execute(command, echo);
        return exit;
    }
}