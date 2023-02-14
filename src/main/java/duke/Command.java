package duke;

import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeTaskArgumentException;
import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeInvalidArgumentsException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;


public class Command {
    private enum DukeCommand {
        BYE, LIST,
        MARK, UNMARK,
        TODO, DEADLINE, EVENT,
        DELETE,
        LISTDATE, FIND,
        FAIL
    }

    private DukeCommand currCommand;
    private String commandResponse;
    private Ui ui;
    private TaskList list;

    public Command(String dukeQuery, TaskList list) {
        this.ui = new Ui();
        this.list = list;
        for (DukeCommand c : DukeCommand.values()) {
            if (c.name().equals(dukeQuery.toUpperCase())) {
                this.currCommand = DukeCommand.valueOf(dukeQuery.toUpperCase());
                break;
            } else {
                this.currCommand = DukeCommand.FAIL;
            }
        }
    }

    public DukeCommand getCommand() {
        return currCommand;
    }

    public String executeCommand(TaskList list, String[] userInput) {
        try {
            switch (this.currCommand) {
            case BYE :
                commandResponse = this.ui.exitDisplay();
                break;
            case LIST :
                commandResponse = this.ui.displayList(list);
                break;
            case MARK :
                commandResponse = this.list.markComplete(userInput, list);
                break;
            case UNMARK :
                commandResponse = this.list.markIncomplete(userInput, list);
                break;
            case TODO :
                commandResponse = this.addTodo(userInput);
                break;
            case DEADLINE :
                commandResponse = this.addDeadline(userInput);
                break;
            case EVENT :
                commandResponse = this.addEvent(userInput);
                break;
            case DELETE :
                commandResponse = this.deleteTask(userInput);
                break;
            case LISTDATE:
                commandResponse = this.displayTasksWithDates(userInput);
                break;
            case FIND:
                commandResponse = this.findTasks(userInput);
                break;
            case FAIL :
                throw new DukeInvalidCommandException();
            default :
                commandResponse = "Please try again !!";
            }
        } catch (DukeException e) {
            commandResponse = String.format("%s\n", e);
        }

        return commandResponse;
    }

    private String addTodo(String[] userInput) throws DukeMissingArgumentException{
        try {
            Todo todo = new Todo(userInput[1]);
            this.list.addTask(todo);
            return this.ui.taskAddDisplay(todo, this.list.getListLength());
        } catch (IndexOutOfBoundsException e) {
            String task = "todo";
            throw new DukeMissingArgumentException(task);
        }
    }

    private String addDeadline(String[] userInput) throws DukeMissingArgumentException {
        try {
            String deadlineInfo[] = userInput[1].split("/by");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String deadlineText = deadlineInfo[0].trim();
            LocalDateTime deadlineDate = LocalDateTime.parse(deadlineInfo[1].trim(), formatter);
            Deadline deadline = new Deadline(deadlineText, deadlineDate);
            this.list.addTask(deadline);
            return this.ui.taskAddDisplay(deadline, this.list.getListLength());
        } catch(IndexOutOfBoundsException e) {
            String task = "deadline";
            throw new DukeMissingArgumentException(task);
        } catch(DateTimeParseException e) {
            return "Invalid Date format (Required format: DD/MM/YYYY HH:MM)";
        }
    }

    private String addEvent(String[] userInput) throws DukeMissingArgumentException{
        try {
            String eventInfo[] = userInput[1].split("/from|/to");
            String eventText = eventInfo[0].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime eventFrom = LocalDateTime.parse(eventInfo[1].trim(), formatter);
            LocalDateTime eventTo = LocalDateTime.parse(eventInfo[2].trim(), formatter);

            Event event = new Event(eventText, eventFrom, eventTo);
            this.list.addTask(event);
            return this.ui.taskAddDisplay(event, this.list.getListLength());
        } catch (IndexOutOfBoundsException e) {
            String task = "event";
            throw new DukeMissingArgumentException(task);
        } catch(DateTimeParseException e) {
            return "Invalid Date format (Required format: DD/MM/YYYY HH:MM)";
        }
    }

    private String deleteTask(String[] userInput) throws DukeTaskArgumentException,
            DukeMissingArgumentException, DukeInvalidArgumentsException {
        try {
            String indexString = userInput[1];
            String[] listIndices = indexString.split(",");
            System.out.println(listIndices[0]);
            for (int i = 0; i < listIndices.length; i++) {
                int taskIndex = Integer.parseInt(listIndices[i]);
                if (taskIndex > this.list.getListLength()) {
                    throw new DukeTaskArgumentException();
                }
            }

            System.out.println(listIndices.length);

            String deleteResponse = this.ui.taskDeleteDisplay(this.list, listIndices)
                    + this.ui.displayTasks(this.list.getListLength() - listIndices.length);
            this.list.deleteTask(listIndices);
            return deleteResponse;
        } catch(IndexOutOfBoundsException e) {
            String task = "delete";
            throw new DukeMissingArgumentException(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
    }

    private String displayTasksWithDates(String[] userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(userInput[1], formatter);
        int counter = 1;
        String response = "";

        for(int i = 0; i < this.list.getListLength(); i++) {
            String taskType = this.list.getTask(i).getTaskType();
            LocalDate taskDate = this.list.getTask(i).getDate().toLocalDate();
            if(taskType.equals("D") || taskType.equals(("E"))) {
                if(date.equals(taskDate)) {
                    response += String.format("\t%d. %s", counter,
                            this.list.getTask(i).toString()) + '\n';
                }
            }
        }
        assert response != null;
        return response;
    }

    private String findTasks(String[] userInput) {
        String keyword = userInput[1];
        if(this.list.isEmpty()) {
            return "There is not no task in the list.";
        }

        ArrayList<Task> filteredTasks= this.list.getFilteredTasks(keyword);
        TaskList foundTasks = new TaskList(filteredTasks);

        if(foundTasks.isEmpty()) {
            return this.ui.noMatchFoundDisplay();
        } else {
            return this.ui.matchFoundDisplay(foundTasks);
        }
    }

}
