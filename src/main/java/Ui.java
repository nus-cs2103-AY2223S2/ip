public class Ui {
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I cannot find the directory!");
    }

    public void showCommandError(String word) {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("Command: " + word);
    }

    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    public void showStored(TaskList tasks) {
        System.out.println("File has been stored!");
        System.out.println(tasks);
    }

    public void showDeleted(Task task) {
        System.out.println("This task has been deleted successfully");
        System.out.println(task);
    }

    public void showDescriptionError(String word) {
        System.out.println("Description is written incorrectly!");
        System.out.println("Command: " + word);
    }
}
