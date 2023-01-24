// import libraries here

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Acerizm {
    public static void main(String[] args) {
        String personal_logo = "                      - \n"
                             + "    /                (_) \n"
                             + "   /  \\   ___ ___ _ __ _ _____ __ ___ \n"
                             + "  / /  \\ / __/ _ \\ '__| |_  / '_ ` _ \\ \n"
                             + " / ____ \\ (_|  __/ |  | |/ /| | | | | | \n"
                             + "/_/    \\_\\___\\___|_|  |_/___|_| |_| |_| \n";

                System.out.println("Hi there! I am \n" + personal_logo);
                chat();
    }

    // Chat method is for level 1 of chatbot
    // Level 3: Refactor code to manage tasks using Task class
    public static void chat(){

        // level 2: added a temporary list to store items
        List<Task> taskList = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("*-".repeat(100));
        System.out.println("What can I do for you?");
        System.out.println("*-".repeat(100));
        while(scanner.hasNextLine()){
            try {
                // need extract the input and check if there are commands of spaces
                String[] input = scanner.nextLine().split(" ");
                // first expected error
                TypeOfTask actionTaken = convertToAction(input[0]);
                if(actionTaken == TypeOfTask.bye) {
                    System.out.println("*-".repeat(100));
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("*-".repeat(100));
                    break;
                } else if(actionTaken == TypeOfTask.list){
                    // for listing of task
                    System.out.println("*-".repeat(100));
                    System.out.println("Here are the tasks in your list:");
                    for(int i=0; i < taskList.size();i++){
                        Task currentTask = taskList.get(i);
                        String description = currentTask.getDescription();
                        String isMarked = currentTask.getStatusIcon();
                        String currentStatus = currentTask.getTypeOfTask();
                        System.out.println(String.format("%d. %s",i+1,currentTask.toString()));
                    }
                    System.out.println("*-".repeat(100));
                } else if(actionTaken == TypeOfTask.mark){
                    // for marking tasks
                    System.out.println("*-".repeat(100));
                    int userMarkIndex = Integer.parseInt(input[1]) - 1;
                    Task currentTask = taskList.get(userMarkIndex);
                    currentTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currentTask.toString());
                    System.out.println("*-".repeat(100));
                } else if(actionTaken == TypeOfTask.unmark){
                    // for unmarking tasks
                    System.out.println("*-".repeat(100));
                    int userMarkIndex = Integer.parseInt(input[1]) - 1;
                    Task currentTask = taskList.get(userMarkIndex);
                    currentTask.unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currentTask.toString());
                    System.out.println("*-".repeat(100));
                } else if(actionTaken == TypeOfTask.todo){
                    // for todo tasks
                    System.out.println("*-".repeat(100));
                    String userInput = convertToUserInput(input,TypeOfTask.todo,"");
                    Task newTask = new Todo(userInput);
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.toString());
                    System.out.println(String.format("Now you have %d tasks in the list",taskList.size()));
                    System.out.println("*-".repeat(100));
                } else if(actionTaken == TypeOfTask.deadline){
                    // for deadline
                    System.out.println("*-".repeat(100));
                    String userInput = convertToUserInput(input,TypeOfTask.deadline,"");
                    // added additional variable to store the date of the deadline
                    String day = convertToUserInput(input,TypeOfTask.deadline,"/by");
                    Task newTask = new Deadline(userInput,day);
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.toString());
                    System.out.println(String.format("Now you have %d tasks in the list",taskList.size()));
                    System.out.println("*-".repeat(100));
                } else if (actionTaken == TypeOfTask.event) {
                    // for event
                    System.out.println("*-".repeat(100));
                    String userInput = convertToUserInput(input,TypeOfTask.event,"");
                    // added additional variable to store the start and end time of event
                    String startTime = convertToUserInput(input,TypeOfTask.event,"/from");
                    String endTime = convertToUserInput(input,TypeOfTask.event,"/to");
                    Task newTask = new Event(userInput,startTime,endTime);
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.toString());
                    System.out.println(String.format("Now you have %d tasks in the list",taskList.size()));
                    System.out.println("*-".repeat(100));

                } else if(actionTaken == TypeOfTask.delete) {
                    int taskIndexDelete = Integer.parseInt(convertToUserInput(input,TypeOfTask.delete,"")) - 1;
                    Task taskToBeDeleted = taskList.get(taskIndexDelete);
                    String description = taskToBeDeleted.getDescription();
                    String isMarked = taskToBeDeleted.getStatusIcon();
                    String currentStatus = taskToBeDeleted.getTypeOfTask();
                    System.out.println("Noted! I've removed this task:");
                    System.out.println(String.format("%d. %s",taskIndexDelete+1,taskToBeDeleted.toString()));
                    taskList.remove(taskIndexDelete);
                    System.out.println(String.format("Now you have %d tasks in the list",taskList.size()));
                    System.out.println("*-".repeat(100));
                }else {
                    // if it doesn't match any action, do nothing
                    // print error
                    // this else clause will never be reached as we have handled the exceptions in DukeExceptions
                    System.out.println("Oh no! You forgot to type in something useful :p");
                    System.out.println("What can I do for you?");
                }

            } catch (DukeException e){
                System.out.println(e.getMessage());
                System.out.println("*-".repeat(100));
            } catch(Exception others){
                System.out.println(others.getMessage());
                System.out.println("*-".repeat(100));
            }
        }
    }

    /*
        This method converts the user input's and transform them into their respective outputs for each different type
        of task.
     */
    // limiter -> "/by", "/from", "/to"
    public static String convertToUserInput(String[] input, TypeOfTask action,String limiter ) throws DukeException{
        switch(action){
            case todo: {
                // changed the way the string is outputted from the array
                String userInput = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                if (userInput.equals("") || userInput == null)
                    throw new DukeException(TypeOfTask.todo, 0);
                else
                    return userInput;
            }
            case deadline: { // added braces to scope the variables below locally to each case
                // algo to detect deadline's input content
                String userInput = "";
                if(!limiter.equals("/by")){
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].equals("/by")) {
                            break;
                        } else {
                            userInput += input[i] + " ";
                        }
                    }
                } else {
                    // to get the day after "/by"
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].equals("/by")) {
                            for(int j = i + 1; j < input.length; j++){
                                userInput += input[j] + " ";
                            }
                            break;
                        }
                    }
                }
                if(userInput.equals("") || userInput == null)
                    throw new DukeException(TypeOfTask.deadline,0);
                else
                    return userInput;

            }
            case event: {
                String userInput = "";
                if(limiter.equals("/from")){
                    for(int i = 1; i < input.length; i++){
                        if(input[i].equals("/from")){
                            for(int j = i + 1; j < input.length; j++){
                                if(input[j].equals("/to")){
                                    break;
                                } else {
                                    userInput += input[j] + " ";
                                }
                            }
                            break;
                        }
                    }
                } else if (limiter.equals("/to")) {
                    for(int i = 1; i < input.length; i++){
                        if(input[i].equals("/to")){
                            for(int j = i + 1; j < input.length; j++){
                                userInput += input[j] + " ";
                            }
                            break;
                        }
                    }
                } else {
                    // to get the user's input before the "/from" limiter
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].equals("/from")) {
                            break;
                        } else {
                            userInput += input[i] + " ";
                        }
                    }
                }
                if(userInput.equals("") || userInput == null)
                    throw new DukeException(TypeOfTask.event,0);
                else
                    return userInput;
            }
            case delete: {
                if(input.length == 1)
                    throw new DukeException(TypeOfTask.delete,0);
                else if(input.length >= 2)
                    throw new DukeException(TypeOfTask.delete,1);
                else
                    return input[1];
            }
            default:
                throw new DukeException();
        }
    }

    /*
        convertToAction is a method that converts the beginning of the user's input into a TypeOfTask enum type
     */
    public static TypeOfTask convertToAction(String input) throws DukeException {
        switch(input){
            case "mark":
                return TypeOfTask.mark;
            case "unmark":
                return TypeOfTask.unmark;
            case "list":
                return TypeOfTask.list;
            case "bye":
            case "quit":
                return TypeOfTask.bye;
            case "todo":
                return TypeOfTask.todo;
            case "deadline":
                return TypeOfTask.deadline;
            case "event":
                return TypeOfTask.event;
            case "delete":
                return TypeOfTask.delete;
            default: throw new DukeException();
        }
    }

}
