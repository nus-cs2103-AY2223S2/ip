// import libraries here

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Acerizm {
    public enum actions {
        mark,
        unmark,
        list,
        bye,
        todo,
        deadline,
        event
    }
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
            // need extract the input and check if there are commands of spaces
            String[] input = scanner.nextLine().split(" ");
            //String[] checkInput = input.split(" ");
            // for bye
            if(input[0].equals(actions.bye.toString())) {
                System.out.println("*-".repeat(100));
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("*-".repeat(100));
                break;
            } else if(input[0].equals(actions.list.toString())){
                // for listing of task
                System.out.println("*-".repeat(100));
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i < taskList.size();i++){
                    Task currentTask = taskList.get(i);
                    String description = currentTask.getDescription();
                    String isMarked = currentTask.getStatusIcon();
                    String currentStatus = currentTask.getTypeOfTask();
                    System.out.println(String.format("%d.[%s][%s] %s",i+1,currentStatus,isMarked,description));
                }
                System.out.println("*-".repeat(100));
            } else if(input[0].equals(actions.mark.toString())){
                // for marking tasks
                System.out.println("*-".repeat(100));
                int userMarkIndex = Integer.parseInt(input[1]) - 1;
                String userInput = taskList.get(userMarkIndex).getDescription();
                Task currentTask = taskList.get(userMarkIndex);
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format(" [%s][%s] %s",currentTask.getTypeOfTask(),currentTask.getStatusIcon(),userInput));
                System.out.println("*-".repeat(100));
            } else if(input[0].equals(actions.unmark.toString())){
                // for unmarking tasks
                System.out.println("*-".repeat(100));
                int userMarkIndex = Integer.parseInt(input[1]) - 1;
                String userInput = taskList.get(userMarkIndex).getDescription();
                Task currentTask = taskList.get(userMarkIndex);
                currentTask.unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format(" [%s][%s] %s",currentTask.getTypeOfTask(),currentTask.getStatusIcon(),userInput));
                System.out.println("*-".repeat(100));
            } else if(input[0].equals(actions.todo.toString())){
                System.out.println("*-".repeat(100));
                String userInput = convertToUserInput(input);
                Task newTask = new Task(userInput,TypeOfTask.todo);
                taskList.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("[%s][%s] %s",newTask.getTypeOfTask(),newTask.getStatusIcon(),newTask.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list",taskList.size()));
                System.out.println("*-".repeat(100));
            } else if(input[0].equals(actions.deadline.toString())){
                System.out.println("*-".repeat(100));
                String userInput = convertToUserInput(input);
                Task newTask = new Task(userInput,TypeOfTask.deadline);
                taskList.add(newTask);
                // added additional variable to store the date of the deadline
                String day
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("[%s][%s] %s",newTask.getTypeOfTask(),newTask.getStatusIcon(),newTask.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list",taskList.size()));
                System.out.println("*-".repeat(100));
            }
            else {
                // if it doesn't match any action, add the new task to the list

            }
        }
    }

    public static String convertToUserInput(String[] input){
        // changed the way the string is outputted from the array
        return String.join(" ",Arrays.copyOfRange(input,1,input.length));
    }
}
