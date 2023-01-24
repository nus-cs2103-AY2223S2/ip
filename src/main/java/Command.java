import java.time.LocalDate;

public class Command {
    private Ui ui;
    private Parser parser;

    public Command() {
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public void executeCommand(Parser.Action action, String userInput, TaskList tasks) throws DukeException {
        switch(action) {
            case LIST:
                this.listTasksCommand(tasks);
                break;
            case TODO:
                tasks.addTask(new ToDo(this.parser.getTodoDescription(userInput)));
                break;
            case DEADLINE:
                tasks.addTask(new Deadline(this.parser.getDeadlineDescription(userInput),
                        this.parser.getDeadlineDate(userInput)));
                break;
            case EVENT:
                LocalDate[] eventDetails = this.parser.getEventDateDetails(userInput);
                tasks.addTask(new Event(this.parser.getEventDescription(userInput),
                        eventDetails[0], eventDetails[1]));
                break;
            case MARK:
                tasks.markTask(this.parser.getTaskIndex(userInput));
                break;
            case UNMARK:
                tasks.unmarkTask(this.parser.getTaskIndex(userInput));
                break;
            case DELETE:
                tasks.removeTask(this.parser.getTaskIndex(userInput));
                break;
            case UNKNOWN:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public void listTasksCommand (TaskList store) throws DukeException {
        try {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < store.getSize(); i ++) {
                ui.sendTaskDetails(i + 1, store.getTask(i));
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
