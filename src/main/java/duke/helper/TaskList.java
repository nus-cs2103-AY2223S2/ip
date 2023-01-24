package duke.helper;

import duke.exception.InvalidDateTimeException;
import duke.storage.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> inputs = new ArrayList<>();
    private Database db;
    private Ui ui;
    private int size;

    public TaskList(Database db, Ui ui) throws IOException, InvalidDateTimeException {
        this.db = db;
        this.ui = ui;
        this.size = 0;
        this.updateInputs();
    }

    public void addToTasks(Task task) {
        this.inputs.add(task);
        this.size++;
    }

    public void outputList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size; i++) {
            System.out.println((i + 1) + "." + this.getTask(i));
        }
    }

    public void deleteTask(int taskNo) throws IOException {
        Task task = this.getTask(taskNo);
        this.inputs.remove(taskNo);
        this.db.updateDatabase(inputs);
        this.size--;
        ui.showDelete(task, this.size);
    }

    public int getSize() {
        return this.size;
    }

    public Task getTask(int taskNo) {
        return this.inputs.get(taskNo);
    }

    public void updateInputs() throws IOException, InvalidDateTimeException {
        Scanner scanner = new Scanner(db.getFile());
        while(scanner.hasNextLine()) {
            String task = scanner.nextLine();

            char taskType = task.charAt(2);
            boolean isMark = task.charAt(5) == 'X';

            switch (taskType) {
                case 'T':
                    inputs.add(new Todo(task.substring(8)));
                    break;
                case 'D':
                    inputs.add(Deadline.createDeadline(task.substring(8)));
                    break;
                case 'E':
                    inputs.add(Event.createEvent(task.substring(8)));
                    break;
            }

            if(isMark) {
                inputs.get(this.size - 1).setIsDone(true);
            }
        }
    }

    public void mark(boolean isDone, String taskId) {
        int taskNo = Integer.parseInt(taskId) - 1;
        Task taskToMark = this.getTask(taskNo);
        taskToMark.setIsDone(isDone);
        this.ui.showMark(isDone, taskToMark);
    }

    public void handleTaskOutput() throws IOException {
        Task task = this.getTask(this.size - 1);
        this.ui.showTaskOutput(task, this.size);
        this.db.appendToFile(task.toString());
    }
}
