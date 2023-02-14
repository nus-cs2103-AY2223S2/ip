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
                doBye(response);
                break;
            case "list":
                doList(taskList, response);
                break;
            case "mark":
                doMark(taskList, response);
                break;
            case "unmark":
                doUnmark(taskList, response);
                break;
            case "addTodo":
                doTodo(taskList, response);
                break;
            case "addDeadline":
                doDeadline(taskList, response);
                break;
            case "addEvent":
                doEvent(taskList, response);
                break;
            case "addFixed":
                doFixed(taskList, response);
                break;
            case "deleteTask":
                doDelete(taskList, response);
                break;
            case "find":
                doFind(taskList, response);
                break;
        }
    }

    private void doBye(Response response) {
        isExit = true;
        response.showBye();
    }

    private void doList(TaskList taskList, Response response) {
        response.showList(taskList);
    }

    private void doMark(TaskList taskList, Response response) {
        assert data.length == 1;
        int markIndex = Integer.parseInt(data[0]) - 1;
        taskList.getTask(markIndex).setDone(true);
        response.showMark(markIndex);
        taskList.saveList();
    }

    private void doUnmark(TaskList taskList, Response response) {
        assert data.length == 1;
        int unmarkIndex = Integer.parseInt(data[0]) - 1;
        taskList.getTask(unmarkIndex).setDone(false);
        response.showUnmark(unmarkIndex);
        taskList.saveList();
    }

    private void doTodo(TaskList taskList, Response response) {
        assert data.length == 1;
        String todoDescrip = data[0];
        Task newTodo = taskList.addTodo(todoDescrip);
        response.showAddTask(newTodo);
        taskList.saveList();
    }

    private void doDeadline(TaskList taskList, Response response) {
        assert data.length == 2;
        String deadlineDescrip = data[0];
        String by = data[1];
        Deadline newDeadline = taskList.addDeadline(deadlineDescrip, by);
        response.showAddTask(newDeadline);
        taskList.saveList();
    }

    private void doEvent(TaskList taskList, Response response) {
        assert data.length == 3;
        String eventDescrip = data[0];
        String from = data[1];
        String to = data[2];
        Event newEvent = taskList.addEvent(eventDescrip, from, to);
        response.showAddTask(newEvent);
        taskList.saveList();
    }

    private void doFixed(TaskList taskList, Response response) {
        assert data.length == 2;
        String fixedDescrip = data[0];
        String hours = data[1];
        FixedTask newFixedTask = taskList.addFixed(fixedDescrip, hours);
        response.showAddTask(newFixedTask);
        taskList.saveList();
    }

    private void doDelete(TaskList taskList, Response response) {
        assert data.length == 1;
        int index = Integer.parseInt(data[0]) - 1;
        assert index <= taskList.getCount();
        response.showDeleteTask(index);
        taskList.removeTask(index);
        taskList.saveList();
    }

    private void doFind(TaskList taskList, Response response) {
        assert data.length == 1;
        String keyword = data[0];
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
        response.showFindList(matchingTasks);
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
