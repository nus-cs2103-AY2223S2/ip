package baymax;

import tasks.Deadline;
import tasks.Task;
import tasks.Todo;
import tasks.Event;


public class Ui {
    
    public String showLoadingErrorMessage() {
        return ("Unable to load previous tasks");
    }
    public String welcomeMessage() {
        return ("Hello, I am Baymax.Baymax your personal chat bot Companion. \nWhat can I do for you today?");
    }

    public String exitMessage() {
        return ("See you soon!");
    }

    public String printList(TaskList taskList) {
        int i = 1;
        String str = "";
        for (Task s : taskList.getTaskList()) {
            str = str + ((i++) + ": " + s.toString()) + "\n";
        }
        return str;
    }

    public String newToDoMessage(Todo todo) {
        return ("New ToDo task has been added successfully: " + todo.toString());
    }

    public String newDeadlineMessage(Deadline deadline) {
        return ("New Deadline task has been added successfully: " + deadline.toString());
    }

    public String newEventMessage(Event event) {
        return ("New Event task has been added successfully: " + event.toString());
    }

    public String markMessage(Task task) {
        return ("Task marked as done: " + task.toString());
    }

    public String unmarkMessage(Task task) {
        return ("Task marked as not done: " + task.toString());
    }

    public String deleteMessage(Task task) {
        return "Task has been deleted: " + task.toString();
    }
}
