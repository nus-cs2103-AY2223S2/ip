import Command.*;
import DukeException.*;
import Storage.TaskList;

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
     * Function to process the user's request and get the response
     */
    public void processRequest() {
        String[] req = this.request.split(" ");
        String command = req[0];

        try {
            switch (command) {
                case "list":
                    Command com = new ListCommand();
                    response = com.execute(this.tasks);
                    break;
                case "mark":
                    com = new MarkCommand(this.request);
                    response = com.execute(this.tasks);
                    break;
                case "unmark":
                    com = new UnmarkCommand(this.request);
                    response = com.execute(this.tasks);
                    break;
                case "todo":
                    com = new AddTodoCommand(this.request);
                    response = com.execute(this.tasks);
                    break;
                case "deadline":
                    com = new AddDeadlineCommand(this.request);
                    response = com.execute(this.tasks);
                    break;
                case "event":
                    com = new AddEventCommand(request);
                    response = com.execute(this.tasks);
                    break;
                case "delete":
                    com = new RemoveCommand(request);
                    response = com.execute(this.tasks);
                    break;
                default:
                    throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }
    }

    @Override
    public String toString() {
        return response;
    }
}
