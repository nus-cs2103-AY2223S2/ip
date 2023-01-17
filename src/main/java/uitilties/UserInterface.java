package uitilties;

import commands.*;
import exceptions.DukeException;
import exceptions.InvalidCommandException;
import tasks.ITask;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static void Speak(String sentence) {
        String dialogSeparator = "____________________________________________________________";
        System.out.println(dialogSeparator);
        System.out.println(sentence);
        System.out.println(dialogSeparator);
    }

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Speak(logo + "\nHello! I'm Duke\nWhat can I do for you?\n");
    }

    public static ICommand receptor(ArrayList<ITask> tasks) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.next().trim();
        String content = sc.nextLine().trim();
        //TODO remark parse here
        switch (cmd) {
            case "bye":
                return new Exit(new Parser(tasks));
            case "list":
                return new ListTasks(new Parser(tasks));

            case "mark":
                return new Mark(new Parser(content, tasks));

            case "unmark":
                return new Unmark(new Parser(content, tasks));
            case "delete":
                return new Delete(new Parser(content, tasks));
            case "todo":
            case "deadline":
            case "event":
                return new Add(new Parser(tasks, content, ITask.convertTaskTypeCmdToEnum(cmd)));
            default:
                throw new InvalidCommandException(cmd);
        }
    }
}
