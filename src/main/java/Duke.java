import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static boolean isRunning = true;
    private static List<Task> tasks = new ArrayList<Task>();

    
    public static void main(String[] args) {
        UiHandler.start();
        
        while (isRunning) {
            isRunning = UiHandler.run(tasks);

        }
    }
}
