import java.io.IOException;

public class Duke {
    private static TaskList tasks = new TaskList();
    private static String divider = "    ____________________________________________________________";
    private Ui ui;
    private Storage storage;

    public void run(){
        boolean repeat = true;
        while (repeat) {
            String command = ui.nextInput();
            System.out.println(divider);
            try {
                repeat = Parser.handleGeneralCommand(command, tasks);
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.println(divider);
        }
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Unable to save");
        }
        ui.closeCommand();
    }

//    public static void main(String[] args) {
////        Duke d = new Duke("data/", "tasks.txt");
//        boolean repeat = true;
//        Scanner sc = new Scanner(System.in);
//        while (repeat) {
//            String command = sc.nextLine();
//            System.out.println(divider);
//            try {
//                repeat = Parser.handleGeneralCommand(command, tasks);
//            } catch (DukeException e) {
//                System.out.println(e);
//            }
//            System.out.println(divider);
//        }
//        sc.close();
//    }

    public static void main(String[] args) {
        new Duke("src/main/data/", "src/main/data/duke.txt").run();

    }

    public Duke(String fileDir, String filePath) {
        ui = new Ui();
        storage = new Storage(fileDir, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
}
