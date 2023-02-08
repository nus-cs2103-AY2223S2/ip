package duke;

import java.util.ArrayList;

/**
 * Class that represents a Command consisting of a type and data.
 * Type refers to todo, deadline or event.
 * Data refers to the dates associated with the Command.
 */
public class Command {

    private String type;
    private String[] data;
    private boolean isExit;

    public Command(String type, String[] data) {
        this.type = type;
        this.data = data;
    }

    /**
     * Executes the Command instance using a given task list and UI
     *
     * @param taskList taskList object to modify tasks in if command requires
     * @param response UI object to show command execution on
     */
    public void execute(TaskList taskList, Response response) {
        switch(type) {
            case "bye":
                isExit = true;
                response.showBye();
                break;
            case "list":
                response.showList(taskList);
                break;
            case "mark":
                int markIndex = Integer.parseInt(data[0]) - 1;
                taskList.getTask(markIndex).setDone(true);
                response.showMark(markIndex);
                taskList.saveList();
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(data[0]) - 1;
                taskList.getTask(unmarkIndex).setDone(false);
                response.showUnmark(unmarkIndex);
                taskList.saveList();
                break;
            case "addTodo":
                String todoDescrip = data[0];
                Task newTodo = taskList.addTodo(todoDescrip);
                response.showAddTask(newTodo);
                taskList.saveList();
                break;
            case "addDeadline":
                String deadlineDescrip = data[0];
                String by = data[1];
                Deadline newDeadline = taskList.addDeadline(deadlineDescrip, by);
                response.showAddTask(newDeadline);
                taskList.saveList();
                break;
            case "addEvent":
                String eventDescrip = data[0];
                String from = data[1];
                String to = data[2];
                Event newEvent = taskList.addEvent(eventDescrip, from, to);
                response.showAddTask(newEvent);
                taskList.saveList();
                break;
            case "deleteTask":
                int index = Integer.parseInt(data[0]) - 1;
                taskList.removeTask(index);
                response.showDeleteTask(index);
                taskList.saveList();
                break;
            case "find":
                String keyword = data[0];
                ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
                response.showFindList(matchingTasks);
                break;
        }
    }

    public String getType() {
        return type;
    }

    public String[] getData() {
        return data;
    }

    public boolean getExit() {
        return isExit;
    }

}
