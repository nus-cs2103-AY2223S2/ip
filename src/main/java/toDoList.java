import java.util.ArrayList;

public class toDoList {

    ArrayList<Task> allTasks;

    toDoList(){
        allTasks =  new ArrayList<Task>();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". "+ allTasks.get(i).getItem());
        }
    }

    public void addItem(String x) {
        Task newTask = new Task(x);
        allTasks.add(newTask);
        System.out.println("added: " + x);
    }

    public void changingStatus(int x, int index) {
        if(x == 0) { //marked
            allTasks.get(index-1).mark();

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + allTasks.get(index-1).getItem());

        } else { //unmarked
            allTasks.get(index-1).unmark();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + allTasks.get(index-1).getItem());
        }
    }

}
