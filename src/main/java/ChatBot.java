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
                //String fullInput = this.input.nextLine().trim();
                Command command = parser.parse(input);
                command.executeCommand(taskManager);
                isExit = command.isExit();
                System.out.println(isExit);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }


//        loop: while (true) { //previously input.hasNextLine()
//            //remove leading and trailing whitespaces
//            String ip = input.nextLine().trim();
//
//            this.inputArr = ip.split(" ", 2);
//            int len = inputArr.length;
//
//            if (ip.isBlank()) {
//                System.out.println("No command given, please give me one!");
//            }
//
//            String command = inputArr[0];
//            try {
//                switch (command) {
//                case "":
//                    continue;
//                case "list":
//                    ListCommand lc = new ListCommand(taskManager);
//                    lc.executeCommand();
//                    break;
//                case "bye":
//                    ByeCommand bc = new ByeCommand();
//                    bc.executeCommand();
//                    fileManager.saveTasksToFile(taskManager);
//                    break loop;
//                case "find":
//                    FindCommand fc = new FindCommand(taskManager, inputArr[1]);
//                    fc.executeCommand();
//                    break;
//                case "check":
//                    CheckCommand cc = new CheckCommand(taskManager, inputArr[1]);
//                    cc.executeCommand();
//                    break;
//                case "uncheck":
//                    UncheckCommand uc = new UncheckCommand(taskManager, inputArr[1]);
//                    uc.executeCommand();
//                    break;
//                case "delete":
//                    DeleteCommand dc = new DeleteCommand(taskManager, inputArr[1]);
//                    dc.executeCommand();
//                    break;
//                case "todo":
//                    TodoCommand tc = new TodoCommand(taskManager, inputArr[1]);
//                    tc.executeCommand();
//                    break;
//                case "event":
//                    EventCommand ec = new EventCommand(taskManager, inputArr[1]);
//                    ec.executeCommand();
//                    break;
//                case "deadline":
//                    DeadlineCommand dlc = new DeadlineCommand(taskManager, inputArr[1]);
//                    dlc.executeCommand();
//                default:
//                    throw new DukeException("Command not recognised, please enter a valid command!");
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println("Please enter an index or description with the command!");
//            } catch (DukeException e) {
//                System.out.println(e);
//            }
//        }
    }

    public static void main(String[] args) {
        WelcomeUI welcome = new WelcomeUI();
        Scanner input = new Scanner(System.in);
        ChatBot chat = new ChatBot(input, welcome);

        chat.run();
        input.close();
    }
}
