import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Parser parser;
    private Database db;
    private ArrayList<Task> inputs = new ArrayList<>();

    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();

        try {
            this.db = new Database(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner scanner = ui.getScanner();
        String currInput = scanner.nextLine();
        String[] splitStr = currInput.split(" ", 2);

        ui.showWelcome();
        ui.showLine();

        while(!currInput.equals("bye")) {
            try {
                if(inputs.size() == 0) {
                    db.updateInputs(inputs);
                }
                this.parser.parseInputs(splitStr, inputs, db);
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Mark commands need to be followed by an integer!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(String.format("Sorry but there are only %d tasks stored!", inputs.size()));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
                currInput = scanner.nextLine();
                splitStr = currInput.split(" ", 2);
                ui.showLine();
            }
        }
        ui.showExit();
        scanner.close();
    }

    public static void main(String[] arg) {
        new Duke("data/dukeTasks.txt").run();
    }
}
