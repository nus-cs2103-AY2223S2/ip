package donkeychat;

public class Ui {

    private String nextOutputString = "";

    public void displayIntro() {
        addToOutput("Hello! I'm DonkeyChat!\nWhat can I do for you?");
    }

    public void displayBye() {
        addToOutput("Adios!");
    }

    public void displayTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            addToOutput("You have no tasks in your list!");
            return;
        }
        addToOutput("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getAtIndex(i);
            addToOutput(i + 1 + "." + task);
        }
    }

    public void displayMatchingTasks(TaskList taskList, String toMatch) {
        if (taskList.getSize() == 0) {
            addToOutput("You have no tasks in your list!");
            return;
        }
        boolean hasFoundMatch = false;
        addToOutput("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getAtIndex(i);
            if(task.description.contains(toMatch)) {
                addToOutput(i + 1 + "." + task);
                hasFoundMatch = true;
            }
        }
        if(!hasFoundMatch) {
            addToOutput("No Tasks with matching Description found!");
        }
    }

    public void displayText(String text) {
        addToOutput(text);
    }

    public void displayMarkTask(boolean marked, Task task) {
        if (true) {
            addToOutput("Nice! I've marked this task as done:");
            addToOutput(String.valueOf(task));
        } else {
            addToOutput("OK, I've marked this task as not done yet:");
            addToOutput(String.valueOf(task));
        }
    }

    public void displayAddTask(TaskList taskList) {
        Task task = taskList.getAtIndex(taskList.getSize() - 1);
        if (task instanceof ToDo) {
            addToOutput("Added Todo task:\n" + task);
            addToOutput("Now you have " + taskList.getSize() + " tasks in the list!");
        } else if (task instanceof Event) {
            addToOutput("Added donkeychat.Event task:\n" + task);
            addToOutput("Now you have " + taskList.getSize() + " tasks in the list!");
        } else if (task instanceof Deadline) {
            addToOutput("Added donkeychat.Deadline task:\n" + task);
            addToOutput("Now you have " + taskList.getSize() + " tasks in the list!");
        }
    }

    public void displayDeleteTask(Task task) {
        addToOutput("Gotcha, removed this task: ");
        addToOutput(String.valueOf(task));
    }

    public void addToOutput(String string) {
        nextOutputString += string + "\n";
    }

    public String getOutput() {
        String cacheString = nextOutputString;
        nextOutputString = "";
        return cacheString;
    }
}
