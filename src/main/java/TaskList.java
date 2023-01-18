import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList() {
        taskList = new ArrayList<Task>();
    }

    String addTask(String command) {
        String taskType = command.split(" ")[0];
        String description; Task task;
        if (taskType.equals("todo")) {
            description = command.substring(5);
            task = new ToDo(description);
            taskList.add(task);
        } else if (taskType.equals("deadline")) {
            int doneByIndex = command.indexOf("/by");
            description = command.substring(9, doneByIndex - 1);
            String doneBy = command.substring(doneByIndex + 4);
            task = new Deadline(description, doneBy);
            taskList.add(task);
        } else {
            int startIndex = command.indexOf("/from"), endIndex = command.indexOf("/to");
            description = command.substring(6, startIndex - 1);
            String start = command.substring(startIndex + 6, endIndex - 1), end = command.substring(endIndex + 4);
            task = new Event(description, start, end);
            taskList.add(task);
        }
        return addTaskText(task);
    }

    String markTask(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.completeTask();
        return String.format("\tNice! I've marked this task as done:\n\t   %s\n", task);
    }

    String unmarkTask(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.undoTask();
        return String.format("\tOK, I've unmarked this task as not done yet:\n\t   %s\n", task);
    }

    String addTaskText(Task task) {
        return String.format("\tGot it. I've added this task:\n\t   %s\n\tNow you have %s task%s in the list.\n",
                task.toString(), getNumOfTasks(), getNumOfTasks() == 1 ? "" : "s");
    }
    int getNumOfTasks() {
        return taskList.size();
    }

    @Override
    public String toString() {
        String printedList = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            printedList += String.format("\t%d. %s\n", i + 1, taskList.get(i));
        }
        return printedList;
    }

}
