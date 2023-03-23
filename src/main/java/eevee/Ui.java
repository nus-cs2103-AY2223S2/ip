package eevee;

import eevee.task.Task;

public class Ui {

    public Ui() {}

    public String sayHello() {
        return "EEVEE! I'm EeveeBot! How can I help you today?\n\n" +
                "Type 'help' to see the list of commands you can use!";
    }

    public String sayBye() {
        return "EEVEE!";
    }

    public String getHelp() {
        return "Here are the commands that you can use in EeveeBot!\n\n" +
                "Adding todos      :todo DESCRIPTION\n" +
                "Adding deadlines  :deadline DESCRIPTION /by DEADLINE\n" +
                "Adding events     :event DESCRIPTION /from START /to END\n" +
                "Show task list    :list\n" +
                "Delete task       :delete INDEX\n" +
                "Mark task         :mark INDEX\n" +
                "Unmark task       :unmark INDEX\n" +
                "Find task         :find KEYWORD\n" +
                "Exit programme    :bye\n\n" +
                "Make sure the format of DEADLINE, START and END is:\n" +
                "yyyy-MM-dd HH:mm :D\n\n" +
                "For a more detailed guide, visit https://toh-xinyi.github.io/ip/";
    }

    public String showLoadingError() {
        return "Eevee... Currently no task in task list. Creating new task list..." + askForNextCommand();
    }

    public String showDeletingTask(Task task, TaskList taskList) {
        return String.format("EEVEE! I have removed this task:\n%s\n"
                        + "Now you have %d tasks in the list.%n", task.getDescription(),
                taskList.getTaskListSize()) + askForNextCommand();
    }

    public String showAddingNewTask(Task task, TaskList taskList) {
        return String.format("EEVEE! I have added a new task:\n%s\n" +
                        "Now you have %d tasks in the list%n", task.getDescription(),
                taskList.getTaskListSize()) + askForNextCommand();
    }

    public String showFailAddingNewTask(TaskList tasks) {
        return String.format("Eevee... This task cannot be added as it clashes with another task in the task list:" +
                        "\n%s", showList(tasks)) + askForNextCommand();
    }

    public String showMarkingTaskDone(Task task) {
        return String.format("EEVEE! EEVEE! I've marked this task as done:\n %s%n",
                task.getDescription()) + askForNextCommand();
    }

    public String showMarkingTaskUndone(Task task) {
        return String.format("EEVEE. I've marked this task as not done yet:\n %s%n",
                task.getDescription()) + askForNextCommand();
    }

    public String showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Eevee? No tasks in task list." + askForNextCommand();
        }
        return String.format("EEVEE! Here are the tasks in your list:\n%s", showList(tasks) + askForNextCommand());
    }

    public String showFindingTask(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Eevee? No matching tasks found." + askForNextCommand();
        }
        return String.format("EEVEE! Here are the matching tasks in your list:\n%s", showList(tasks))
                + askForNextCommand();
    }

    public static String showList(TaskList tasks) {
        String taskList = "";
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            Task currTask = tasks.getTaskByIndex(i);
            taskList = String.format("%s%d. %s\n", taskList, i + 1, currTask.getDescription());
        }
        return taskList;
    }

    public static String askForNextCommand() {
        return "\nWhat would you like me to do next?";
    }
}
