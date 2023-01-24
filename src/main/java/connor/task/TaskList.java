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
        this.ui.addTaskMessage(task, this.tasks.size());
    }

    public void deleteTask(String number) throws InvalidTaskException {
        if (number.equals("all")) {
            this.ui.deleteAllMessage();
            this.tasks.clear();
        } else {
            try {
                int value = Integer.parseInt(number);
                if (number.length() < 1 || value > this.tasks.size() || value <= 0) {
                    throw new InvalidTaskException();
                } else {
                    Task task = this.tasks.remove(value - 1);
                    this.ui.deleteTaskMessage(task, this.tasks.size());
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
        this.ui.markDoneMessage(this.tasks.get(number - 1).toString());
    }
    public void markUndone(int number) {
        this.tasks.get(number - 1).unmark();
        this.updateFile();
        this.ui.markUndoneMessage(this.tasks.get(number - 1).toString());
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
