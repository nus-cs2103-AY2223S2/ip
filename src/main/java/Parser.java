public class Parser {


    Parser() {

    }

    private boolean isMark(String message) {
        String[] messageSplit = message.split(" ");
        if (messageSplit.length != 2) {
            return false;
        }
        String action = messageSplit[0];

        if ((action.equals("mark") || action.equals("unmark"))) {
            return true;
        }
        return false;

    }

    private boolean isAdd(String message) {
        String[] messageSplit = message.split(" ");
        String action = messageSplit[0];
        return (action.equals("todo") || action.equals("deadline") || action.equals("event"));
    }

    private boolean isDelete(String message) {
        String[] messageSplit = message.split(" ");
        String action = messageSplit[0];
        return action.equals("delete");
    }

    MessageStatus process(String message) throws InvalidInputException {

        MessageStatus status;
        if (message.equals("bye")) {
            status = MessageStatus.END;
        } else if (message.equals("list")){
            status = MessageStatus.LIST;
        } else if (isMark(message)) {
            status = MessageStatus.MARK;
        } else if (isAdd(message)) {
            status = MessageStatus.ADD;
        } else if (isDelete(message)) {
            status = MessageStatus.DELETE;
        } else {
            throw new InvalidInputException();
        }

        return status;
    }
}
