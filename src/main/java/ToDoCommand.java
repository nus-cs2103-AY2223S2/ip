public class ToDoCommand extends Command {

    public ToDoCommand(String[] command) {
        super(command);
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (command.length == 1) {
                throw new DukeException(null, null);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < command.length; i++) {
                sb.append(command[i]);
                if (i + 1 != command.length) {
                    sb.append(" ");
                }
            }
            tasks.add(new Todo(sb.toString()));
            ui.addMsg(tasks);
            storage.write(tasks);
        } catch (DukeException e) {
            ui.todoError();
        }
    }
}