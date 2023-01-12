public class RequestHandler {
    private enum RequestType {
        INDEX, MARK, UNMARK, TODO, DEADLINE, EVENT, UNKNOWN
    }
    private String request;
    private TaskList taskList;
    private RequestType requestType;
    /**
     * Constructor for the request handler.
     * @param request   the request message
     * @param taskList  the todo database
     */
    public RequestHandler(String request, TaskList taskList) {
        this.request = request;
        this.taskList = taskList;
        parseRequest();
    }

    /**
     * Provides a sanitised reply
     * @return  A sanitised response string.
     */
    public String getReply() {
        switch(this.requestType) {
            case INDEX:
                String res = "";
                for (int i = 0; i < this.taskList.indexTask().size(); i++) {
                    res += (i+1) + ". " + this.taskList.indexTask().get(i) + "\n";
                }
                return res.trim();
            case TODO:
                String task = this.request.split(" ", 2)[1];

                this.taskList.createTodo(task);
                return "added: " + task;
            case DEADLINE:
                return "deadline";
            case EVENT:
                return "event";
            case MARK:
                int index = Integer.parseInt(this.request.split(" ", 2)[1]) - 1;

                this.taskList.markTask(index);
                return "Nice! I've marked this task as done:\n" + this.taskList.showTask(index);
            case UNMARK:
                index = Integer.parseInt(this.request.split(" ", 2)[1]) - 1;

                this.taskList.unmarkTask(index);
                return "OK, I've marked this task as not done yet:\n" + this.taskList.showTask(index);
            default:
                return "Command not recognised.";
        }
    }

    private void parseRequest() {
        String firstWord = this.request.split(" ", 2)[0];
        if (firstWord.equals("list")) {
            this.requestType = RequestType.INDEX;
        } else if (firstWord.equals("mark")) {
            this.requestType = RequestType.MARK;
        } else if (firstWord.equals("unmark")) {
            this.requestType = RequestType.UNMARK;
        } else if (firstWord.equals("todo")) {
            this.requestType = RequestType.TODO;
        } else if (firstWord.equals("deadline")) {
            this.requestType = RequestType.DEADLINE;
        } else if (firstWord.equals("event")) {
            this.requestType = RequestType.EVENT;
        } else {
            this.requestType = RequestType.UNKNOWN;
        }
    }
}
