import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.Ui;

/**
 * The main class where the programs start
 */
public class Duke {
    private Storage storage;
    private ArrayList<Task> list;

    /**
     * @param filePath indicates the place where the txt file is located at
     * @param list     an arraylist containing all the task
     * @throws IOException throws an exception when the file cannot be found or created
     */
    public Duke(String filePath, ArrayList<Task> list) throws IOException {
        storage = new Storage(filePath);
        Ui userInterface = new Ui();
        userInterface.welcomeMessage();
        storage.loadData(list);
        this.list = list;
    }

    /**
     * A method responsible for running the program containing the loop
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                Parser parser = new Parser();
                String input = scanner.nextLine().trim();
                if (parser.parserInput(list, input)) {
                    storage.saveData(list);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main method of the Duke class
     *
     * @param args takes in the txt file contents, optional
     * @throws IOException if cannot access the txt file
     */
    public static void main(String[] args) throws IOException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        Duke duke = new Duke("./userData/duke.txt", taskArrayList);
        duke.run();
    }
}



