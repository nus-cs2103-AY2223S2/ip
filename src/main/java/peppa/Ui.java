package peppa;

import java.util.Scanner;

import peppa.commands.DeadlineCommand;
import peppa.commands.DeleteCommand;
import peppa.commands.EventCommand;
import peppa.commands.ExitCommand;
import peppa.commands.ListCommand;
import peppa.commands.MarkCommand;
import peppa.commands.TodoCommand;
import peppa.commands.UnmarkCommand;
public class Ui {
    public static final String DIVIDER = "=============================================";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void displayAddTaskMessage(Task task) {
        System.out.println("Oink! I've added the following task:");
        System.out.println("> " + task.toString());
    }

    public static void displayCommands() {
        System.out.println("> " + ListCommand.COMMAND_WORD + "\n"
                + "> " + MarkCommand.COMMAND_WORD + "\n"
                + "> " + UnmarkCommand.COMMAND_WORD + "\n"
                + "> " + TodoCommand.COMMAND_WORD + "\n"
                + "> " + DeadlineCommand.COMMAND_WORD + "\n"
                + "> " + EventCommand.COMMAND_WORD + "\n"
                + "> " + DeleteCommand.COMMAND_WORD + "\n"
                + "> " + ExitCommand.COMMAND_WORD);
    }

    public static void displayDeleteTaskMessage(Task task) {
        System.out.println("Oink! I've removed the following task:");
        System.out.println("> " + task.toString());
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displayMarkDoneMessage(Task task) {
        System.out.println("Oink! I've marked this task as done:");
        System.out.println("> " + task.toString());
    }

    public static void displayTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list currently:");
        for (int i = 0; i < tasks.getLength(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.retrieveTask(i));
        }
    }

    public static void displayTaskSummary(TaskList tasks) {
        System.out.println("You now have " + tasks.getLength() + " tasks in the list.");
    }

    public static void displayUnmarkDoneMessage(Task task) {
        System.out.println("Oink! I've marked this task as undone:");
        System.out.println("> " + task.toString());
    }

    public static void greetUser() {
        String logo = " ____  ____  ____  ____   __\n"
                + "(  _ \\(  __)(  _ \\(  _ \\ / _\\\n"
                + " ) __/ ) _)  ) __/ ) __//    \\\n"
                + "(__)  (____)(__)  (__)  \\_/\\_/\n";
        String art = "       _\n"
                + "  <`--'\\>______\n"
                + "  /. .  `'     \\\n"
                + " (`')  ,        @\n"
                + "  `-._,        /\n"
                + "     )-)_/--( >  jv\n"
                + "    ''''  ''''\n";
        System.out.println(DIVIDER);
        System.out.println("Oink! I'm\n" + logo + art);
        System.out.println("Nice to meet you! How can I assist you today?");
        System.out.println(DIVIDER);
    }

    public static void insertDivider() {
        System.out.println(DIVIDER);
    }

    public void terminateSession() {
        System.out.println("Oink oink! See you again :)");
        this.sc.close();
    }

    public String readCommand() {
        return this.sc.nextLine();
    }
}
