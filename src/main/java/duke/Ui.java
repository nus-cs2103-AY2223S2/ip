package duke;

import duke.exceptions.DukeExceptions;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {
    private final String SEPARATOR = "\u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾";
    public void showWelcome() {
        System.out.println("｡ﾟﾟ･｡･ﾟﾟ｡\n" + "。 welcome to tigerlily to-do\n" + "　ﾟ･｡･ﾟ\n"
                + "✎ . . . . add your tasks here");
    }
    public void showGoodbye() {
        System.out.println("\n(\\\\ (\\\\ \n" + "(„• ֊ •„)\n" + "━━O━O━━━━━━━━━━━━━━━\n" +
                "bye, see you again soon!\n" + "━━━━━━━━━━━━━━━━━━━━\n");
    }

    public void showMessage(String message) {
        System.out.println("\n" + message + SEPARATOR);
    }

    public void showAddedMessage(Task task, TaskList taskList) {
        System.out.println("\nokay perf, your task: " + task.toString() + " has been added to your list\n"
                + "there are now " + taskList.getSize() + " task(s) in your list\n" + SEPARATOR);
    }

    public void showError(DukeExceptions error) {
        System.out.println("\n" + error + SEPARATOR);
    }
}