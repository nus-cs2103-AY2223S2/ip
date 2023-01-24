import Tasks.Task;

import java.io.IOException;

public class ExitCommand extends Command{

    public void execute(TaskList l, Ui ui, Storage s) {
        ui.showExit();
        try {
            s.save(l);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
