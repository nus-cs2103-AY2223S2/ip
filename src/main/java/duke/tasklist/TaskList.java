package duke.tasklist;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;

import duke.storage.Storage;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private static final String TASKS_FILE_PATH = "data/duke_tasks.txt";

    private final Storage taskStorage = new Storage(TASKS_FILE_PATH);

    private final ArrayList<Task> tasks = taskStorage.readTasksFromFile();

    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("You do not have any tasks added to the list.");

        } else {
            System.out.println("Listing all tasks...");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println((i + 1) + ") " + this.tasks.get(i));
            }

        }

    }

    private void markUnmark(int taskNumber, boolean toMark) throws DukeException {
        boolean isValidTaskNumber =
                (taskNumber > 0 && taskNumber <= this.tasks.size());

        if (isValidTaskNumber) {
            if (toMark) {
                this.tasks.get(taskNumber - 1).mark();
                System.out.println("I have marked Task " + taskNumber + " as done.");
                System.out.println(this.tasks.get(taskNumber - 1));

            } else {
                this.tasks.get(taskNumber - 1).unmark();
                System.out.println("I have marked Task " + taskNumber + " as undone.");
                System.out.println(this.tasks.get(taskNumber - 1));

            }

            this.taskStorage.writeTasksToFile(this.tasks);

        } else {
            throw new DukeInvalidArgumentException("Sorry... That is an invalid task number :/");
        }

    }

    public void markTask(int taskNumber) throws DukeException {
        this.markUnmark(taskNumber, true);
    }

    public void unmarkTask(int taskNumber) throws DukeException {
        this.markUnmark(taskNumber, false);
    }

    public void addTask(Task task, String taskType) {
        this.tasks.add(task);
        this.taskStorage.writeTasksToFile(this.tasks);
        System.out.println("I have added the " + taskType + " to the list :)");
        System.out.println(task);

    }

    public void deleteTask(int taskNumber) throws DukeException {
        boolean isValidTaskNumber =
                (taskNumber > 0 && taskNumber <= this.tasks.size());

        if (isValidTaskNumber) {
            Task removedTask = this.tasks.remove(taskNumber - 1);
            this.taskStorage.writeTasksToFile(this.tasks);
            System.out.println("I have removed Task " + taskNumber + " from the list.");
            System.out.println(removedTask);
            System.out.println("You now have " + this.tasks.size() + " task(s) in the list.");

        } else {
            throw new DukeInvalidArgumentException("Sorry... That is an invalid task number :/");
        }

    }

    public void findKeywordInTasks(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            String taskDescription = task.getDescription().toLowerCase();

            int findIndex = taskDescription.indexOf(keyword.toLowerCase());

            if (findIndex != -1) {
                tasksWithKeyword.add(task);
            }

        }

        if (tasksWithKeyword.size() == 0) {
            System.out.println("I could not find any tasks with the keyword '"
                    + keyword + "' :/");

        } else {
            System.out.println("Listing all tasks with the keyword '"
                    + keyword + "'...");
            for (int i = 0; i < tasksWithKeyword.size(); i++) {
                System.out.println((i + 1) + ") " + tasksWithKeyword.get(i));
            }

        }


    }

}
