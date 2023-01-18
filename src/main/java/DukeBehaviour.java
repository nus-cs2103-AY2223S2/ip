import java.util.ArrayList;

public class DukeBehaviour {
    Boolean isActive = true;
    ArrayList<Task> taskList = new ArrayList<>();


    public DukeBehaviour() {
        System.out.println("DukeBehaviour constructor called...");
    }

    public void receiveInput(String userIn){
        if (userIn.equals("bye")){
            System.out.println("exit command received, exiting...");
            isActive = false;
        } else if (userIn.equals("list")){
            displayList();
        } else if (userIn.split(" ")[0].equals("mark")) {
            mark(userIn);
        } else if (userIn.split(" ")[0].equals("unmark")){
            unmark(userIn);
        }
        else {
            addTask(userIn);
        }
    }

    private void echo(String userIn){
        System.out.println(userIn);
    }

    private void mark(String userIn) {
        Task currTask = taskList.get(Integer.parseInt(userIn.split(" ")[1])-1);
        currTask.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask.getTaskView());
    }

    private void unmark(String userIn){
        Task currTask = taskList.get(Integer.parseInt(userIn.split(" ")[1])-1);
        currTask.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask.getTaskView());
    }


    private void addTask(String userIn){
        taskList.add(new Task(userIn));
        System.out.println("added: " + userIn);
    }

    private void displayList() {
        for (int i = 0; i<taskList.size(); i++){
            System.out.println((i+1) + ". " + taskList.get(i).getTaskView());
        }
    }
}
