public class MessageProcessor {
    TaskList taskList;

    MessageProcessor(TaskList taskList) {
        this.taskList = taskList;
    }

    DukeMessage process(String message) {
        MessageStatus status;
        if (message.equals("bye")) {
            status = MessageStatus.END;
        } else if (message.equals("list")){
            status = MessageStatus.LIST;
            message = this.taskList.toString();
        } else {
            status = MessageStatus.ADD;
            taskList.addTask(message);
        }

        return new DukeMessage(status, message);
    }

}
