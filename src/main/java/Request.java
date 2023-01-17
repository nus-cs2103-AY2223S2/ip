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
        Integer idx = req.length > 1 ? Integer.parseInt(req[1]) - 1 : null;

        switch (command) {
            case "list":
                response = tasks.getTaskList();
                break;
            case "mark":
                this.tasks.getTask(idx).markComplete();
                response = "Nice! I have marked this task as done \n" + this.tasks.getTask(idx);
                break;
            case "unmark":
                this.tasks.getTask(idx).unmarkComplete();
                response = "Aww! One more task on the list \n" + this.tasks.getTask(idx);
                break;
            default:
                response = "added: " + request;
                tasks.addTask(request);
        }
    }

    @Override
    public String toString() {
        return response;
    }
}
