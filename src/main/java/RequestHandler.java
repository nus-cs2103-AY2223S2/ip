public class RequestHandler {
    private enum RequestType {
        INDEX, MARK, UNMARK, TODO, DEADLINE, EVENT, UNKNOWN
    }
    private final String request;
    private final TaskList taskList;
    private RequestType requestType;
    private String requestContent;
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
                String res = "Here are the tasks in your list:\n";
                for (int i = 0; i < this.taskList.indexTask().size(); i++) {
                    res += (i+1) + ". " + this.taskList.indexTask().get(i) + "\n";
                }
                return res.trim();
            case TODO:
                Todo newTodo = this.taskList.createTodo(this.requestContent);
                return "Got it. I've added this task:\n" + newTodo +
                        "\nNow you have " +this.taskList.countTask() + " tasks in the list.";

            case DEADLINE:
                String deadlineTask = this.requestContent.split(" /by ", 2)[0];
                String deadline = this.requestContent.split(" /by ", 2)[1];

                Deadline newDeadline = this.taskList.createDeadline(deadlineTask, deadline);
                return "Got it. I've added this task:\n" + newDeadline +
                        "\nNow you have " +this.taskList.countTask() + " tasks in the list.";
            case EVENT:
                String[] splitContent = this.requestContent.split(" /from ", 2);
                String eventTask = splitContent[0];
                String startTime = splitContent[1].split(" /to ", 2)[0];
                String endTime = splitContent[1].split(" /to ", 2)[1];

                Event newEvent = this.taskList.createEvent(eventTask, startTime, endTime);
                return "Got it. I've added this task:\n" + newEvent +
                        "\nNow you have " +this.taskList.countTask() + " tasks in the list.";

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

        this.requestContent = this.request.split(" ", 2).length == 2
            ? this.request.split(" ", 2)[1]
            : "";
    }
}
