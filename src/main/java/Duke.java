import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public void run(){
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        tasks = new TaskList(storage.load());
        boolean continueRunning = true;
        Scanner scanner = new Scanner(System.in);
        while(continueRunning){
            String userInput = scanner.nextLine();
            String resultString = "";
            String errMsg = "";
            try{
                if (parser.isByeCommand(userInput)){
                    resultString = "______________________________________\n"
                            + "Bye. Hope to see you again soon!\n"
                            + "______________________________________\n";
                    continueRunning = false;
                } else if(parser.isListCommand(userInput)){
                    StringBuilder listOfInputs = new StringBuilder();
                    for(int i = 0; i < tasks.getUserTasks().size(); i++){
                        listOfInputs.append(i + 1)
                                .append(".")
                                .append(tasks.getUserTasks().get(i)).append("\n");
                    }
                    resultString = "______________________________________\n"
                            + "Here are the tasks in your list:\n"
                            + listOfInputs
                            + "______________________________________\n";
                } else if(parser.isMarkCommand(userInput)){
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace+1).isBlank()){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a task number you wish to mark as completed.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    try{
                        int numToMark = Integer.parseInt(userInput.split(" ")[1]);
                        if (numToMark == 0 || (numToMark > tasks.getUserTasks().size())){
                            errMsg = "______________________________________\n"
                                    + " ☹ OOPS!!! Invalid mark selection.\n"
                                    + "______________________________________\n";
                            throw new DukeException(errMsg);
                        }
                        tasks.getUserTasks().get(numToMark - 1).setIsDone(true);
                        resultString = "______________________________________\n"
                                + "Nice! I've marked this task as done:\n"
                                + tasks.getUserTasks().get(numToMark - 1) + "\n"
                                + "______________________________________\n";
                    } catch (NumberFormatException nfe){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a valid task number you wish to mark as completed.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    storage.saveTasksToFile(tasks.getUserTasks());
                } else if (parser.isUnmarkCommand(userInput)){
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace+1).isBlank()){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a task number you wish to mark as incomplete.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    try{
                        int numToUnmark = Integer.parseInt(userInput.split(" ")[1]);
                        if (numToUnmark == 0 || (numToUnmark > tasks.getUserTasks().size())){
                            errMsg = "______________________________________\n"
                                    + " ☹ OOPS!!! Invalid unmark selection.\n"
                                    + "______________________________________\n";
                            throw new DukeException(errMsg);
                        }
                        tasks.getUserTasks().get(numToUnmark - 1).setIsDone(false);
                        resultString = "______________________________________\n"
                                + "OK, I've marked this task as not done yet:\n"
                                + tasks.getUserTasks().get(numToUnmark - 1) + "\n"
                                + "______________________________________\n";
                    } catch (NumberFormatException nfe){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a valid task number you wish to mark as incomplete.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    storage.saveTasksToFile(tasks.getUserTasks());
                } else if (parser.isDeleteCommand(userInput)){
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    if (indexOfFirstSpace == -1|| userInput.substring(indexOfFirstSpace+1).isBlank()){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a task number you wish to delete.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }

                    try{
                        int numToDelete = Integer.parseInt(userInput.split(" ")[1]);
                        if (numToDelete == 0 || (numToDelete > tasks.getUserTasks().size())){
                            errMsg = "______________________________________\n"
                                    + " ☹ OOPS!!! Invalid delete selection.\n"
                                    + "______________________________________\n";
                            throw new DukeException(errMsg);
                        }

                        resultString = "______________________________________\n"
                                + "Noted, I've removed this task: \n"
                                + tasks.getUserTasks().get(numToDelete- 1) + "\n";
                        tasks.getUserTasks().remove(numToDelete-1);
                        resultString += "Now you have " + tasks.getUserTasks().size() + " tasks in the list.\n"
                                + "______________________________________\n";
                    } catch (NumberFormatException nfe){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a valid task number you wish to delete.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    storage.saveTasksToFile(tasks.getUserTasks());
                } else if(parser.isTodoCommand(userInput)){
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    String taskDescription = userInput.substring(indexOfFirstSpace+1);
                    if(indexOfFirstSpace == -1 || taskDescription.isBlank()){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The description of a todo cannot be empty.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    Todo newTodo = new Todo(taskDescription);
                    tasks.getUserTasks().add(newTodo);
                    resultString = "______________________________________\n"
                            + "Got it. I've added this task:\n"
                            + newTodo + "\n"
                            + "Now you have " + tasks.getUserTasks().size() + " tasks in the list.\n"
                            + "______________________________________\n";
                    storage.saveTasksToFile(tasks.getUserTasks());
                }
                else if(parser.isDeadlineCommand(userInput)){
                    int indexOfBy = userInput.indexOf("/by");
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    if(indexOfFirstSpace == -1 || indexOfBy == -1){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please include /by followed by the actual deadline.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    if(indexOfBy + 4 >= userInput.length()){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The deadline specified after /by cannot be empty.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    String[] s = userInput.substring(indexOfBy + 4).split(" ");
                    if (s.length != 2){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The task deadline must be defined as '/by YYYY-MM-DD HH:MM' .\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    String deadlineDate = s[0];
                    String deadlineTime = s[1];
                    if (indexOfFirstSpace+1 > indexOfBy-1){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The task description for a deadline cannot be empty.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    try{
                        LocalDate parsedDate = LocalDate.parse(deadlineDate);
                        Date parsedTime = new SimpleDateFormat("hh:mm").parse(deadlineTime);
                        String taskDescription = userInput.substring(indexOfFirstSpace+1,indexOfBy-1);
                        if(taskDescription.isBlank()){
                            errMsg = "______________________________________\n"
                                    + " ☹ OOPS!!! The task description for a deadline cannot be empty.\n"
                                    + "______________________________________\n";
                            throw new DukeException(errMsg);
                        }
                        Deadline newDeadline = new Deadline(taskDescription, parsedDate, parsedTime, false);
                        tasks.getUserTasks().add(newDeadline);
                        resultString = "______________________________________\n"
                                + "Got it. I've added this task:\n"
                                +  newDeadline + "\n"
                                + "Now you have " + tasks.getUserTasks().size() + " tasks in the list.\n"
                                + "______________________________________\n";
                        storage.saveTasksToFile(tasks.getUserTasks());
                    } catch (DateTimeParseException | ParseException e){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The task deadline must be defined as '/by YYYY-MM-DD HH:MM' .\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                }
                else if(parser.isEventCommand(userInput)){
                    int indexOfFrom = userInput.indexOf("/from");
                    int indexOfTo = userInput.indexOf("/to");
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    if (indexOfFirstSpace == -1 || indexOfFrom == -1 || indexOfTo == -1){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The event command must contain both /from and /to\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    if (indexOfFrom > indexOfTo){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! /from cannot be after /to\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    String eventStart = userInput.substring(indexOfFrom+6, indexOfTo-1);
                    String eventEnd = userInput.substring(indexOfTo+4);

                    String[] eventStartSplitStr = eventStart.split(" ");
                    String[] eventEndSplitStr = eventEnd.split(" ");

                    if (eventStartSplitStr.length != 2 || eventEndSplitStr.length != 2){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The event start and the event end must defined " +
                                "as '/from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM' .\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    String eventStartDate = eventStartSplitStr[0];
                    String eventStartTime = eventStartSplitStr[1];
                    String eventEndDate = eventEndSplitStr[0];
                    String eventEndTime = eventEndSplitStr[1];

                    if (indexOfFirstSpace+1 > indexOfFrom-1){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The event description cannot be empty!\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }

                    try{
                        LocalDate parsedStartDate = LocalDate.parse(eventStartDate);
                        LocalDate parsedEndDate = LocalDate.parse(eventEndDate);
                        Date parsedStartTime = new SimpleDateFormat("hh:mm").parse(eventStartTime);
                        Date parsedEndTime = new SimpleDateFormat("hh:mm").parse(eventEndTime);
                        String taskDescription = userInput.substring(indexOfFirstSpace+1,indexOfFrom-1);
                        if(taskDescription.isBlank()){
                            errMsg = "______________________________________\n"
                                    + " ☹ OOPS!!! The Event description cannot be empty!\n"
                                    + "______________________________________\n";
                            throw new DukeException(errMsg);
                        }
                        Event newEvent = new Event(taskDescription, parsedStartDate, parsedStartTime, parsedEndDate, parsedEndTime, false);
                        tasks.getUserTasks().add(newEvent);
                        resultString = "______________________________________\n"
                                + "Got it. I've added this task:\n"
                                + newEvent + "\n"
                                + "Now you have " + tasks.getUserTasks().size() + " tasks in the list.\n"
                                + "______________________________________\n";
                        storage.saveTasksToFile(tasks.getUserTasks());
                    } catch (DateTimeParseException | ParseException e){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The event start and the event end must defined " +
                                "as '/from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM' .\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                } else {
                    errMsg = "______________________________________\n"
                            + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + "______________________________________\n";
                    throw new DukeException(errMsg);
                }
                ui.showResult(resultString);
            } catch(DukeException e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
