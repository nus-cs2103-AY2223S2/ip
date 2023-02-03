import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Parser {
    final static String EXIT_COMMAND = "bye";
    final static String LIST_COMMAND = "list";
    final static String DELETE_COMMAND = "delete";
    final static String MARK_COMMAND = "mark";
    final static String UNMARK_COMMAND = "unmark";
    final static String TODO_COMMAND = "todo";
    final static String DEADLINE_COMMAND = "deadline";
    final static String EVENT_COMMAND = "event";
    final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/d HHmm");
    final static DateTimeFormatter outputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;



    void parse(String input, Ui ui, TaskList list) throws IOException {
        try {
            String[] inputWords = input.split(" ", 2);
            String command = inputWords[0];
            if (command.equals(EXIT_COMMAND)) {
                ui.printBye();
                ui.isClosed = true;
            } else if (command.equals(LIST_COMMAND)) {
                ui.printGetList(list);
            } else if (isMark(command)) {
                this.handleMark(inputWords, ui, list);
            } else if (isUnmark(command)) {
                this.handleUnmark(inputWords, ui, list);
            } else if (isToDo(command)) {
                this.handleToDo(inputWords, ui, list);
            } else if (isDeadline(command)) {
                this.handleDeadline(inputWords, ui, list);
            } else if (isEvent(command)) {
                this.handleEvent(inputWords, ui, list);
            } else if (command.equals(DELETE_COMMAND)) {
                this.handleDelete(inputWords, ui, list);
            } else {
                throw new InvalidInputException();
            }
        } catch (Exception e) {
            ui.printException(e);
        }
    }

    public void handleDelete(String[] inputWords, Ui ui, TaskList list) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.getTask(index - 1);
        list.removeTask(index-1);
        ui.printHandleDelete(task, list);
    }

    public  void handleMark(String[] inputWords, Ui ui, TaskList list) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.getTask(index - 1);
        task.mark();
        ui.printHandleMark(task);
    }

    public void handleUnmark(String[] inputWords, Ui ui, TaskList list) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.getTask(index - 1);
        task.unmark();
        ui.printHandleUnmark(task);
    }

    public void handleToDo(String[] inputWords, Ui ui, TaskList list) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String description = inputWords[1];
        Task newTask = new ToDo(description);
        list.addTask(newTask);
        ui.printAddTask(newTask, list);
    }

    public void handleDeadline(String[] inputWords, Ui ui, TaskList list) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String[] splitedString = inputWords[1].split(" /by ");
        String action = splitedString[0];
        String date = splitedString[1]; // in yyyy/mm/d HHMM format
        LocalDateTime inputDateTime = LocalDateTime.parse(date, inputFormatter);
        String outputDateTime = inputDateTime.format(outputFormatter);
        Task newTask = new Deadline(action, outputDateTime);
        list.addTask(newTask);
        ui.printAddTask(newTask, list);
    }

    public void handleEvent(String[] inputWords, Ui ui, TaskList list) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String[] splitedString = inputWords[1].split(" /from ");
        String action = splitedString[0];
        String duration = splitedString[1];
        String[] fromTo = duration.split(" /to ");
        String from = fromTo[0];
        String to = fromTo[1];
        Task newTask = new Event(action, from, to);
        list.addTask(newTask);
        ui.printAddTask(newTask, list);
    }
    public void checkEmptyDescription(String[] inputWords) throws EmptyDescriptionException {
        if (inputWords.length < 2) {
            throw new EmptyDescriptionException();
        }
    }

    public boolean isMark(String word) {
        return word.equals(MARK_COMMAND);
    }

    public boolean isUnmark(String word) {
        return word.equals(UNMARK_COMMAND);
    }

    public boolean isToDo(String word) {
        return word.equals(TODO_COMMAND);
    }

    public boolean isDeadline(String word) {
        return word.equals(DEADLINE_COMMAND);
    }

    public boolean isEvent(String word) {
        return word.equals(EVENT_COMMAND);
    }

}
