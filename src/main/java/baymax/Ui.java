package baymax;

import tasks.Deadline;
import tasks.Task;
import tasks.Todo;
import tasks.Event;


public class Ui {
    // deals with interactions with the user

    public void showLoadingErrorMessage() {
        System.out.println("Unable to load previous tasks");
    }
    public void welcomeMessage() {
        System.out.println("Hello, I am Baymax.Baymax your personal chat bot Companion. \nWhat can I do for you today?");
    }

    public void exitMessage() {
        System.out.println("See you soon!");
    }

    public void printList(TaskList taskList) {
        int i = 1;
            for (Task s : taskList.getTaskList()) {
                System.out.println((i++) + ": " + s.toString());
            }
    }

    public void newToDoMessage(Todo todo) {
        System.out.println("New ToDo task has been added successfully: " + todo.toString());
    }

    public void newDeadlineMessage(Deadline deadline) {
        System.out.println("New Deadline task has been added successfully: " + deadline.toString());
    }

    public void newEventMessage(Event event) {
        System.out.println("New Event task has been added successfully: " + event.toString());
    }

    public void markMessage(Task task) {
        System.out.println("Task marked as done: " + task.toString());
    }

    public void unmarkMessage(Task task) {
        System.out.println("Task marked as not done: " + task.toString());
    }
}
