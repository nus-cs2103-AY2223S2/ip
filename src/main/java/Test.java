import tasklist.TaskList;
import tasklist.task_types.ToDo;

public class Test {
    public static void main(String[] args) {
        TaskList test = new TaskList();
        ToDo td = new ToDo("asd");
        test.addTask(td);
        System.out.println(test);
    }
}
