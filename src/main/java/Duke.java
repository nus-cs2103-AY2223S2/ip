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
            this.execute(scanner.nextLine());
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
           System.out.println(i + ". " + this.tasks[i]);
        }
    }

    private void toggleTask(Task task) {
        task.toggleDone();
        System.out.println("Task " + task + " marked as " + (task.getIsDone() ? "" : "not ") + "done");
    }
    private void init() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.tasks = new Task[100];
        this.numTasks = 0;
        System.out.println("Hello!");
    }

    private void execute(String input) {
        try {
            Command command = new Command(input);
            switch (command.getType()) {
            case "bye":
                this.isRunning = false;
                break;
            case "todo":
                this.addTask(new Todo(command.getArg("todo")));
                break;
            case "deadline":
                this.addTask(new Deadline(
                        command.getArg("deadline"),
                        command.getArg("by")
                ));
                break;
            case "event":
                this.addTask(new Event(
                        command.getArg("event"),
                        command.getArg("from"),
                        command.getArg("to")
                ));
                break;
            case "list":
                this.showTasks();
                break;
            case "mark":
                this.toggleTask(this.tasks[Integer.parseInt(
                        command.getArg("mark")
                )]);
                break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
