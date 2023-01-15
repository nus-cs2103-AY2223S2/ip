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

    private String generateMarkMessage(Task task) {
        String heading = task.getDoneStatus()
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        return String.format("%s\n%s", heading, task.toString());
    }

    private String generateListMessage() {
        String heading = "Here are the tasks in your list:\n";
        return heading + this.taskList.toString();
    }

    private String generateAddMessage(Task task) {
        String header = "Got it. I've added this task:\n";
        String end = String.format("Now you have %d tasks in the list.", this.taskList.getTaskCount());

        return header + task.toString() + "\n" + end;
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
            message = generateMarkMessage(task);
        } else if (isAdd(message)) {
            Task task = taskList.addTask(message);
            status = MessageStatus.ADD;
            message = generateAddMessage(task);
        } else {
            throw new InvalidInputException();
        }

        return new DukeMessage(status, message);
    }

}
