package duke;

import java.util.ArrayList;

public class Command {
    private final String INPUT;
    private final TaskList LIST;
    private final Ui UI;

    public Command(String input, TaskList list, Ui ui) {
        this.INPUT = input;
        this.UI = ui;
        this.LIST = list;
    }

    public String listCommand(TaskList list) {
    return this.UI.showTaskList(list);
    }

    public String markCommand() {
        String str = INPUT.replace("mark ", "");
        int index = Integer.parseInt(str);
        index--;
        Task task = LIST.get(index);
        task.markAsDone();
        return UI.showMarked(task);
    }

    public String unmarkCommand() {
        String str = INPUT.replace("unmark ", "");
        int index = Integer.parseInt(str);
        index--;
        Task task = LIST.get(index);
        task.unmark();
        return UI.showUnmarked(task);
    }

    public String toDoCommand() throws EmptyDescriptionException {
        String str = INPUT.replace("todo", "");
        ToDo toDo = new ToDo(str);
        LIST.add(toDo);
        return UI.showAdd(toDo) + UI.showTaskSize(LIST.size());
    }

    public String deadlineCommand() throws EmptyDescriptionException {
        String str = INPUT.replace("deadline", "");
        String description = str.split("/by")[0];
        String dateAndTime = str.split("/by ")[1];
        Deadline deadline = new Deadline(description, dateAndTime);
        LIST.add(deadline);
        return UI.showAdd(deadline) + UI.showTaskSize(LIST.size());
    }

    public String eventCommand() throws EmptyDescriptionException {
        String str = INPUT.replace("event", "");
        String description = str.split("/from")[0];
        String temp = str.split("/from")[1];
        String from = temp.split("/to")[0];
        String to = temp.split("/to")[1];
        Event event = new Event(description, from, to);
        LIST.add(event);
        return UI.showAdd(event) + UI.showTaskSize(LIST.size());
    }

    public String deleteCommand() {
        String str = INPUT.replace("delete", "");
        int index = Integer.parseInt(str);
        index--;
        Task removedTask = LIST.get(index);
        LIST.remove(index);
        return UI.showDelete(removedTask);
    }

    public String findCommand() {
        String keyword = INPUT.split(" ", 2)[1];
        ArrayList<Task> matchingTaskList = new ArrayList<>();
        for (Task task : LIST) {
            if (task.toString().contains(keyword)) {
                matchingTaskList.add(task);
            }
        }
        TaskList matches = new TaskList(matchingTaskList);
        String result = "Here are the matching tasks in your list:\n" + matches;
        return result;
    }

    public String byeCommand() {
        return UI.showBye();
    }
}
