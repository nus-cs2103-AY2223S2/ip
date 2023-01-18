import Task.Deadline;
import Task.Todo;
import Task.Event;
public class Request {

    final private String request;
    final private TaskList tasks;
    private String response;

    /**
     * Constructor for Request class
     * @param request request by the user
     * @param tasks task array to store the tasks added by the user
     */
    public Request(String request, TaskList tasks) {
        this.request = request;
        this.tasks = tasks;
        processRequest();
    }

    /**
     * Function to process the user's request to get the response
     */
    public void processRequest() {
        String[] req = this.request.split(" ");
        String command = req[0];
        switch (command) {
            case "list":
                response = tasks.getTaskList();
                break;
            case "mark":
                Integer idx = Integer.parseInt(req[1]) - 1;
                this.tasks.getTask(idx).markComplete();
                response = "Nice! I have marked this task as done \n" + this.tasks.getTask(idx);
                break;
            case "unmark":
                idx = Integer.parseInt(req[1]) - 1;
                this.tasks.getTask(idx).unmarkComplete();
                response = "Aww! One more task on the list \n" + this.tasks.getTask(idx);
                break;
            case "todo":
                String task = request.split("todo ")[1];
                Todo newTodo = tasks.addTodo(task);
                response = "Great! I've added this task for you \n" + newTodo +
                            "\nYou have " + tasks.numOfTask() + " tasks in the list";
                break;
            case "deadline":
                req = request.split("deadline ")[1].split(" /by ");
                task = req[0];
                String deadline = req[1];
                Deadline newDeadline = tasks.addDeadline(task, deadline);
                response = "Great! I've added this task for you \n" + newDeadline +
                            "\nYou have " + tasks.numOfTask() + " tasks in the list";
                break;
            case "event":
                req = request.split("event ")[1].split(" /from ");
                task = req[0];
                String duration[] = req[1].split(" /to ");
                String from = duration[0];
                String to = duration[1];
                Event newEvent = tasks.addEvent(task, from, to);
                response = "Great! I've added this task for you \n" + newEvent +
                        "\nYou have " + tasks.numOfTask() + " tasks in the list";
                break;
            default:
                response = "unknown command: " + request;
        }
    }

    @Override
    public String toString() {
        return response;
    }
}
