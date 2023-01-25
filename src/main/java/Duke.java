import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static TaskList ls = new TaskList();

    public static void main(String[] args) throws DukeException, FileNotFoundException {

        if (Storage.saveExists()) {
            Storage.loadSave(ls);
        } else {
            Storage.createSave();
        }

        Ui.showWelcome();
        Scanner in = new Scanner(System.in);

        boolean isChatting = true;
        while (isChatting) {
            String chat = in.nextLine();
            Ui.respond(chat, ls);
            if (Ui.receivedBye()) {
                try {
                    Storage.saveList(ls);
                } catch (Exception e) {
                    Ui.showError(e);
                }
                break;
            }
        }
        Ui.showBye();
    }
}
