import java.util.ArrayList;
public class DukeList {
    private ArrayList<Task> taskList;
    DukeList() {
        taskList = new ArrayList<>();
    }

    public void addTask(String input) throws DukeException {
        String taskName;
        if (input.startsWith("todo")){
            taskName = input.substring(5);
            if (taskName.isEmpty()) {
                throw new DukeException("Empty description of todo");
            }
            taskList.add(new ToDoTask(taskName));
        } else if (input.startsWith("deadline")) {
            int firstSlashIndex = input.indexOf("/");
            taskName = input.substring(9, firstSlashIndex - 1);
            String deadline =input.substring(firstSlashIndex);
            taskList.add(new DeadlineTask(taskName, deadline));
        } else if (input.startsWith("event")) {
            int firstSlashIndex = input.indexOf("/");
            taskName = input.substring(6, firstSlashIndex - 1);
            String period =input.substring(firstSlashIndex);
            taskList.add(new EventTask(taskName, period));
        } else {
            throw new DukeException("Invalid command");
        }
        int count = taskList.size() - 1;
        System.out.println(DukeIO.wrapContent("Task added:\n\t\t" + taskList.get(count) + "\n\tNow you have "
                + taskList.size() + " tasks in the list."));
    }

    public void markTask(int taskIndex) throws DukeException {
        if (taskIndex > taskList.size() || taskIndex < 0) {
            throw new DukeException("Invalid task index specified");
        }
        taskList.get(taskIndex - 1).markTask();
    }

    public void unmarkTask(int taskIndex) throws DukeException {
        if (taskIndex > taskList.size() || taskIndex < 0) {
            throw new DukeException("Invalid task index specified");
        }
        taskList.get(taskIndex - 1).unmarkTask();
    }

    public void deleteTask(int taskIndex) throws  DukeException {
        if (taskIndex > taskList.size() || taskIndex < 0) {
            throw new DukeException("Invalid task index specified");
        }
        Task toRemove = taskList.get(taskIndex - 1);
        taskList.remove(taskIndex - 1);
        System.out.println(DukeIO.wrapContent("Task removed:\n\t\t" + toRemove.toString() + "\n\t" + taskList.size() + " tasks remaining in list."));
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Current tasks in list:");
        for (int i=0; i < taskList.size(); i++) {
            result.append("\n\t").append(i + 1).append(". ").append(taskList.get(i));
        }
        return result.toString();
    }
}
