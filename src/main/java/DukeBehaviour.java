import java.util.ArrayList;

public class DukeBehaviour {
    Boolean isActive = true;
    ArrayList<Task> taskList = new ArrayList<>();


    public DukeBehaviour() {
        //System.out.println("DukeBehaviour constructor called...");
    }

    public void receiveInput(String userIn){
        try {
            if (userIn.equals("bye")) {
                //System.out.println("exit command received, exiting...");
                isActive = false;
            } else if (userIn.equals("list")) {
                displayList();
            } else if (userIn.split(" ")[0].equals("mark")) {
                mark(userIn);
            } else if (userIn.split(" ")[0].equals("unmark")) {
                unmark(userIn);
            } else if (userIn.split(" ")[0].equals("delete")) {
                delete(userIn);
            } else {
                addTask(userIn);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void delete(String userIn) throws DukeException{
        int index = Integer.parseInt(userIn.split(" ")[1]);
        if (index < 1 || index > taskList.size()){
            throw new DukeException("index " + index +" not in range!");
        }
        Task currTask = taskList.get(index-1);
        taskList.remove(index-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(currTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void echo(String userIn){
        System.out.println(userIn);
    }

    private void mark(String userIn) throws DukeException{
        int index = Integer.parseInt(userIn.split(" ")[1]);
        if (index < 1 || index > taskList.size()){
            throw new DukeException("index " + index +" not in range!");
        }
        Task currTask = taskList.get(index-1);
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
        try{
            switch (taskType) {
                case "todo":
                    addToDo(userIn);
                    break;
                case "deadline":
                    addDeadline(userIn);
                    break;
                case "event":
                    addEvent(userIn);
                    break;
                default:
                    throw new DukeException("I'm sorry, I could not understand that command.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addEvent(String userIn) {
        String descAndTimes = userIn.replace("event", "");
        Event newEvent = new Event(
                descAndTimes.split("/")[0].trim(),
                descAndTimes.split("/")[1].replace("from", "").trim(),
                descAndTimes.split("/")[2].replace("to", "").trim()
        );
        taskList.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void addDeadline(String userIn) {
        String descAndBy = userIn.replace("Deadline", "");
        Deadline newDeadline = new Deadline(descAndBy.split("/")[0].trim(), descAndBy.split("/")[1].replace("by", "").trim());
        taskList.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void addToDo(String userIn) throws DukeException{
        if (userIn.split(" ").length == 1) {
            throw new DukeException("todo cannot have no description!");
        }
        ToDo newToDo = new ToDo(userIn.replace("ToDo", ""));
        taskList.add(newToDo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newToDo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void displayList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i<taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println("End of task list.");
    }
}
