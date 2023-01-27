package runner;
import java.util.Scanner;

public class Duke {
    protected int exit;
    protected final Storage store;
    protected final TaskList taskList;

    public Duke() {
        this.exit = 0;
        this.taskList = new TaskList();
        this.store = new Storage(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        store.loadList();
        Ui.start();
        while (exit != 1) {
            Scanner sc = new Scanner(System.in);
            String info = sc.nextLine();
            new Parser(this).handle(info);
        }
    }
}
