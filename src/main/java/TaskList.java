import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> arr;

    public TaskList(){
        arr = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arr){
        this.arr = arr;
    }

    public static ArrayList<Task> getList() {
        return arr;
    }

    public static String markDone(int index) {
        arr.get(index).markDone();
        return arr.get(index).getTaskName();
    }

    public static String markUndone(int index) {
        arr.get(index).markUndone();
        return arr.get(index).getTaskName();
    }

    public static void addTask(Task task) {
        arr.add(task);
    }

    public static Task remove(int index) {
        return arr.remove(index);
    }

    public static void close() {
        arr = null;
    }

}
