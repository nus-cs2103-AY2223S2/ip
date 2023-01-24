package connor.task;

import connor.storage.Storage;
import connor.ui.Ui;

import java.io.IOException;
import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> tasks;
    private Storage storage;
    private Ui ui;

    public TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
        try {
            this.tasks = storage.initialize();
        } catch (IOException e) {
            this.tasks = new LinkedList<>();
        }
    }

    private void updateFile() {
        this.storage.updateFile(this.tasks);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.updateFile();
        String message = "I have added " + task.getTaskName() + " to my memory\n";
        message = message + "          " + task.toString() + "\n";
        message = message + "        You have " + tasks.size() + " tasks in the list";
        this.ui.printMessage(message);
    }

    public void deleteTask(String number) throws InvalidTaskException {
        if (number.equals("all")) {
            this.ui.printMessage("All tasks on the list have been cleared");
            this.tasks.clear();
        } else {
            try {
                int value = Integer.parseInt(number);
                if (number.length() < 1 || value > this.tasks.size() || value <= 0) {
                    throw new InvalidTaskException();
                } else {
                    Task task = this.tasks.remove(value - 1);
                    String message = "I have removed " + task.getTaskName() + " from my memory\n";
                    message = message + "          " + task.toString() + "\n";
                    message = message + "        You have " + tasks.size() + " tasks in the list";
                    this.ui.printMessage(message);
                }
            } catch (NumberFormatException e){
                throw new InvalidTaskException();
            }
        }
        this.updateFile();
    }

    public void markDone(int number) {
        this.tasks.get(number - 1).mark();
        this.updateFile();
        this.ui.printMessage("Understood, I have marked the task as done:\n"
                + "        "
                + this.tasks.get(number - 1).toString());
    }
    public void markUndone(int number) {
        this.tasks.get(number - 1).unmark();
        this.updateFile();
        this.ui.printMessage("Understood, I have marked the task as undone:\n"
                + "        "
                + this.tasks.get(number - 1).toString());
    }

    public void getList() {
        System.out.println(this.ui.LINE);
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("        "
                    + (i + 1)
                    + "."
                    + this.tasks.get(i).toString());
        }
        System.out.println("        I have " + this.tasks.size() + " tasks in my memory");
    }
}
