import java.util.ArrayList;

public class toDoList {

    ArrayList<Task> allTasks;

    toDoList(){
        allTasks =  new ArrayList<Task>();
    }

    public int numberOfTask() {
        return allTasks.size();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". "+ allTasks.get(i).toString());
        }
    }

    public void addItem(String type, String item) {
        Task newTask = new Task(item, type);
        allTasks.add(newTask);
        System.out.println(" " + newTask.toString());
    }

    public void addItemDeadline(String type, String item, String time) {

        Task newTask = new Deadline(item, type, time);
        allTasks.add(newTask);

        System.out.println(" " + newTask.toString());
    }

    public void addItemEvent(String type, String item, String time1, String time2) {
        Task newTask = new Event(item, type, time1, time2);

        //newTask.setEventTime(time1, time2);
        allTasks.add(newTask);
        System.out.println(" " + newTask.toString());
    }

    public void changingStatus(int x, int index) {
        if(x == 0) { //marked
            allTasks.get(index-1).mark();

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + allTasks.get(index-1).toString());

        } else { //unmarked
            allTasks.get(index-1).unmark();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + allTasks.get(index-1).toString());
        }
    }

}
