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
                    String description = taskList.get(i).getDescription();
                    String isMarked = taskList.get(i).getStatusIcon();
                    System.out.println(String.format("%d.[%s] %s",i+1,isMarked,description));
                }
                System.out.println("*-".repeat(100));
            } else if(input[0].equals(actions.mark.toString())){
                // for marking tasks
                System.out.println("*-".repeat(100));
                int userMarkIndex = Integer.parseInt(input[1]) - 1;
                String userInput = taskList.get(userMarkIndex).getDescription();
                taskList.get(userMarkIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format(" [%s] %s",taskList.get(userMarkIndex).getStatusIcon(),userInput));
                System.out.println("*-".repeat(100));
            } else if(input[0].equals(actions.unmark.toString())){
                // for unmarking tasks
                System.out.println("*-".repeat(100));
                int userMarkIndex = Integer.parseInt(input[1]) - 1;
                String userInput = taskList.get(userMarkIndex).getDescription();
                taskList.get(userMarkIndex).unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format(" [%s] %s",taskList.get(userMarkIndex).getStatusIcon(),userInput));
                System.out.println("*-".repeat(100));
            }
            else {
                // if it doesn't match any action, add the new task to the list
                System.out.println("*-".repeat(100));
                String userInput = Arrays.toString(input);
                Task newTask = new Task(userInput);
                System.out.println(String.format("added: %s",newTask.getDescription()));
                taskList.add(newTask);
                System.out.println("*-".repeat(100));
            }
        }
    }

    public static String convertToUserInput(String[] input){
        return Arrays.toString(Arrays.copyOfRange(input,1,input.length - 1));
    }
}
