import java.util.Scanner;
public class Duke {
    /** Scanner used by each duke */
    private Scanner scanner;
    /** Whether the duke is still running or has been commanded to end */
    private boolean isRunning;

    /** Array of tasks */
    private Task[] tasks;

    /** Number of tasks stored */
    private int numTasks;

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

    private void addTask(Task task) {
        this.tasks[this.numTasks] = task;
        this.numTasks += 1;
        System.out.println("added " + task);
    }

    private void showTasks() {
        for (int i = 0; i < this.numTasks; i++) {
           System.out.println(i + ". " + this.tasks[i].getStatusIcon()
                   + " " + this.tasks[i]);
        }
    }

    private void init() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.tasks = new Task[100];
        this.numTasks = 0;
        System.out.println("Hello!");
    }

   private String readCommand() {
        return scanner.nextLine();
    }

    private void execute(String command) {
        if (command.equals("bye")) {
            this.isRunning = false;
        } else if (command.equals("list")) {
            this.showTasks();
        } else if (command.startsWith("mark")) {
            this.tasks[Integer.parseInt(command.substring(5))].toggleDone();
        } else {
            this.addTask(new Task(command));
        }
    }

    private void exit() {
        this.scanner.close();
        System.out.println("GoodBye!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
