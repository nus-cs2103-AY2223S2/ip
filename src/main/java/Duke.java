import Storage.LocalStorage;
import Storage.TaskList;
import java.util.Scanner;

public class Duke {

    private TaskList tasks;
    private LocalStorage localStorage;

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    public void run() {
        UI.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equalsIgnoreCase("bye")) {
            Request request = new Request(input, tasks);
            UI.printRes(request.toString());
            input = sc.nextLine();
        }
        localStorage.saveFile(tasks);
        sc.close();
        UI.exit();
    }

    public Duke(String file_path) {
        TaskList tasks = new TaskList();
        this.localStorage = new LocalStorage(file_path);
        this.localStorage.loadTasks(tasks);
        this.tasks = tasks;
    }
    /**
     * Function to handle the user's request
     */
    public static void handleRequest() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        TaskList tasks = new TaskList();
        while(!input.equalsIgnoreCase("bye")) {
            Request request = new Request(input, tasks);
            UI.printRes(request.toString());
            input = sc.nextLine();
        }
        sc.close();
    }
}
