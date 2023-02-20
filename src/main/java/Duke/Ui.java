package Duke;

import Duke.Tasks.Task;

public class Ui {
    public Ui() {
    }

    /**
     * Greet to the user when first run the program.
     */
    public static String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String output = String.format("Hello from\n%s \nWhat " +
                "can I do for you?", logo);
        System.out.println(output);
        return output;
    }

    public String printMarked(Task task) {
        String output = String.format("Nice! I've marked this task as done:\n%s", task);
        System.out.println(output);
        return output;
    }

    public String printUnmarked(Task task) {
        String output = String.format("Nice! I've marked this task as not done yet:\n%s", task);
        System.out.println(output);
        return output;
    }

    public String printDeletedTask(Task task) {
        String output = String.format("This task has been deleted successfully\n%s", task.toString());
        System.out.println(output);
        return output;
    }

    public String printLoadingError() {
        String output = "The directory is not found!";
        System.out.println(output);
        return output;
    }

    public String printStoredTasks(TaskList taskList) {
        String output = String.format("File has been stored!\n%s",taskList.toFormattedString());
        System.out.println(output);
        return output;
    }

    public String printAddedTask(Task task, TaskList tasks) {
        String output = String.format("Got it. I've added this task:\n%s", task.toString());
        output += String.format("\nNow you have %d tasks in the list.", tasks.getSize());
        System.out.println(output);
        return output;
    }

    public String printAllTasks(TaskList tasks) {
        String output = tasks.toFormattedString();
        System.out.println(output);
        return output;
    }

    /**
     * Say goodbye to the user when "bye" Duke.command detected.
     */
    public String bye() {
        String output = String.format("Bye. Hope to see you again soon!\n");
        System.out.println(output);
        return output;
    }
}
