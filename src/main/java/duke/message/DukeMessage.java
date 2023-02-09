package duke.message;

/**
 * Represents a response message by Duke to the user. A DukeMessage object
 * corresponds to a message consisting of either a message status only or
 * message status and string content.
 */
public class DukeMessage extends Message {

    MessageStatus status;

    public DukeMessage(MessageStatus status) {
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
        case SORT:
        case MARK:
        case LIST:
        case DELETE:
        case FIND:
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
