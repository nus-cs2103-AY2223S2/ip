package duke.utility.ui;

import duke.tasklist.TaskList;
import duke.utility.parser.CommandMap;

public class Ui {
    private static final String BRACKETS =
            "_______________________________________________________\n";

    private static String logo =
            " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static String openingMessage = "Welcome to the Duke Bot.\n";

    public static void welcomeMessage() {
        System.out.println(openingMessage + logo);
    }

    public void load(UiMessage msg, TaskList list) {
        String ui = BRACKETS;
        CommandMap type = msg.TYPE;
        switch (type) {
            case list:
                ui += "\n" + list.getTotal() + "\n" + list.toString();
                break;
            case mark:
                ui += "Nice! One Task Down!\n" + msg.task.toString() + "\n";
                break;
            case unmark:
                ui += "One more task added ):\n" + msg.task.toString() + "\n";
                break;
            case delete:
                ui += "Deleted one task \n" + msg.task.toString() + "\n" + list.getTotal();
                break;
            case bye:
                ui += "Bye! See you soon!\n";
                break;
            case find:
                TaskList filteredTasks = list.filter(msg.task.getName());
                ui += "\nHere are the matching tasks in your list:" + "\n"
                        + filteredTasks.toString();
                break;
            default:
                ui += "Added one task!\n" + msg.task.toString() + "\n" + list.getTotal();
        }

        ui += BRACKETS;

        System.out.println(ui);
    }

}
