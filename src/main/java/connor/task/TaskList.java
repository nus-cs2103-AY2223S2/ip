package connor.task;

import connor.ui.Ui;

import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> tasks;

    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(String number, Ui ui) throws InvalidTaskException {
        try {
            int value = Integer.parseInt(number);
            if (number.length() < 1 || value > this.tasks.size() || value <= 0) {
                throw new InvalidTaskException();
            } else {
                Task task = this.tasks.remove(value - 1);
                ui.deleteTaskMessage(task, this.tasks.size());
            }
        } catch (NumberFormatException e){
            throw new InvalidTaskException();
        }
    }

    public void deleteAllTask() {
        this.tasks.clear();
    }

    public void markDone(int number, Ui ui) {
        this.tasks.get(number - 1).mark();
        ui.markDoneMessage(this.tasks.get(number - 1).toString());
    }
    public void markUndone(int number, Ui ui) {
        this.tasks.get(number - 1).unmark();
        ui.markUndoneMessage(this.tasks.get(number - 1).toString());
    }

    public String find(String input) {
        StringBuilder str = new StringBuilder();
        str.append(Ui.LINE).append("        HERE ARE THE MATCHING RESULTS:\n");
        int counter = 1;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getTaskName().contains(input)) {
                str.append("        ")
                        .append(counter++)
                        .append(".")
                        .append(this.tasks.get(i).toString())
                        .append("\n");
            }
        }
        return str.toString();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(Ui.LINE).append("\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append("        ")
                    .append(i + 1)
                    .append(".")
                    .append(this.tasks.get(i).toString())
                    .append("\n");
        }

        str.append("        I have ")
                .append(this.tasks.size())
                .append(" tasks in my memory")
                .append("\n");
        return str.toString();
    }


    public int getSize() {
        return this.tasks.size();
    }

    public LinkedList<Task> getList() {
        return this.tasks;
    }
}
