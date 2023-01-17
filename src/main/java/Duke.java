import java.util.Scanner;
public class Duke {
    /** Scanner used by each duke */
    private Scanner scanner;
    /** Whether the duke is still running or has been commanded to end */
    private boolean isRunning;

    /**
     * Runs the duke.
     */
    public void run() {
        this.init();
        while (this.isRunning) {
            this.execute(this.readCommand());
        }
        this.exit();
    }

    private void init() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        System.out.println("Hello!");
    }

    private void exit() {
        this.scanner.close();
        System.out.println("GoodBye!");
    }

    private String readCommand() {
        return scanner.next();
    }

    private void execute(String command) {
        if (command.equals("bye")) {
            this.running = false;
        } else {
            System.out.println(command);
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
