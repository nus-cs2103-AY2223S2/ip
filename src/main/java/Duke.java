import java.util.Scanner;
import java.io.File;
import java.io.IOException;


import parser.Parser;
import storage.Storage;
import tasklist.Tasklist;
import ui.Ui;

/**
 * The Duke program implements a 'to-do' list
 * where users can add, mark and delete tasks 
 */

public class Duke {

    public static void main(String[] args) {

        Ui.showWelcomeMessage();

        Tasklist tasklist = new Tasklist();
        try {
            File saveFile = new File("src/duke.txt");
            if (saveFile.createNewFile()) {
                System.out.println("Save file created: " + saveFile.getName());
            } else {
                tasklist = Storage.load();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        


        boolean loop = true;
        while (loop) {
            Scanner echoScanner = new Scanner(System.in);
            String msg = echoScanner.nextLine();

            loop = Parser.receiveCommand(tasklist, msg, echoScanner, loop);
        }
    }
}
