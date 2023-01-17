public class Request {

    final private String request;
    final private TaskList tasks;
    private String response;


    public Request(String request, TaskList tasks) {
        this.request = request;
        this.tasks = tasks;
        processRequest();
    }

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
