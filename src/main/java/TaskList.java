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
        return IntStream.range(0, lst.size())
                .mapToObj(i -> String.format("\n%d) %s", i, this.lst.get(i)))
                .reduce("", (a, b) -> a + b);
    }
}
