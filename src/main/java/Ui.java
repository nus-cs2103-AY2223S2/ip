import java.util.List;
import java.util.Scanner;

/*
 * User Interface Class for handling user's interactions
 */
public class Ui {
    
    public Ui(){

    }

    public void chat() throws Exception {
        String personal_logo = "                      - \n"
                             + "    /                (_) \n"
                             + "   /  \\   ___ ___ _ __ _ _____ __ ___ \n"
                             + "  / /  \\ / __/ _ \\ '__| |_  / '_ ` _ \\ \n"
                             + " / ____ \\ (_|  __/ |  | |/ /| | | | | | \n"
                             + "/_/    \\_\\___\\___|_|  |_/___|_| |_| |_| \n";

        System.out.println("Hi there! I am \n" + personal_logo);
        List<Task> taskList = loadTasks();
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
                    // level-7 added saving at the end of the program
                    saveTasks(taskList);
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
                    String[] dateTime = convertToUserInput(input,TypeOfTask.deadline,"/by").split(" ");
                    Task newTask = new Deadline(userInput,dateTime[0],dateTime[1]);
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
        scanner.close();
    }
}
