import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> array;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> array) {
        this.array = array;
    }

}
