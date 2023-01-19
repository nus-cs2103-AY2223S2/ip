import java.awt.*;
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
        } else {
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
        System.out.println(currTask);
    }

    private void unmark(String userIn){
        Task currTask = taskList.get(Integer.parseInt(userIn.split(" ")[1])-1);
        currTask.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask);
    }

    private void addTask(String userIn){
        String taskType = userIn.split(" ")[0];
        //System.out.println("tasktype: " + taskType);
        if (taskType.equals("todo") ) {
            //System.out.println("attempting to add todo");
            addToDo(userIn);
        } else if (taskType.equals("deadline")) {
            addDeadline(userIn);
        } else if (taskType.equals("event")) {
            addEvent(userIn);
        }
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void addEvent(String userIn) {
        String descAndTimes = userIn.replace("event", "");
        Event newEvent = new Event(
                descAndTimes.split("/")[0],
                descAndTimes.split("/")[1].replace("from", ""),
                descAndTimes.split("/")[2].replace("to", "")
        );
        taskList.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
    }

    private void addDeadline(String userIn) {
        String descAndBy = userIn.replace("Deadline", "");
        Deadline newDeadline = new Deadline(descAndBy.split("/")[0], descAndBy.split("/")[1].replace("by", ""));
        taskList.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
    }

    private void addToDo(String userIn){
        ToDo newToDo = new ToDo(userIn.replace("ToDo", ""));
        taskList.add(newToDo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newToDo);
    }



    private void displayList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i<taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println("End of task list.");
    }
}
