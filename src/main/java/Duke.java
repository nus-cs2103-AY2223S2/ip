import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Duke {

    private TaskList taskList = new TaskList();
    private Storage storage = new Storage();
    private Parser parser = new Parser();

    public Duke() {
        taskList = new TaskList();
        storage = new Storage();
    }

    public void run() {
        System.out.println("Hello! I'm DonkeyChat!\nWhat can I do for you?");
        boolean isRunning = true;
        storage.loadSave(taskList);
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            String currInput = input.nextLine();
            isRunning = parser.parse(currInput, taskList, storage);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
