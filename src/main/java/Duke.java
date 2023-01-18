import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /** Scanner used by each duke */
    private Scanner scanner;
    /** Whether the duke is still running or has been commanded to end */
    private boolean isRunning;

    /** ArrayList of tasks */
    private ArrayList<Task> tasks;

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
        this.tasks.add(task);
        System.out.println("Added Task " + task);
    }

    private void showTasks() {
        int index = 1;
        for (Task task : this.tasks) {
           System.out.println(index + ". " + task);
           index += 1;
        }
    }

    private void toggleTask(Task task) {
        task.toggleDone();
        System.out.println("Marked Task " + task + " as " + (task.getIsDone() ? "" : "not ") + "done");
    }

    private void deleteTask(Task task) {
        this.tasks.remove(task);
        System.out.println("Deleted Task " + task);
    }

    private void init() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.tasks = new ArrayList<Task>();
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
                this.toggleTask(this.tasks.get(Integer.parseInt(
                        command.getArg("mark")
                )));
                break;
            case "delete":
                this.deleteTask(this.tasks.get(Integer.parseInt(
                        command.getArg("delete")
                )));
                break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exit() {
        this.scanner.close();
        System.out.println("Good bye!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
