import command.*;
import task.Deadline;
import task.Event;
import task.TaskManager;
import task.ToDo;
import ui.WelcomeUI;
import util.DukeException;
import util.Parser;

import java.util.*;

public class ChatBot {
    private Scanner input;
    private TaskManager taskManager;
    private String[] inputArr;


    public ChatBot(Scanner input, WelcomeUI welcomeUI) {
        System.out.println(welcomeUI);
        this.input = input;
        //initialise taskmanager that manages array of tasks
        this.taskManager = new TaskManager();
    }

    public void run() {

        loop: while(input.hasNextLine()) {

            //remove leading and trailing whitespaces
            String ip = input.nextLine().trim();

            //convert user input to String array
            this.inputArr = ip.split(" ", 2);
            int len = inputArr.length;

            //if blank input is given
            if(ip.isBlank()) {
                System.out.println("No command given, please give me one!");
            }

            String command = inputArr[0];
            //String key = inputArr[1];
            try {
                switch (command) {
                    case "":
                        continue;
                    case "list":
                        ListCommand lc = new ListCommand(taskManager);
                        lc.executeCommand();
                        break;
                    case "bye":
                        ByeCommand bc = new ByeCommand();
                        bc.executeCommand();
                        break loop;
                    case "check":
                        //catch ArrayOutOfbounds exception if user enters check(arr len = 1)
                        CheckCommand cc = new CheckCommand(taskManager, inputArr[1]);
                        cc.executeCommand();
                        break;
                    case "uncheck":
                        UncheckCommand uc = new UncheckCommand(taskManager, inputArr[1]);
                        uc.executeCommand();
                        break;
                    case "delete":
                        DeleteCommand dc = new DeleteCommand(taskManager, inputArr[1]);
                        dc.executeCommand();
                        break;
                    case "todo":
                        TodoCommand tc = new TodoCommand(taskManager, inputArr[1]);
                        tc.executeCommand();
                        break;
                    case "event":
                        EventCommand ec = new EventCommand(taskManager, inputArr[1]);
                        ec.executeCommand();
                        break;
                    case "deadline":
                        DeadlineCommand dlc = new DeadlineCommand(taskManager, inputArr[1]);
                        dlc.executeCommand();
                    default:
                        throw new DukeException("Command not recognised, please enter a valid command!");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter an index or description with the command!");
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}
