package duke.ui;

import duke.task.*;

public class Ui {

    public Ui() {
    }

    private final String LINE_BREAK = "\n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";

    public void greet() {
        String LOGO = "             _____             _____   _    _              _   _ \n" +
                "     /\\     |_   _|           / ____| | |  | |     /\\     | \\ | |\n" +
                "    /  \\      | |    ______  | |      | |__| |    /  \\    |  \\| |\n" +
                "   / /\\ \\     | |   |______| | |      |  __  |   / /\\ \\   | . ` |\n" +
                "  / ____ \\   _| |_           | |____  | |  | |  / ____ \\  | |\\  |\n" +
                " /_/    \\_\\ |_____|           \\_____| |_|  |_| /_/    \\_\\ |_| \\_|\n" +
                "                                                                 \n" +
                "                                                                 ";
        System.out.println("\t Hello I'm\n" + LOGO);
        System.out.println("\t What can I do for you?" + LINE_BREAK);
    }

    public void end() {
        System.out.println(LINE_BREAK +
                "\t Bye. See you next time! :)\n" +
                LINE_BREAK);
    }

    public void addTask(Task task, int size) {
        System.out.println(LINE_BREAK + "\t Adding the task:\n\t\t" + task +
                "\n\t You now have " + size + " task(s)." + LINE_BREAK);
    }

    public void deleteMessage(Task item, int size) {
        System.out.println(LINE_BREAK + "\t 1 less task! :)");
        System.out.println("\t\t" + item+ "\n\tNow you have " + (size - 1) + " tasks left!" + LINE_BREAK);
    }

    public void markMessage(Task item) {
        System.out.println(LINE_BREAK + "\t Great job completing your task! :)");
        System.out.println("\t\t" + item + LINE_BREAK);
    }

    public void unmarkMessage(Task item) {
        System.out.println(LINE_BREAK + "\t I see you want to redo this task...");
        System.out.println("\t\t" + item + LINE_BREAK);
    }

    public void print(TaskList taskList) {
        System.out.println(LINE_BREAK + "\tHere are all your tasks, good luck!");
        System.out.print(taskList.toString());
        System.out.println(LINE_BREAK);
    }

    public void showLoadingError() {
        System.out.println(LINE_BREAK + "\tLoading error :(");
        System.out.println(LINE_BREAK);
    }

}
