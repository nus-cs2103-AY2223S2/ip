import java.util.ArrayList;
import java.util.List;


public class Duke {
    private boolean isRunning;
    private final List<Task> tasks;
    private final UiHandler ui;
    
    Duke() {
        this.isRunning = true;
        this.tasks = new ArrayList<>();
        this.ui = new UiHandler();
    }
    
    public static void main(String[] args) {
        Duke d = new Duke();
        d.ui.start();
        
        while (d.isRunning) {
            d.isRunning = d.ui.run(d.tasks);
        }
    }
}
