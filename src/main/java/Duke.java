import command.Command;
import task.TaskManager;
import ui.WelcomeUI;
import util.DukeException;
import util.FileManager;
import java.util.Scanner;
import util.Parser;

/**
 * Represents the main logic flow of the chatbot.
 */
public class Duke {
    private Scanner input;
    private TaskManager taskManager;
    private FileManager fileManager;
    private Parser parser;

    public Duke() {
        this.taskManager = new TaskManager();
        this.fileManager = new FileManager();
        this.fileManager.loadDataToArrayList(this.taskManager);
        this.parser = new Parser(fileManager);
    }

    /**
     * Initialises the Scanner to receive user input
     * and the WelcomeUI to be displayed upon start up.
     * @param input
     * @param welcomeUI
     */
//    public Duke(Scanner input, WelcomeUI welcomeUI) {
//        System.out.println(welcomeUI);
//        this.input = input;
//        this.taskManager = new TaskManager();
//        this.fileManager = new FileManager();
//        this.fileManager.loadDataToArrayList(this.taskManager);
//        this.parser = new Parser(fileManager);
//    }

    /**
     * Driver method to run the chatbot.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String ip = input.nextLine().trim();
                Command command = parser.parse(ip);
                command.executeCommand(taskManager);
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NullPointerException e) {
                isExit = false;
            }
        }
    }

    public static void main(String[] args) {
        WelcomeUI welcome = new WelcomeUI();
        Scanner input = new Scanner(System.in);
        //Duke duke = new Duke(input, welcome);

       // duke.run();
        input.close();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input.trim());
            String res = command.executeCommand(taskManager);
            return res;
        } catch (DukeException e) {
            return e.toString();
        } catch (NullPointerException e) {
            return e.toString();
        }
    }
}
