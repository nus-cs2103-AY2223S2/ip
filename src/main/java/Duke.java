import java.util.ArrayList;
import java.util.List;


public class Duke {
    private boolean isRunning;
    private final TaskList tasks;
    private final UiHandler ui;
    
    Duke() {
        this.isRunning = true;
        this.tasks = new TaskList();
        this.ui = new UiHandler();
    }
    
    public static void main(String[] args) {
        Duke d = new Duke();
        d.ui.showStartingDialogue();
        
        while (d.isRunning) {
            d.isRunning = d.ui.run(d.tasks);
        }
    }
}
