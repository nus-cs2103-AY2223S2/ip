package duke;
public class Parser {
    private static final Ui UI = new Ui();

    /**
     * parses the given command into the given TaskList.
     * @param cmd String representing input command.
     * @param list TaskList representing the list of tasks.
     * @throws DukeException
     */
    public static void parse(String cmd, TaskList list) throws DukeException {
        if (cmd.equals("list")) {
            UI.showTaskList(list);
        } else if (cmd.startsWith("mark")) {
            String str = cmd.replace("mark ", "");
            int index = Integer.parseInt(str);
            index--;
            Task task = list.get(index);
            task.markAsDone();
            UI.showMarked(task);
        } else if (cmd.startsWith("unmark")) {
            String str = cmd.replace("unmark ", "");
            int index = Integer.parseInt(str);
            index--;
            Task task = list.get(index);
            UI.showUnmarked(task);
        } else if (cmd.startsWith("todo")) {
            String str = cmd.replace("todo", "");
            ToDo toDo = new ToDo(str);
            list.add(toDo);
            UI.showAdd(toDo);
            UI.showTaskSize(list.size());
        } else if (cmd.startsWith("deadline")) {
            String str = cmd.replace("deadline", "");
            String description = str.split("/by")[0];
            String dateAndTime = str.split("/by ")[1];
            Deadline deadline = new Deadline(description, dateAndTime);
            list.add(deadline);
            UI.showAdd(deadline);
            UI.showTaskSize(list.size());
        } else if (cmd.startsWith("event")) {
            String str = cmd.replace("event", "");
            String description = str.split("/from")[0];
            String temp = str.split("/from")[1];
            String from = temp.split("/to")[0];
            String to = temp.split("/to")[1];
            Event event = new Event(description, from, to);
            list.add(event);
            UI.showAdd(event);
            UI.showTaskSize(list.size());
        } else if (cmd.startsWith("delete")) {
            String str = cmd.replace("delete", "");
            int index = Integer.parseInt(str);
            index--;
            Task removedTask = list.get(index);
            list.remove(index);
            UI.showDelete(removedTask);
        } else {
            throw new UnknownCommandException();
        }
    }
}
