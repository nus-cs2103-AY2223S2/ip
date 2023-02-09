package duke;

import duke.task.Task;

/**
 * Stores the list of Task objects.
 */
public class TaskList {
    public static final int MIN_INDEX = 0;
    public static final int MAX_INDEX = 99;
    private int index;
    private Task[] tasks;

    /**
     * The TaskList constructor used when there are past Task objects to be inputted.
     * @param tasks The array of past Task objects
     */
    public TaskList(Task[] tasks) {
        this.index = 0;
        this.tasks = tasks;
        for (int i = 0; i < TaskList.MAX_INDEX + 1; i++) {
            if (tasks[i] == null) {
                this.index = i;
                break;
            }
        }
    }

    /**
     * The TaskList constructor used to create an empty TaskList object.
     */
    public TaskList() {
        this.index = 0;
        this.tasks = new Task[TaskList.MAX_INDEX + 1];
    }

    /**
     * Add task to task list.
     * @param task The Task object to be added.
     * @return The Duke chatbot's reply after adding a task.
     */
    public String addTask(Task task) {
        this.tasks[this.index] = task;
        String str = "Got it! This task has been added:\n";
        str += this.printTask(index) + "\n";
        this.index++;
        String sp = this.index == 1 ? "task" : "tasks";
        str += "You now have " + this.index + " " + sp + " in the list.";
        return str;
    }

    /**
     * Delete task from task list.
     * @param index Index of task in task list to be deleted.
     * @return The Duke chatbot's reply after deleting a task.
     */
    public String deleteTask(int index) {
        if (this.tasks[index] == null) {
            throw new RuntimeException("Task does not exist!");
        }
        String str = "Got it! This task has been removed:\n";
        str += this.printTask(index) + "\n";
        this.index--;
        String sp = this.index == 1 ? "task" : "tasks";
        str += "You now have " + this.index + " " + sp + " in the list.";
        for (int i = index; i < TaskList.MAX_INDEX; i++) {
            this.tasks[i] = this.tasks[i + 1];
        }
        this.tasks[TaskList.MAX_INDEX] = null;
        return str;
    }

    /**
     * Mark a task in task list as done.
     * @param index Index of task to be marked as done.
     * @return The Duke chatbot's reply after marking a task as done.
     */
    public String markTask(int index) {
        if (this.tasks[index] == null) {
            throw new RuntimeException("Task does not exist!");
        }
        this.tasks[index].mark();
        String str = "Great job! This task has been marked as done:\n";
        str += this.printTask(index);
        return str;
    }

    /**
     * Marking a task as undone.
     * @param index Index of task to be marked as undone.
     * @return The Duke chatbot's reply after marking a task as undone.
     */
    public String unMarkTask(int index) {
        if (this.tasks[index] == null) {
            throw new RuntimeException("Task does not exist!");
        }
        this.tasks[index].unMark();
        String str = "Noted! This task has been marked as undone:\n";
        str += this.printTask(index);
        return str;
    }

    /**
     * Find tasks that have descriptions matching keyword
     * @param keyword Keyword that is being searched for.
     * @return String representation of the matching tasks.
     */
    public String find(String keyword) {
        String result = "";
        for (int i = 0; i < this.index; i++) {
            Task task = this.tasks[i];
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                if (result.equals("")) {
                    result += (i + 1) + ". " + task;
                } else {
                    result += "\n" + (i + 1) + ". " + task;
                }
            }
        }
        return result;
    }

    public String updateTask(int index, String body) {
        if (this.tasks[index] == null) {
            throw new RuntimeException("Task does not exist!");
        }
        Task task = this.tasks[index];
        final String byField = "/by";
        final String fromField = "/from";
        final String toField = "/to";
        final int bodyLength = body.length();
        int firstSpaceIndex = body.indexOf(" ");
        String firstWord;
        if (firstSpaceIndex == -1) {
            firstWord = body;
        } else {
            firstWord = body.substring(0, firstSpaceIndex);
        }
        switch (firstWord) {
        case (byField):
            final int bySpaceLength = byField.length() + 1;
            if (bodyLength <= bySpaceLength) {
                throw new RuntimeException("Please fill in the /by field!");
            }
            String newBy = body.substring(bySpaceLength);
            task.updateByField(newBy);
            break;
        case (fromField):
            final int fromSpaceLength = fromField.length() + 1;
            if (bodyLength <= fromSpaceLength) {
                throw new RuntimeException("Please fill in the /from field!");
            }
            String newFrom = body.substring(fromSpaceLength);
            task.updateFromField(newFrom);
            break;
        case (toField):
            final int toSpaceLength = toField.length() + 1;
            if (bodyLength <= toSpaceLength) {
                throw new RuntimeException("Please fill in the /to field!");
            }
            String newTo = body.substring(toSpaceLength);
            task.updateToField(newTo);
            break;
        default:
            task.changeDescription(body);
        }
        String reply = "Ok! This task has been updated:\n";
        reply += task;
        return reply;
    }

    public String printTask(int index) {
        return tasks[index].toString();
    }

    /**
     * Create a string to represent all the tasks in task list to be saved in file.
     * @return String representing all the tasks in task list.
     */
    public String taskListToSavedForm() {
        String str = "";
        for (int i = 0; i < this.index; i++) {
            str += tasks[i].taskToSavedForm() + "\n";
        }
        return str;
    }

    /**
     * Lists out all the tasks in task list.
     * @return List of all tasks in task list.
     */
    @Override
    public String toString() {
        if (this.index == 0) {
            return "There are currently no tasks! Yay!";
        }
        String str = "";
        for (int i = 0; i < this.index; i++) {
            if (i == this.index - 1) {
                str += (i + 1) + ". " + this.printTask(i);
            } else {
                str += (i + 1) + ". " + this.printTask(i) + "\n";
            }
        }
        return str;
    }
}
