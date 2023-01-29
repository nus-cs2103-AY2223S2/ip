public class Ui {

    public void showLoadingError() {
        System.out.println("Error loading data!");
    }

    public void showIndexError() {
        System.out.println("Error: Index specified must be smaller than current list size.");
    }

    public void showIndexNotSpecifiedError() {
        System.out.println("Error: Index not specified");
    }

    public void showTaskAddedMessage(String taskMessage, int newTasksLength) {
        System.out.println("Got it. I've added this task:");
        showTaskEditedMessage(taskMessage, newTasksLength);

    }


    public void showTaskEditedMessage(String taskMessage, int newTasksLength) {
        System.out.println("\t" + taskMessage);
        System.out.println("Now you have " + newTasksLength + " tasks in the list");
    }


    public void showStartupMessage() {
        String logo = "               _     __,..---\"\"-._                 ';-,\n" +
                "        ,    _/_),-\"`             '-.                `\\\\\n" +
                "       \\|.-\"`    -_)                 '.                ||\n" +
                "       /`   a   ,                      \\              .'/\n" +
                "       '.___,__/                 .-'    \\_        _.-'.'\n" +
                "          |\\  \\      \\         /`        _`\"\"\"\"\"\"`_.-'\n" +
                "             _/;--._, >        |   --.__/ `\"\"\"\"\"\"`\n" +
                "           (((-'  __//`'-......-;\\      )\n" +
                "                (((-'       __//  '--. /\n" +
                "                          (((-'    __//\n" +
                "                                 (((-'";

        String MESSAGE_START = "What can I do for you?";

        System.out.println("Squeak squeak\n" + logo);
        System.out.println(MESSAGE_START);
    }

    public void showRemovedTaskMessage(String taskMessage, int taskLength) {
        System.out.println("Got it. I've removed this task:");
        showTaskEditedMessage(taskMessage, taskLength);
    }

    public void showTasksMessage(String tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    public void showTaskStateCompletedError() {
        System.out.println("Task is already marked as done.");
    }

    public void showTaskCompletedMessage(String taskMessage) {
        System.out.println("Nice! I've marked this task as done:");

        System.out.println("\t" + taskMessage);
    }

    public void showTaskStateIncompletedError() {
        System.out.println("Task is already marked as incompelted.");
    }

    public void showTaskIncompletedMessage(String taskMessage) {
        System.out.println("I have maked this task as incomplete:");
        System.out.println("\t" + taskMessage);

    }

    public void showEmptyDescriptionError() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");

    }

    public void showIncompleteDeadlineArgumentsError() {
        System.out.println("Error: Invalid number of args. Pls add a /by in your command, " +
                "or ensure task name is not not empty");
    }

    public void showIncompleteEventArgumentsError() {
        System.out.println("Error: Invalid number of args. Pls add a /from and /to in your command," +
                " or ensure task name is not not empty");
    }

    public void showUnrecognisedCommandError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

    }

    public void showDateTimeParseError() {
        System.out.println("Error: Unable to parse datetime.");
    }

    public void showUnimplementedTaskTypeError() {
        System.out.println("Error: Task type is not implemented");
    }

    public void showRemoveAllTasksMessage() {
        System.out.println("Deletion complete!");
    }

    public void showNoTasksMessage() {
        System.out.println("There are no tasks to print!");
    }
}
