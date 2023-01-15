public class MessageProcessor {
    TaskList taskList;

    MessageProcessor(TaskList taskList) {
        this.taskList = taskList;
    }

    private Task processMark(String message) {
        String[] messageSplit = message.split(" ");
        String action = messageSplit[0];
        int taskNum = Integer.parseInt(messageSplit[1]);

        this.taskList.markTask(action, taskNum);

        return this.taskList.getTask(taskNum);
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

    private String generateTaskMessage(MessageStatus status, Task task) {
        String heading = "";
        String end = "";

        switch (status) {
            case MARK:
                heading = task.getDoneStatus()
                        ? "Nice! I've marked this task as done:"
                        : "OK, I've marked this task as not done yet:";
                break;
            case ADD:
                heading = "Got it. I've added this task:";
                end = String.format("\nNow you have %d tasks in the list.", this.taskList.getTaskCount());
                break;
            case DELETE:
                heading = "Noted. I've removed this task:";
                end = String.format("\nNow you have %d tasks in the list.", this.taskList.getTaskCount());
                break;
            default:
                break;
        }

        return String.format("%s\n%s%s", heading, task.toString(), end);
    }


    private String generateListMessage() {
        String heading = "Here are the tasks in your list:\n";
        return heading + this.taskList.toString();
    }

    DukeMessage process(String message)
            throws InvalidInputException, InvalidTodoException, InvalidDeadlineException, InvalidEventException {

        MessageStatus status;
        if (message.equals("bye")) {
            status = MessageStatus.END;
        } else if (message.equals("list")){
            status = MessageStatus.LIST;
            message = generateListMessage();
        } else if (isMark(message)) {
            Task task = processMark(message);
            status = MessageStatus.MARK;
            message = generateTaskMessage(status, task);
        } else if (isAdd(message)) {
            Task task = taskList.addTask(message);
            status = MessageStatus.ADD;
            message = generateTaskMessage(status, task);
        } else if (isDelete(message)) {
            Task task = taskList.deleteTask(message);
            status = MessageStatus.DELETE;
            message = generateTaskMessage(status, task);
        } else {
            throw new InvalidInputException();
        }

        return new DukeMessage(status, message);
    }

}
