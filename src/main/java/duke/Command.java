package duke;

import java.util.ArrayList;

public class Command {
    private String input;
    private TaskList list;
    private Ui ui;

    public Command(String input, TaskList list, Ui ui) {
        this.input = input;
        this.ui = ui;
        this.list = list;
    }

    public void listCommand(TaskList list) {
    this.ui.showTaskList(list);
    }

    public void markCommand() {
        String str = input.replace("mark ", "");
        int index = Integer.parseInt(str);
        index--;
        Task task = list.get(index);
        task.markAsDone();
        ui.showMarked(task);
    }

    public void unmarkCommand() {
        String str = input.replace("unmark ", "");
        int index = Integer.parseInt(str);
        index--;
        Task task = list.get(index);
        task.unmark();
        ui.showUnmarked(task);
    }

    public void toDoCommand() throws EmptyDescriptionException {
        String str = input.replace("todo", "");
        ToDo toDo = new ToDo(str);
        list.add(toDo);
        ui.showAdd(toDo);
        ui.showTaskSize(list.size());
    }

    public void deadlineCommand() throws EmptyDescriptionException {
        String str = input.replace("deadline", "");
        String description = str.split("/by")[0];
        String dateAndTime = str.split("/by ")[1];
        Deadline deadline = new Deadline(description, dateAndTime);
        list.add(deadline);
        ui.showAdd(deadline);
        ui.showTaskSize(list.size());
    }

    public void eventCommand() throws EmptyDescriptionException {
        String str = input.replace("event", "");
        String description = str.split("/from")[0];
        String temp = str.split("/from")[1];
        String from = temp.split("/to")[0];
        String to = temp.split("/to")[1];
        Event event = new Event(description, from, to);
        list.add(event);
        ui.showAdd(event);
        ui.showTaskSize(list.size());
    }

    public void deleteCommand() {
        String str = input.replace("delete", "");
        int index = Integer.parseInt(str);
        index--;
        Task removedTask = list.get(index);
        list.remove(index);
        ui.showDelete(removedTask);
    }

    public void findCommand() {
        String keyword = input.split(" ", 2)[1];
        ArrayList<Task> matchingTaskList = new ArrayList<>();
        for (Task task : list) {
            if (task.toString().contains(keyword)) {
                matchingTaskList.add(task);
            }
        }
        TaskList matches = new TaskList(matchingTaskList);
        System.out.println("Here are the matching tasks in your list:\n" + matches);
    }
}
