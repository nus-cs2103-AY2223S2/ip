package duke;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private void displayText(String text) {
        System.out.println(text);
    }

    public void replyTaskInfo(Task task) {
        displayText("  " + task.toString());
    }

    public void endChat() {
        displayText("Chat with duke.Duke has ended");
    }

    public void greetUser() {
        displayText("Hello. This is duke.Duke");
    }

    public void replyTotalTasks(TaskList taskList) {
        displayText("Total tasks: " + taskList.getSize());
    }

    public void replyTaskAdded(Task task) {
        displayText("The following task has been added:");
        displayText("  " + task.toString());
    }

    public void replyTaskMarked(Task task) {
        displayText("The following task is marked as done:");
        displayText("  " + task.toString());
    }

    public void replyTaskUnmarked(Task task) {
        displayText("The following task is marked as not done:");
        displayText("  " + task.toString());
    }

    public void replyTaskDeleted(Task task) {
        displayText("The following task has been deleted:");
        displayText("  " + task.toString());
    }

    public void replyEmptyTaskList() {
        displayText("No task stored.");
    }

    public void replyError(String errorMessage) {
        displayText("An error has occurred!");
        displayText(errorMessage);
    }
}
