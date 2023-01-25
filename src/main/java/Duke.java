import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    public enum CmdType{
        bye,
        list,
        mark,
        unmark,
        delete,
        todo,
        deadline,
        event,
    }
    public static void main(String[] args) {

        ArrayList<Task> userTasks = new ArrayList<Task>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        boolean continueRunning = true;
        String greeting = "______________________________________\n"
                + "Hey there buddy! I'm Duke. Your Personal Task Assistant!\n"
                + "What can I do for you today?\n"
                + "______________________________________\n";
        System.out.print(greeting);
        Scanner scanner = new Scanner(System.in);

        while(continueRunning){
            String userInput = scanner.nextLine();
            String resultString = "";
            String errMsg = "";
            try{
                if (userInput.replaceAll("\\s","").equals(CmdType.bye.name())){
                    resultString = "______________________________________\n"
                            + "Bye. Hope to see you again soon!\n"
                            + "______________________________________\n";
                    continueRunning = false;
                } else if(userInput.replaceAll("\\s", "").equals(CmdType.list.name())){
                    StringBuilder listOfInputs = new StringBuilder();
                    for(int i = 0; i < Task.getNumTasks(); i++){
                        listOfInputs.append(i + 1)
                                .append(".")
                                .append(userTasks.get(i)).append("\n");
                    }
                    resultString = "______________________________________\n"
                            + "Here are the tasks in your list:\n"
                            + listOfInputs
                            + "______________________________________\n";
                } else if((userInput.split(" ")[0]).equals(CmdType.mark.name())){
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace+1).isBlank()){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a task number you wish to mark as completed.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    try{
                        int numToMark = Integer.parseInt(userInput.split(" ")[1]);
                        if (numToMark == 0 || (numToMark > Task.getNumTasks())){
                            errMsg = "______________________________________\n"
                                    + " ☹ OOPS!!! Invalid mark selection.\n"
                                    + "______________________________________\n";
                            throw new DukeException(errMsg);
                        }
                        userTasks.get(numToMark - 1).setIsDone(true);
                        resultString = "______________________________________\n"
                                + "Nice! I've marked this task as done:\n"
                                + userTasks.get(numToMark - 1) + "\n"
                                + "______________________________________\n";
                    } catch (NumberFormatException nfe){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a valid task number you wish to mark as completed.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                } else if((userInput.split(" ")[0]).equals(CmdType.unmark.name())){
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace+1).isBlank()){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a task number you wish to mark as incomplete.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    try{
                        int numToUnmark = Integer.parseInt(userInput.split(" ")[1]);
                        if (numToUnmark == 0 || (numToUnmark > Task.getNumTasks())){
                            errMsg = "______________________________________\n"
                                    + " ☹ OOPS!!! Invalid unmark selection.\n"
                                    + "______________________________________\n";
                            throw new DukeException(errMsg);
                        }
                        userTasks.get(numToUnmark - 1).setIsDone(false);
                        resultString = "______________________________________\n"
                                + "OK, I've marked this task as not done yet:\n"
                                + userTasks.get(numToUnmark - 1) + "\n"
                                + "______________________________________\n";
                    } catch (NumberFormatException nfe){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a valid task number you wish to mark as incomplete.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                } else if (userInput.split(" ")[0].equals(CmdType.delete.name())){
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    if (indexOfFirstSpace == -1|| userInput.substring(indexOfFirstSpace+1).isBlank()){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a task number you wish to delete.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }

                    try{
                        int numToDelete = Integer.parseInt(userInput.split(" ")[1]);
                        if (numToDelete == 0 || (numToDelete > Task.getNumTasks())){
                            errMsg = "______________________________________\n"
                                    + " ☹ OOPS!!! Invalid delete selection.\n"
                                    + "______________________________________\n";
                            throw new DukeException(errMsg);
                        }
                        Task.deleteTask();
                        resultString = "______________________________________\n"
                                + "Noted, I've removed this task: \n"
                                + userTasks.get(numToDelete- 1) + "\n"
                                + "Now you have " + Task.getNumTasks() + " tasks in the list.\n"
                                + "______________________________________\n";
                        userTasks.remove(numToDelete-1);
                    } catch (NumberFormatException nfe){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! Please supply a valid task number you wish to delete.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }

                }else if(userInput.split(" ")[0].equals(CmdType.todo.name())){
                    int indexOfFirstSpace = userInput.indexOf(" ");
                    String taskDescription = userInput.substring(indexOfFirstSpace+1);
                    if(indexOfFirstSpace == -1 || taskDescription.isBlank()){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The description of a todo cannot be empty.\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                    Todo newTodo = new Todo(taskDescription);
                    userTasks.add(newTodo);
                    Task.addTask();
                    resultString = "______________________________________\n"
                            + "Got it. I've added this task:\n"
                            + newTodo + "\n"
                            + "Now you have " + Task.getNumTasks() + " tasks in the list.\n"
                            + "______________________________________\n";
                }
                else if(userInput.split(" ")[0].equals(CmdType.deadline.name())){
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
                        Deadline newDeadline = new Deadline(taskDescription, parsedDate, parsedTime);
                        userTasks.add(newDeadline);
                        Task.addTask();
                        resultString = "______________________________________\n"
                                + "Got it. I've added this task:\n"
                                +  newDeadline + "\n"
                                + "Now you have " + Task.getNumTasks() + " tasks in the list.\n"
                                + "______________________________________\n";
                    } catch (DateTimeParseException | ParseException e){
                        errMsg = "______________________________________\n"
                                + " ☹ OOPS!!! The task deadline must be defined as '/by YYYY-MM-DD HH:MM' .\n"
                                + "______________________________________\n";
                        throw new DukeException(errMsg);
                    }
                }
                else if(userInput.split(" ")[0].equals(CmdType.event.name())){
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
                        Event newEvent = new Event(taskDescription, parsedStartDate, parsedStartTime, parsedEndDate, parsedEndTime);
                        userTasks.add(newEvent);
                        Task.addTask();
                        resultString = "______________________________________\n"
                                + "Got it. I've added this task:\n"
                                + newEvent + "\n"
                                + "Now you have " + Task.getNumTasks() + " tasks in the list.\n"
                                + "______________________________________\n";
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
                System.out.print(resultString);
            } catch(DukeException e){
                System.out.println(e);
            }
        }
    }
}
