import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import java.io.*;
public class Duke {
    //private Scanner sc = new Scanner(System.in);
    private static String FILEPATH = "./data/duke.txt";
    private TaskList list;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILEPATH);
        this.list = new TaskList(this.storage.loadData());
        this.parser = new Parser();
    }

    private enum DukeCommand {
        BYE, LIST,
        MARK, UNMARK,
        TODO, DEADLINE, EVENT,
        DELETE,
        LISTDATE
    }


    private void inputResponse() {
        while (true) {
            String input;
            input = this.ui.getUserInput();
            String[] userInput = this.parser.parseUserInput(input);
            String dukeQuery = userInput[0];
            DukeCommand dukeCommand= DukeCommand.valueOf(dukeQuery.toUpperCase());

            try {
                switch (dukeCommand) {
                    case BYE :
                        this.storage.storeData(this.list);
                        this.ui.exitDisplay();
                        this.ui.closeInput();
                        return;
                    case LIST :
                        this.ui.displayList(this.list);
                        break;
                    case MARK :
                        this.markComplete(userInput);
                        break;
                    case UNMARK :
                        this.markInComplete(userInput);
                        break;
                    case TODO :
                        this.addTodo(userInput);
                        break;
                    case DEADLINE :
                        this.addDeadline(userInput);
                        break;
                    case EVENT :
                        this.addEvent(userInput);
                        break;
                    case DELETE :
                        this.deleteTask(userInput);
                        break;
                    case LISTDATE:
                        this.displayTasksWithDates(userInput);
                        break;
                    default :
                        throw new DukeInvalidCommandException();

                }
            } catch (DukeException e) {
                System.out.printf("\t%s\n", e);
            } catch (IOException e) {
                System.out.println("Error while storing data..");
            }
            this.ui.printLine();
        }

    }

    private void displayList() {
        int listSize = this.list.listLength();
        for(int i = 1; i <= listSize; i++) {
            System.out.println("\t" + i + ". " + this.list.getTask(i).toString());
        }
    }

    private void markComplete(String[] userInput) throws DukeInvalidArgumentsException, DukeMissingArgumentException, DukeTaskArgumentException{
        try {
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.listLength()) {
                throw new DukeTaskArgumentException();
            }
            if (list.getTask(taskIndex).getStatus()) {
                throw new DukeTaskArgumentException();
            }

            Task taskToBeMarked = this.list.getTask(taskIndex);
            taskToBeMarked.changeStatus();
            this.ui.markTaskDisplay(taskToBeMarked);
        } catch (NumberFormatException e){
            throw new DukeInvalidArgumentsException();
        } catch (IndexOutOfBoundsException e) {
            String task = "mark";
            throw new DukeMissingArgumentException(task);
        }
    }

    private void markInComplete(String[] userInput) throws DukeMissingArgumentException, DukeInvalidArgumentsException, DukeTaskArgumentException{
        try {
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.listLength()) {
                throw new DukeTaskArgumentException();
            }
            if (list.getTask(taskIndex).getStatus() == false) {
                throw new DukeTaskArgumentException();
            }

            Task taskToBeUnmarked = this.list.getTask(taskIndex);
            taskToBeUnmarked.changeStatus();
            this.ui.unmarkTaskDisplay(taskToBeUnmarked);
        }  catch(IndexOutOfBoundsException e) {
            String task = "unmark";
            throw new DukeMissingArgumentException(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
    }

    private void addTodo(String[] userInput) throws DukeMissingArgumentException{
        try {
            Todo todo = new Todo(userInput[1]);
            this.list.addTask(todo);
            this.ui.taskAddDisplay(todo, this.list.listLength());
        } catch (IndexOutOfBoundsException e) {
            String task = "todo";
            throw new DukeMissingArgumentException(task);
        }

    }

    private void addDeadline(String[] userInput) throws DukeMissingArgumentException {
        try {
            String deadlineInfo[] = userInput[1].split("/by");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String deadlineText = deadlineInfo[0].trim();
            LocalDateTime deadlineDate = LocalDateTime.parse(deadlineInfo[1].trim(), formatter);
            Deadlines deadline = new Deadlines(deadlineText, deadlineDate);
            this.list.addTask(deadline);
            this.ui.taskAddDisplay(deadline, this.list.listLength());
        } catch(IndexOutOfBoundsException e) {
            String task = "deadline";
            throw new DukeMissingArgumentException(task);
        } catch(DateTimeParseException e) {
            System.out.println("\tInvalid Date format (Required format: DD/MM/YYYY HH:MM)");
        }
    }

    private void addEvent(String[] userInput) throws DukeMissingArgumentException{
        try {
            String eventInfo[] = userInput[1].split("/from|/to");
            String eventText = eventInfo[0].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime eventFrom = LocalDateTime.parse(eventInfo[1].trim(), formatter);
            LocalDateTime eventTo = LocalDateTime.parse(eventInfo[2].trim(), formatter);

            Event event = new Event(eventText, eventFrom, eventTo);
            this.list.addTask(event);
            this.ui.taskAddDisplay(event, this.list.listLength());
        } catch (IndexOutOfBoundsException e) {
            String task = "event";
            throw new DukeMissingArgumentException(task);
        } catch(DateTimeParseException e) {
            System.out.println("\tInvalid Date format (Required format: DD/MM/YYYY HH:MM)");
        }
    }

    private void deleteTask(String[] userInput) throws DukeTaskArgumentException, DukeMissingArgumentException, DukeInvalidArgumentsException{
        try{
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.listLength()) {
                throw new DukeTaskArgumentException();
            }
            this.ui.taskDeleteDisplay(this.list, taskIndex);
            this.list.deleteTask(taskIndex);
            this.ui.displayTasks(this.list.listLength());
        } catch(IndexOutOfBoundsException e) {
            String task = "delete";
            throw new DukeMissingArgumentException(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
    }


    private void displayTasksWithDates(String[] userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(userInput[1], formatter);
        int counter = 1;
        for(int i = 0; i < this.list.listLength(); i++) {
            String taskType = this.list.getTask(i).getTaskType();
            LocalDate taskDate = this.list.getTask(i).getDate().toLocalDate();
            if(taskType.equals("D") || taskType.equals(("E"))){
                if(date.equals(taskDate)) {
                    System.out.println(String.format("\t%d. %s", counter, this.list.getTask(i).toString()));
                }
            }

        }
    }

    public static void main(String[] args)  {
        Duke dukeObj = new Duke();
        dukeObj.ui.initialDisplay();
        dukeObj.inputResponse();
    }
}
