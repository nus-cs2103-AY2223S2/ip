import exception.DukeException;
import storage.Storage;
import ui.StringParser;
import ui.Ui;
import tasks.TaskList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private StringParser sp;
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        sp = new StringParser();
        try {
            taskList = new TaskList();
        } catch (DukeException e) {
            ui.errMsg(e.getMessage());
        }
    }

    public void run(){
        boolean isBye = false;
        Scanner sc = new Scanner(System.in);
        ui.welcomeMsg();
        while (!isBye) {
            try {
                String answer = sc.nextLine();
                ui.showLine();
                isBye = sp.parse(answer, taskList);
                storage.updateFile(taskList);
            } catch(DukeException e){
                System.out.println(e.getMessage());
            }
            ui.showLine();
        }

    }
    public static void main(String[] args) {
        new Duke().run();
    }

}
