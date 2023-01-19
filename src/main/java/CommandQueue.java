import java.util.ArrayList;

public class CommandQueue {
    private ArrayList<Commands> queue = new ArrayList<>(); // Create an ArrayList object
    public void add(Commands command){
        queue.add(command);
    }
    public void executeQueue(){
        for (Commands command : queue) {
            command.execute();
        }
        queue.clear();
    }
}
