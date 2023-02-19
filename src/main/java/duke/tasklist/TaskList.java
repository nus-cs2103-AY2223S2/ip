package duke.tasklist;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;

import duke.storage.Storage;

import duke.task.Priority;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a task list manager that aids in storing and manipulating the
 * list of Tasks.
 */
public class TaskList {

    private static final String TASKS_FILE_PATH = "data/duke_tasks.txt";

    private final Storage taskStorage = new Storage(TASKS_FILE_PATH);

    private final ArrayList<Task> tasks = taskStorage.readTasksFromFile();

    /**
     * Returns all the Tasks in the list of Tasks.
     * 
     * @return The String response of the chatbot.
     */
    public String listTasks() {
        if (this.tasks.size() == 0) {
            return "You do not have any tasks added to the list.\n";

        } else {
            StringBuilder response = new StringBuilder("Listing all tasks...\n");
            for (int i = 0; i < this.tasks.size(); i++) {
                response.append(i + 1)
                        .append(") ")
                        .append(this.tasks.get(i))
                        .append("\n");
            }

            return response.toString();

        }

    }

    private String markUnmark(int taskNumber, boolean isMarkCommand) throws DukeException {
        boolean isValidTaskNumber = (taskNumber > 0
                && taskNumber <= this.tasks.size());

        if (isValidTaskNumber) {
            StringBuilder response = new StringBuilder();
            if (isMarkCommand) {
                this.tasks.get(taskNumber - 1).mark();
                response.append("I have marked Task ")
                        .append(taskNumber)
                        .append(" as done.\n")
                        .append(this.tasks.get(taskNumber - 1))
                        .append("\n");

            } else {
                this.tasks.get(taskNumber - 1).unmark();
                response.append("I have marked Task ")
                        .append(taskNumber)
                        .append(" as undone.\n")
                        .append(this.tasks.get(taskNumber - 1))
                        .append("\n");

            }

            this.taskStorage.writeTasksToFile(this.tasks);
            return response.toString();

        } else {
            throw new DukeInvalidArgumentException("Sorry... That is an invalid task number :/");
        }

    }

    /**
     * Marks a particular Task in the list of Tasks, as done.
     *
     * @param taskNumber The number to indicate which Task is to be marked as done.
     * @return The String response of the chatbot.                  
     * @throws DukeException When the task number given is not valid or
     * when there is an error in writing to the file.
     */
    public String markTask(int taskNumber) throws DukeException {
        return this.markUnmark(taskNumber, true);
    }

    /**
     * Marks a particular Task in the list of Tasks, as undone.
     *
     * @param taskNumber The number to indicate which Task is to be marked as undone.
     * @return The String response of the chatbot.                  
     * @throws DukeException When the task number given is not valid or
     * when there is an error in writing to the file.
     */
    public String unmarkTask(int taskNumber) throws DukeException {
        return this.markUnmark(taskNumber, false);
    }

    /**
     * Adds a given Task to the list of Tasks.
     *
     * @param task The Task to be added to the list of Tasks.
     * @param taskType The type of the given task.
     * @return The String response of the chatbot.
     * @throws DukeException When there is an error in writing to the file.
     */
    public String addTask(Task task, String taskType) throws DukeException {
        assert task != null;
        assert taskType != null;

        this.tasks.add(task);
        this.taskStorage.writeTasksToFile(this.tasks);

        StringBuilder response = new StringBuilder();
        response.append("I have added the ")
                .append(taskType)
                .append(" to the list :)\n")
                .append(task)
                .append("\n");

        return response.toString();

    }

    /**
     * Deletes a Task from the list of Tasks.
     *
     * @param taskNumber The number to indicate which Task is to be deleted.
     * @return The String response of the chatbot.
     * @throws DukeException When the task number given is not valid or
     * when there is an error in writing to the file.
     */
    public String deleteTask(int taskNumber) throws DukeException {
        boolean isValidTaskNumber = (taskNumber > 0
                && taskNumber <= this.tasks.size());

        if (isValidTaskNumber) {
            Task removedTask = this.tasks.remove(taskNumber - 1);
            this.taskStorage.writeTasksToFile(this.tasks);

            StringBuilder response = new StringBuilder();
            response.append("I have removed Task ")
                    .append(taskNumber)
                    .append(" from the list.\n")
                    .append(removedTask)
                    .append("\nYou now have ")
                    .append(this.tasks.size())
                    .append(" task(s) in the list.\n");

            return response.toString();

        } else {
            throw new DukeInvalidArgumentException("Sorry... That is an invalid task number :/");
        }

    }

    /**
     * Returns all the Tasks in the list of Tasks, that have the given keyword.
     *
     * @param keyword The keyword to search for, in the list of Tasks.
     * @return The String response of the chatbot.
     */
    public String findKeywordInTasks(String keyword) {
        assert keyword != null;

        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        ArrayList<Integer> requiredTaskNumbers = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            String taskDescription = task.getDescription().toLowerCase();

            int findIndex = taskDescription.indexOf(keyword.toLowerCase());

            if (findIndex != -1) {
                tasksWithKeyword.add(task);
                requiredTaskNumbers.add(i + 1);
            }

        }

        if (tasksWithKeyword.size() == 0) {
            return "I could not find any tasks with the keyword '"
                    + keyword + "' :/\n";

        } else {
            StringBuilder response = new StringBuilder();
            response.append("Listing all tasks with the keyword '")
                    .append(keyword)
                    .append("'...\n");
            for (int i = 0; i < tasksWithKeyword.size(); i++) {
                response.append(requiredTaskNumbers.get(i))
                        .append(") ")
                        .append(tasksWithKeyword.get(i))
                        .append("\n");
            }

            return response.toString();

        }


    }

    public String setTaskPriority(int taskNumber, Priority priority) throws DukeException {
        boolean isValidTaskNumber = (taskNumber > 0
                && taskNumber <= this.tasks.size());

        if (isValidTaskNumber) {
            StringBuilder response = new StringBuilder();
            this.tasks.get(taskNumber - 1).setPriority(priority);
            response.append("I have changed the priority of Task ")
                    .append(taskNumber)
                    .append(" to ")
                    .append(priority)
                    .append(".\n")
                    .append(this.tasks.get(taskNumber - 1))
                    .append("\n");


            this.taskStorage.writeTasksToFile(this.tasks);
            return response.toString();

        } else {
            throw new DukeInvalidArgumentException("Sorry... That is an invalid task number :/");
        }
    }

}
