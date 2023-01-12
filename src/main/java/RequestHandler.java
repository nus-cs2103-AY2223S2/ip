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
//                TODO: add mark task
                return "";
            case UNMARK:
//                TODO: add unmark task
                return "";
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
