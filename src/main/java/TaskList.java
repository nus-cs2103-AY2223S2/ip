import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskList {
    private final List<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public void addTask(String task) {
        this.lst.add(new Task(task));
    }

    public void markTask(int number) {
        int index = number - 1;
        this.lst.set(index, this.lst.get(index).markDone());
    }

    public void unmarkTask(int number) {
        int index = number - 1;
        this.lst.set(index, this.lst.get(index).markNotDone());
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
