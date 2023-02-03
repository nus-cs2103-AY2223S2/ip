import command.ByeCommand;
import command.CheckCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.TodoCommand;
import command.UncheckCommand;

import task.TaskManager;

import ui.WelcomeUI;

import util.DukeException;
import util.FileManager;

import java.util.Scanner;

import util.Parser;

/**
 * Represents the main logic flow of the chatbot.
 */
public class ChatBot {
    private Scanner input;
    private TaskManager taskManager;
    private String[] inputArr;
    private FileManager fileManager;
    private Parser parser;

    /**
     * Initialises the Scanner to recieve user input
     * and the WelcomeUI to be displayed upon start up.
     * @param input
     * @param welcomeUI
     */
    public ChatBot(Scanner input, WelcomeUI welcomeUI) {
        System.out.println(welcomeUI);
        this.input = input;
        this.taskManager = new TaskManager();
        this.fileManager = new FileManager();
        this.fileManager.loadDataToArrayList(this.taskManager);
        this.parser = new Parser(fileManager);
    }

    /**
     * Driver method to run the chatbot.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = parser.parse(input);
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
        ChatBot chat = new ChatBot(input, welcome);

        chat.run();
        input.close();
    }
}
