import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TaskMap<T> {
    private final Map<Integer, T> taskMap = new HashMap<>();

    public void addTask(T task) {
        taskMap.put(taskMap.size() + 1, task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + taskMap.size() + " tasks in the list.");
    }

    public Optional<T> getTask(Integer key) {
        if (key > taskMap.size() || key <= 0) {
            System.out.println("This task don't exists! Please select one from the list.");
            return Optional.empty();
        }
        return Optional.of(taskMap.get(key));
    }

    public void deleteTask(Integer key) {
        if (taskMap.containsKey(key)) {
            T task = taskMap.remove(key);
            System.out.println("Noted. I've removed the task:");
            System.out.println("\t" + task);
            System.out.println("Now you have " + taskMap.size() + " tasks in the list.");
        } else {
            System.out.println("This task don't exists! Please select one from the list.");
        }
    }

    @Override
    public String toString() {
        if (taskMap.size() > 0) {
            StringBuffer sb = new StringBuffer();
            taskMap.forEach((k, v) -> sb.append(k).append(". ").append(v).append("\n"));
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } else {
            return "There are no outstanding tasks!";
        }
    }
}
