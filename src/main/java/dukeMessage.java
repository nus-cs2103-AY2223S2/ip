public class DukeMessage extends Message {

    MessageStatus status;

    DukeMessage(MessageStatus status) {
        super("");
        this.status = status;
    }

    DukeMessage(MessageStatus status, String content) {
        super(content);
        this.status = status;
    }



    @Override
    public String toString() {
        String reply;

        switch (this.status) {
            case ADD:
            case MARK:
            case LIST:
            case DELETE:
                reply = this.getContent();
                break;
            case START:
                reply = "Hello! I'm Duke\nWhat can I do for you?";
                break;
            case END:
                // End conversation
                reply = "Bye. Hope to see you again soon!";
                break;
            default:
                reply = "Invalid";
        }

        return String.format("%s\n", reply);
    }

}
