import java.util.Scanner;

public class MarkCommand extends Command {
    
    public MarkCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.command.length == 1) {
                throw new DukeException(null, null);
            }
            Scanner sc = new Scanner(command[1]);
            if (!sc.hasNextInt()) {
                sc.close();
                throw new DukeException(null, null);
            }
            int index = sc.nextInt() - 1;
            if (tasks.get(index) == null) {
                sc.close();
                throw new DukeException(null, null);
            }
            tasks.get(index).markAsDone();
            ui.markMsg(tasks, index);
            storage.write(tasks);
            sc.close();
        } catch (DukeException e) {
            ui.markError();
        }
    }
}