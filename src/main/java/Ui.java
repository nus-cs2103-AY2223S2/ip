public class Ui {
    private void displayText(String text) {
        System.out.println(text);
    }

    public void replyTaskInfo(Task task) {
        this.displayText("  " + task.toString());
    }

    public void endChat() {
        this.displayText("Chat with Duke has ended");
    }

    public void greetUser() {
        this.displayText("Hello. This is Duke");
    }

    public void replyTotalTasks(TaskList taskList) {
        this.displayText("Total tasks: " + taskList.getSize());
    }

    public void replyTaskAdded(Task task) {
        this.displayText("The following task has been added:");
        this.displayText("  " + task.toString());
    }

    public void replyTaskMarked(Task task) {
        this.displayText("The following task is marked as done:");
        this.displayText("  " + task.toString());
    }

    public void replyTaskUnmarked(Task task) {
        this.displayText("The following task is marked as not done:");
        this.displayText("  " + task.toString());
    }

    public void replyTaskDeleted(Task task) {
        this.displayText("The following task has been deleted:");
        this.displayText("  " + task.toString());
    }

    public void replyEmptyTaskList() {
        this.displayText("No task stored.");
    }

    public void replyError(String errorMessage) {
        this.displayText("An error has occurred!");
        this.displayText(errorMessage);
    }
}
