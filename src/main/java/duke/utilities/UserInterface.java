package duke.utilities;

import duke.commands.Add;
import duke.commands.Delete;
import duke.commands.Exit;
import duke.commands.ICommand;
import duke.commands.ListTasks;
import duke.commands.Mark;
import duke.commands.Unmark;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.ITask;

import java.util.Scanner;

public class UserInterface {

    public void Speak(String sentence) {
//        System.out.println(dialogSeparator);
        System.out.println(sentence);
        String _dialogSeparator = "____________________________________________________________";
        System.out.println(_dialogSeparator);
    }
    public void getCommand(String sentence) {
        System.out.println(sentence);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Speak(logo + "\nHello! I'm Duke\nWhat can I do for you?\n");
    }

    public void showLoadingError(String msg) {
        Speak(msg);
    }
    public void showError(String msg) {
        Speak(msg);
    }

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
                return new Add(new Parser(taskManager, content, ITask.convertTaskTypeCmdToEnum(cmd)));
            default:
                throw new InvalidCommandException(cmd);
        }
    }
}
