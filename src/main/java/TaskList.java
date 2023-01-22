import java.util.ArrayList;

public class TaskList {
    protected int init;
    ArrayList<Task> list = new ArrayList<>(init);

    public TaskList(int init) {
        this.init = init;
    }

    public void addTask(String desc) {
        this.list.add(desc);
    }

    public void processList() {
        for (int i = 0; i < list.size(); i++) {
            Task element = list.get(i);
            System.out.println(String.format("%d.%s", i + 1, element.toString()));
        }
        System.out.println("---------------------------------------");
    }

    public void processEvent() {

    }

    public void processDeadline() {

    }

    public void processTodo() {

    }

    public void processMark(int index) {
        Task task = list.get(index);
        task.setDone();
        System.out.println(String.format("Nice, this task has been marked as done:\n %s", task.toString()));
        System.out.println("---------------------------------------");
    }

    public void processUnmark() {

    }

    public void processDelete(int item) {
        this.list.remove(item);
    }

}
