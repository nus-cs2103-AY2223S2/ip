package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    Parser(TaskList taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
        this.storage = storage;
    }

    public void parseInput(String input) throws DukeException {
        try{
            if (input.equals("bye")) {
                this.storage.save(taskList);
                ui.goodbyeMessage();
            } else if (input.equals("list")){
                ui.printList(taskList.getList());
            } else if (input.startsWith("mark")) {
                markInputChecker(input);
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                taskList.markTaskAsDone(taskNum);
                ui.markTaskAsDoneMessage(taskList.getTask(taskNum));
            } else if (input.startsWith("unmark")) {
                unmarkInputChecker(input);
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                taskList.markTaskAsIncomplete(taskNum);

                ui.markTaskAsIncompleteMessage(taskList.getTask(taskNum));
            } else if (input.startsWith("delete")) {
                deleteInputChecker(input);
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                Task toDelete = taskList.getTask(taskNum);
                taskList.deleteTaskFromList(taskNum);

                ui.deletedTaskMessage(toDelete, taskList.numberOfTasks());
            } else if (isEventTask(input)) {
                eventInputChecker(input);
                String[] eventConstructor = input.replace("event ", "").split("/at ");
                String timeModified = eventConstructor[1].replace("from ", "");
                inputEvent(eventConstructor[0], timeModified);

            } else if (isDeadlineTask(input)) {
                deadlineInputChecker(input);
                addDeadlineFormatted(input);

            } else if (isTodoTask(input)) {
                todoInputChecker(input);
                inputTodo(input.replace("todo ", ""));

            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }
    }

    public boolean isDeadlineTask(String s) {
        return s.startsWith("deadline");
    }

    public boolean isTodoTask(String s) {
        return s.startsWith("todo");
    }

    public boolean isEventTask(String s) {
        return s.startsWith("event");
    }

    public void todoInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        }
    }

    public void eventInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
        }
    }

    public void deadlineInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
        }
    }

    public void deleteInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! You have to choose a task to delete.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! You have to choose a task to delete.");
            }
        }
    }

    public void markInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! You have to choose a task to mark.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! You have to choose a task to mark.");
            }
        }
    }

    public void unmarkInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! You have to choose a task to unmark.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! You have to choose a task to delete.");
            }
        }
    }

    public void addDeadlineFormatted(String input) throws DukeException {
        String[] constructor = input.replace("deadline ", "").split(" /by ");
        try{
            LocalDate temp = LocalDate.parse(constructor[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            inputDeadline(constructor[0], temp);

        } catch (Exception e) {
            throw new DukeException("Please input date in format of dd/MM/yyyy");
        }
    }

    public void inputEvent(String s, String time) {
        Event event = new Event(s, time);
        taskList.add(event);
        ui.addedTaskMessage(event, taskList.numberOfTasks());
    }

    public void inputDeadline(String s, LocalDate d) {
        Deadline deadline = new Deadline(s, d);
        taskList.add(deadline);
        ui.addedTaskMessage(deadline, taskList.numberOfTasks());
    }

    public void inputTodo(String s) {
        Todo todo = new Todo(s);
        taskList.add(todo);
        ui.addedTaskMessage(todo, taskList.numberOfTasks());
    }
}
