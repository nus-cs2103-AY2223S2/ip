import java.util.ArrayList;

public class DukeBehaviour {
    Boolean isActive = true;
    ArrayList<String> taskList = new ArrayList<String>();


    public DukeBehaviour() {
        System.out.println("Duke constructor called...");
    }

    public void receiveInput(String userIn){
        if (userIn.equals("bye")){
            System.out.println("exit command received, exiting...");
            isActive = false;
        } else if (userIn.equals("list")){
            displayList();
        }
        else {
            addTask(userIn);
        }
    }

    private void echo(String userIn){
        System.out.println(userIn);
    }

    private void addTask(String userIn){
        taskList.add(userIn);
        System.out.println("added: " + userIn);
    }

    private void displayList() {
        for (int i = 0; i<taskList.size(); i++){
            System.out.println((i+1) + ". " + taskList.get(i));
        }
    }
}
