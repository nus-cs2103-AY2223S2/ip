package donkeychat;

public class Ui {

    public void displayIntro() {
        System.out.println("Hello! I'm DonkeyChat!\nWhat can I do for you?");
    }

    public void displayBye() {
        System.out.println("Adios!");
    }

    public void displayTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("You have no tasks in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getAtIndex(i);
            System.out.println(i + 1 + "." + task);
        }
    }

    public void displayText(String text) {
        System.out.println(text);
    }

    public void displayMarkTask(boolean marked, Task task) {
        if (true) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
        }
    }

    public void displayAddTask(TaskList taskList) {
        Task task = taskList.getAtIndex(taskList.getSize() - 1);
        if (task instanceof ToDo) {
            System.out.println("Added Todo task:\n" + task);
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list!");
        } else if (task instanceof Event) {
            System.out.println("Added donkeychat.Event task:\n" + task);
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list!");
        } else if (task instanceof Deadline) {
            System.out.println("Added donkeychat.Deadline task:\n" + task);
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list!");
        }
    }

    public void displayDeleteTask(Task task) {
        System.out.println("Gotcha, removed this task: ");
        System.out.println(task);
    }
}
