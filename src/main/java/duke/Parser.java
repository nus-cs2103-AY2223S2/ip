package duke;
import java.util.ArrayList;
public class Parser {
    private static final Ui ui = new Ui();
    public static void parse(String cmd, TaskList list) throws DukeException {
        if (cmd.equals("list")) {
            ui.showTaskList(list);
        } else if (cmd.startsWith("mark")) {
            String str = cmd.replace("mark ", "");
            int index = Integer.parseInt(str);
            index--;
            Task task = list.get(index);
            task.markAsDone();
            ui.showMarked(task);
        } else if (cmd.startsWith("unmark")) {
            String str = cmd.replace("unmark ", "");
            int index = Integer.parseInt(str);
            index--;
            Task task = list.get(index);
            ui.showUnmarked(task);
        } else if (cmd.startsWith("todo")) {
            String str = cmd.replace("todo", "");
            ToDo toDo = new ToDo(str);
            list.add(toDo);
            ui.showAdd(toDo);
            ui.showTaskSize(list.size());
        } else if (cmd.startsWith("deadline")) {
            String str = cmd.replace("deadline", "");
            String description = str.split("/by")[0];
            String dateAndTime = str.split("/by ")[1];
            Deadline deadline = new Deadline(description, dateAndTime);
            list.add(deadline);
            ui.showAdd(deadline);
            ui.showTaskSize(list.size());
        } else if (cmd.startsWith("event")) {
            String str = cmd.replace("event", "");
            String description = str.split("/from")[0];
            String temp = str.split("/from")[1];
            String from = temp.split("/to")[0];
            String to = temp.split("/to")[1];
            Event event = new Event(description, from, to);
            list.add(event);
            ui.showAdd(event);
            ui.showTaskSize(list.size());
        } else if (cmd.startsWith("delete")) {
            String str = cmd.replace("delete", "");
            int index = Integer.parseInt(str);
            index--;
            Task removedTask = list.get(index);
            list.remove(index);
            ui.showDelete(removedTask);
        } else if (cmd.startsWith("find")) {
            String keyword = cmd.split(" ", 2)[1];
            ArrayList<Task> matchingTaskList = new ArrayList<>();
            for (Task task : list) {
                if (task.toString().contains(keyword)) {
                    matchingTaskList.add(task);
                }
            }
            TaskList matches = new TaskList(matchingTaskList);
            System.out.println("Here are the matching tasks in your list:\n" + matches);
        } else {
            throw new UnknownCommandException();
        }
    }
}
