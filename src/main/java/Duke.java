import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.Ui;
import duke.Parser;

public class Duke {
    Storage storage;
    ArrayList<Task> list;

    public Duke(String filePath, ArrayList<Task> list) throws IOException {
        storage = new Storage(filePath);
        Ui userInterface = new Ui();
        userInterface.welcomeMessage();
        storage.loadData(list);
        this.list = list;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

            while (true) {
                try {
                    Parser parser = new Parser();
                    String input = scanner.nextLine().trim();
                    if (parser.parserInput(list, input)) {
                        storage.saveData(list);
                    }
                }catch (DukeException e) {
                    System.out.println(e.message);
                }
            }
        }


    public static void main(String[] args) throws IOException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        Duke duke = new Duke("./userData/duke.txt", taskArrayList);
        duke.run();
    }
}



