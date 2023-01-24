public class Parser {
    public static boolean handleGeneralCommand(String command, TaskList tasks) throws DukeInputError {
        if (command.startsWith("list")) {
            tasks.listTasks();
        } else if ((command.startsWith("mark")) || (command.startsWith("unmark")) ||
                command.startsWith("delete")) {
            tasks.manageTask(command);
        } else if (command.equals("bye")) {
            System.out.println("    Bye. Hope to see you again soon!");
            return false;
        } else if (command.startsWith("event")){
            Event.createEvent(command, tasks);
        } else if (command.startsWith("deadline")) {
            DeadlineTask.createDeadlineTask(command, tasks);
        } else if (command.startsWith("todo")) {
            ToDo.createToDo(command, tasks);
        }
        else {
            throw new DukeInputError("invalid");
        }
        return true;
    }
}
