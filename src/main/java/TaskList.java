import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList() {
        taskList = new ArrayList<>();
    }

    String parseCommand(String command) throws DukeException {
        String[] commandArr = command.split(" ");
        String taskType = commandArr[0];
        String description; Task task;
        switch (taskType) {
            case "todo":
                if (commandArr.length == 1) {
                    throw new EmptyTaskDescriptionException();
                }
                description = command.substring(5);
                task = new ToDo(description);
                break;
            case "deadline":
                int doneByIndex = command.indexOf("/by");
                description = command.substring(9, doneByIndex - 1);
                String doneBy = command.substring(doneByIndex + 4);
                task = new Deadline(description, doneBy);
                break;
            case "event":
                int startIndex = command.indexOf("/from"), endIndex = command.indexOf("/to");
                description = command.substring(6, startIndex - 1);
                String start = command.substring(startIndex + 6, endIndex - 1), end = command.substring(endIndex + 4);
                task = new Event(description, start, end);
                break;
            default:
                //add exception for invalid task type
                throw new InvalidCommandException();
        }
        addTask(task);
        return addTaskText(task);
    }

    void addTask(Task task) {
        taskList.add(task);
    }

    String deleteTask(int taskNum) {
        Task task = taskList.remove(taskNum - 1);
        return String.format("Noted, I've removed this task:\n   %s\nYou now have %d tasks in the list\n",
                task.toString(), getNumOfTasks());
    }

    String markTask(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.completeTask();
        return String.format("Nice! I've marked this task as done:\n   %s\n", task);
    }

    String unmarkTask(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.undoTask();
        return String.format("OK, I've unmarked this task as not done yet:\n   %s\n", task);
    }

    String addTaskText(Task task) {
        return String.format("Got it. I've added this task:\n   %s\nNow you have %s task%s in the list.\n",
                task.toString(), getNumOfTasks(), getNumOfTasks() == 1 ? "" : "s");
    }

    int getNumOfTasks() {
        return taskList.size();
    }
    String getListOfTasks() {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(String.format("%s\n", taskList.get(i)));
        }
        return listString.toString();
    }
    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "There are no tasks in your list";
        }
        StringBuilder printedList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            printedList.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }
        return printedList.toString();
    }

}
