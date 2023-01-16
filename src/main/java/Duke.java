import java.util.*;

public class Duke {
    private Scanner scanner;
    private TaskList list;
    private Router router;

    public Duke(Scanner scanner) {
        this.scanner = scanner;
        this.list = new TaskList();
        this.router = new Router(list);
    }

    public void start() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            boolean isExit = router.handleAndSignalExit(input);
            if(isExit) {
                this.bye();
                scanner.close();
                return;
            }
        }
    }

    public void bye() {
        System.out.println("Duke: " + "Bye" + ". Hope I never see you again!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Duke duke = new Duke(scanner);
        duke.start();
    }
}
