import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList() {
        taskList = new ArrayList<Task>();
    }

    String addTask(String command) throws DukeException {
        String[] commandArr = command.split(" ");
        String taskType = commandArr[0];
        String description; Task task;
        if (taskType.equals("todo")) {
            if (commandArr.length == 1) {
                throw new EmptyTaskDescriptionException();
            }
            description = command.substring(5);
            task = new ToDo(description);
        } else if (taskType.equals("deadline")) {
            int doneByIndex = command.indexOf("/by");
            description = command.substring(9, doneByIndex - 1);
            String doneBy = command.substring(doneByIndex + 4);
            task = new Deadline(description, doneBy);
        } else if (taskType.equals("event")){
            int startIndex = command.indexOf("/from"), endIndex = command.indexOf("/to");
            description = command.substring(6, startIndex - 1);
            String start = command.substring(startIndex + 6, endIndex - 1), end = command.substring(endIndex + 4);
            task = new Event(description, start, end);
        } else {
            //add exception for invalid task type
            throw new InvalidCommandException();
        }
        taskList.add(task);
        return addTaskText(task);
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

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "There are no tasks in your list";
        }
        String printedList = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            printedList += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        return printedList;
    }

}
