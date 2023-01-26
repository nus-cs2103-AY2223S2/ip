public class UI {
    protected String indent = "     ";
    protected String divider = indent + "____________________________________________________________";

    public void printWelcomeMessage() {
        System.out.println(divider);
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println(indent + "What can I do for you?");
        System.out.println(divider);
    }

    public void printMessage(String message) {
        System.out.println(divider + "\n" + indent + message + "\n" + divider);
    }

    public void printSuccessMessage(String message, Task task) {
        printMessage(message + "\n" + indent + task.toString());
    }

    public void printTaskMessage(String message, Task task, int numOfTasks) {
        printMessage(message + "\n" +
                indent + indent + task.toString() + "\n" +
                indent + "Now you have " + numOfTasks + " task(s) in the list.");
    }
}