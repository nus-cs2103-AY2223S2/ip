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

    public void listCommand(TaskList list) {
    this.UI.showTaskList(list);
    }

    public void markCommand() {
        String str = INPUT.replace("mark ", "");
        int index = Integer.parseInt(str);
        index--;
        Task task = LIST.get(index);
        task.markAsDone();
        UI.showMarked(task);
    }

    public void unmarkCommand() {
        String str = INPUT.replace("unmark ", "");
        int index = Integer.parseInt(str);
        index--;
        Task task = LIST.get(index);
        task.unmark();
        UI.showUnmarked(task);
    }

    public void toDoCommand() throws EmptyDescriptionException {
        String str = INPUT.replace("todo", "");
        ToDo toDo = new ToDo(str);
        LIST.add(toDo);
        UI.showAdd(toDo);
        UI.showTaskSize(LIST.size());
    }

    public void deadlineCommand() throws EmptyDescriptionException {
        String str = INPUT.replace("deadline", "");
        String description = str.split("/by")[0];
        String dateAndTime = str.split("/by ")[1];
        Deadline deadline = new Deadline(description, dateAndTime);
        LIST.add(deadline);
        UI.showAdd(deadline);
        UI.showTaskSize(LIST.size());
    }

    public void eventCommand() throws EmptyDescriptionException {
        String str = INPUT.replace("event", "");
        String description = str.split("/from")[0];
        String temp = str.split("/from")[1];
        String from = temp.split("/to")[0];
        String to = temp.split("/to")[1];
        Event event = new Event(description, from, to);
        LIST.add(event);
        UI.showAdd(event);
        UI.showTaskSize(LIST.size());
    }

    public void deleteCommand() {
        String str = INPUT.replace("delete", "");
        int index = Integer.parseInt(str);
        index--;
        Task removedTask = LIST.get(index);
        LIST.remove(index);
        UI.showDelete(removedTask);
    }

    public void findCommand() {
        String keyword = INPUT.split(" ", 2)[1];
        ArrayList<Task> matchingTaskList = new ArrayList<>();
        for (Task task : LIST) {
            if (task.toString().contains(keyword)) {
                matchingTaskList.add(task);
            }
        }
        TaskList matches = new TaskList(matchingTaskList);
        System.out.println("Here are the matching tasks in your list:\n" + matches);
    }
}
