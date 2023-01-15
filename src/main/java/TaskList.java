import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskList {
    private final List<String> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public void addTask(String task) {
        this.lst.add(task);
    }
    
    @Override
    public String toString() {
        if (this.lst.isEmpty()) {
            return "You have no tasks dumbass\n";
        }

        return IntStream.range(0, lst.size())
                .mapToObj(i -> String.format("\n\t%d) %s", i + 1, this.lst.get(i)))
                .reduce("", (a, b) -> a + b) + '\n';
    }
}
