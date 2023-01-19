import java.util.ArrayList;

public class toDoList {

    ArrayList<String> task;

    toDoList(){
        task =  new ArrayList<String>();
    }

    public void printList() {
        for (int i = 0; i < task.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + task.get(i));
        }
    }

    public void addItem(String x) {
        task.add(x);
        System.out.println("added: " + x);
    }
}
