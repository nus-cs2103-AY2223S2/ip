package duke.util;
import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;


public class Parser {

    private static TaskList taskList = new TaskList();

    /**
     * Returns a boolean to signify the termination of the chatbot.
     * It handles the user input.
     *
     * @return Whether chatbot is still running.
     * @throws DukeException If input is empty.
     */
    public static boolean talk() throws DukeException{
        Scanner myObj = new Scanner(System.in);
        String inp = myObj.nextLine();
        if (inp.equals("")) {
            throw new DukeException("Empty Input!");
        }
        while (!inp.equals("bye")) {
            operationHandler(inp);
            System.out.println("done >.<");
            inp = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        return false;
    }

    /**
     * Process the user input and react/output according.
     *
     * @param task String user inputted string for processing.
     * @throws DukeException If action is invalid or if task description is empty.
     */
    public static String operationHandler(String task) throws DukeException{
        String[] inpArr = task.split(" ");
        int deadlineLength = 9;
        int eventLength = 6;
        int snoozeLength = 7;
        if (inpArr[0].equals("list")) {
            return list();
        } else if (inpArr[0].equals("mark")) {
            taskList.markDone(Integer.parseInt(inpArr[1]) - 1);
            return "Marked for you!";
        } else if (inpArr[0].equals("unmark")) {
            taskList.markUndone(Integer.parseInt(inpArr[1]) - 1);
            return "Unmarked for you!";
        } else {
            // add tasks
            if (inpArr[0].equals("todo")) {
                if (task.length() == 4) {
                    throw new DukeException("Description cannot be empty!");
                }
                assert inpArr.length > 1: "input array elements are wrong";
                int todoLength = 5;
                ToDo newToDo = new ToDo(task.substring(todoLength), task);
                taskList.add(newToDo);
                return "Added :)";
            } else if (inpArr[0].equals("deadline")) { // need to handle exception
                assert inpArr.length > 1: "input array elements are wrong";
                String[] processedString = stringProcessorForDatedTasks(true, task.substring(deadlineLength));
                Deadline newDeadline = new Deadline(processedString[0], task,
                        LocalDate.parse(processedString[1]));
                taskList.add(newDeadline);
                return "Added :)";
            } else if (inpArr[0].equals("event")){ // need to handle exception
                assert inpArr.length > 1: "input array elements are wrong";
                String[] processedString = stringProcessorForDatedTasks(false, task.substring(eventLength));
                Event newEvent = new Event(processedString[0], task, LocalDate.parse(processedString[1]),
                        LocalDate.parse(processedString[2]));
                taskList.add(newEvent);
                return "Added :)";
            } else if (inpArr[0].equals("delete")){ // need to handle exception
                assert inpArr.length > 1: "input array elements are wrong";
                taskList.remove(Integer.parseInt(inpArr[1])-1);
                return "Deleted for you";
            } else if (inpArr[0].equals("find")) {
                System.out.println("here ya go :)");
                return taskList.find(inpArr[1]);
            } else if (inpArr[0].equals("snooze")){
                String[] datedTask = task.split("/");
                if (datedTask.length == 2) {
                    String[] processedString = stringProcessorForDatedTasks(true, task.substring(snoozeLength));
                    Deadline deadlineToBeUpdated = (Deadline) taskList.get(Integer.parseInt(inpArr[1])-1);
                    deadlineToBeUpdated.updateDate(LocalDate.parse(processedString[1]));
                    return "Snoozed zzz.";
                } else if (datedTask.length == 3) {
                    String[] processedString = stringProcessorForDatedTasks(false, task.substring(snoozeLength));
                    Event eventToBeUpdated = (Event) taskList.get(Integer.parseInt(inpArr[1])-1);
                    eventToBeUpdated.updateDate(LocalDate.parse(processedString[1]), LocalDate.parse(processedString[2]));
                    return "Snoozed zzz.";
                } else {
                    return "Your input is wrong.";
                }
            } else {
                //throw new DukeException("Invalid Input!");
                return "I am sorry, I don't understand.";
            }
        }
    }

    /**
     * Returns an array of processed String for the actions "Deadline" and "Event".
     * Separate the name and the date(s).
     *
     * @param isDeadline boolean whether is it deadline or event.
     * @param s String string for processing.
     * @return String[] processed String.
     */
    private static String[] stringProcessorForDatedTasks(boolean isDeadline, String s){
        if (isDeadline){
            String[] processedArr = new String[2];
            String tempString = "";
            for (int i=0; i<s.length(); i++){
                if (s.charAt(i+1) == '/') {
                    processedArr[1] = s.substring(i+5);
                    break;
                }
                tempString += s.charAt(i);
            }
            processedArr[0] = tempString;
            return processedArr;
        } else {
            String[] processedArr = new String[3];
            String tempString = "";
            for (int i=0; i<s.length(); i++){
                if (s.charAt(i) == '/') {
                    String[] processedDate = stringProcessorForDatedTasks(true, s.substring(i+6));
                    processedArr[1] = processedDate[0];
                    processedArr[2] = processedDate[1];
                    break;
                }
                tempString += s.charAt(i);
            }
            processedArr[0] = tempString;
            return processedArr;
        }
    }

    /**
     * Output the TaskList..
     */
    public static String list(){
        return taskList.toString();
    }

    /**
     * Process the inputs from saved file if it exists.
     *
     * @param dataArr String[] the array of Strings from file to be processed.
     */
    public static void fileInpProcessor(String[] dataArr){
        try {
            Parser.operationHandler(dataArr[0]);
            if (dataArr[1].equals("1")) {
                Parser.operationHandler("mark " + Integer.toString(taskList.size()));
            }
        } catch (DukeException e){
            System.out.println(e.toString());
        }
    }

    /**
     * Saves the current list to a file.
     */
    public static void saveFile(String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (int i=0; i<taskList.size(); i++) {
                Task tempTask = taskList.get(i);
                myWriter.write(tempTask.toStringForFile());
            }
            myWriter.close();
            System.out.println("Saved your list :).");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
