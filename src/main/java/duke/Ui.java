package duke;

public class Ui {

    public void sayHello() {
        System.out.println("Sup, I'm Dupe. How can I help you?");
    }

    public void sayBye() {
        System.out.println("Goodbye.");
    }

    public void showLoadingError() {
        System.out.println("Currently no task in task list. Creating new task list...");
    }

    public void showDeletingTask(Task task, TaskList taskList) {
        System.out.printf("Got it! I have removed this task: \n%s\n "
                        + "Now you have %d tasks in the list.%n", task.getDescription(),
                taskList.getTaskListSize());
    }

    public void showAddingNewTask(Task task, TaskList taskList) {
        System.out.printf("Got it! I have added a new task: \n%s\n" +
                        "Now you have %d tasks in the list%n", task.getDescription(),
                taskList.getTaskListSize());
    }

    public void showMarkingTaskDone(Task task) {
        System.out.printf("Nice! I've marked this task as done:\n %s%n",
                task.getDescription());
    }

    public void showMarkingTaskUndone(Task task) {
        System.out.printf("Alright! I've marked this task as not done yet:\n %s%n",
                task.getDescription());
    }

    public void showTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            Task currTask = tasks.getTaskByIndex(i);
            System.out.printf("%d. %s\n", i + 1, currTask.description());
        }
        if (tasks.isEmpty()) {
            System.out.println("No tasks in task list.");
        }
    }

    public void showFindingTask(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            this.showTaskList(tasks);
        }
    }

    public void askForNextCommand() {
        System.out.println("What would you like me to do next?");
    }
}
