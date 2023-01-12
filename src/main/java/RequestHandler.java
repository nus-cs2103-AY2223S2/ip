public class RequestHandler {
    private enum RequestType {
        INDEX, CREATE, MARK, UNMARK
    }
    private String request;
    private TodoList todoList;
    private RequestType requestType;
    /**
     * Constructor for the request handler.
     * @param request   the request message
     * @param todoList  the todo database
     */
    public RequestHandler(String request, TodoList todoList) {
        this.request = request;
        this.todoList = todoList;
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
                for (int i = 0; i < this.todoList.indexTodo().size(); i++) {
                    res += (i+1) + ". " + this.todoList.indexTodo().get(i) + "\n";
                }
                return res.trim();
            case CREATE:
                this.todoList.createTodo(this.request);
                return "added: " + this.request;
            case MARK:
                int index = Integer.parseInt(this.request.split(" ", 2)[1]) - 1;

                this.todoList.markTodo(index);
                return "Nice! I've marked this task as done:\n" + this.todoList.showTodo(index);
            case UNMARK:
                index = Integer.parseInt(this.request.split(" ", 2)[1]) - 1;

                this.todoList.unmarkTodo(index);
                return "OK, I've marked this task as not done yet:\n" + this.todoList.showTodo(index);
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
        } else {
            this.requestType = RequestType.CREATE;
        }
    }
}
