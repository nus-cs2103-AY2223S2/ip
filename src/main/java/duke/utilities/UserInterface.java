package duke.utilities;

import java.util.Scanner;

import duke.commands.Add;
import duke.commands.Delete;
import duke.commands.Exit;
import duke.commands.Find;
import duke.commands.ICommand;
import duke.commands.ListTasks;
import duke.commands.Mark;
import duke.commands.Unmark;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.ITask;

/**
 * UserInterface class to interact with user
 * take in input from user and export output to user
 */
public class UserInterface {

    /**
     * Speak function to print the content onto the display
     * in pre-define format
     *
     * @param sentences to print
     */
    public void speak(String... sentences) {
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
        String dialogSeparator = "____________________________________________________________";
        System.out.println(dialogSeparator);
    }

    /**
     * The function to ask user to input command
     *
     * @param sentence type of command to ask the user to input
     */
    public void getCommand(String sentence) {
        System.out.println(sentence);
    }

    /**
     * The function to show when first start up the Duke and welcome the user
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        speak(logo, "Hello! I'm Duke", "What can I do for you?");
    }

    /**
     * The function to show to error loading msg
     *
     * @param msg to be print
     */
    public void showLoadingError(String msg) {
        speak(msg);
    }

    /**
     * The function to show to error msg
     *
     * @param msg to be print
     */
    public void showError(String msg) {
        speak(msg);
    }

    /**
     * The function to read the user command
     *
     * @param taskManager hand over the command to task manager
     * @throws DukeException IF error occur
     */
    public ICommand readCommand(TaskManager taskManager) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.next().trim();
        String content = sc.nextLine().trim();
        switch (cmd) {
        case "bye":
            return new Exit(new Parser(taskManager));
        case "list":
            return new ListTasks(new Parser(taskManager));

        case "mark":
            return new Mark(new Parser(content, taskManager));

        case "unmark":
            return new Unmark(new Parser(content, taskManager));
        case "delete":
            return new Delete(new Parser(content, taskManager));
        case "todo":
        case "deadline":
        case "event":
            return new Add(new Parser(content, taskManager, ITask.convertTaskTypeCmdToEnum(cmd)));
        case "find":
            return new Find(new Parser(content, taskManager, ITask.convertTaskTypeCmdToEnum(cmd)));
        default:
            throw new InvalidCommandException(cmd);
        }
    }
}
