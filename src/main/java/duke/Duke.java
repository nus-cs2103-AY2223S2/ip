package duke;
import java.util.Scanner;


import java.util.ArrayList;



public class Duke {
    public static void main(String[] args) {
     
        //print welcome message, ask for user input
        System.out.println("  ───── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ───── \n Hello! I'm Broccoli the dinosaur \n           <|°▿▿▿▿°|/ \n      What can I do for you? \n   ──── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────" ); 
        //getInput();
        ArrayList<Task> array=new ArrayList<Task>();
        String divider = "    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══";

        while (true) {
            String[] splitInput = getInput();
            String combined = String.join(" ", splitInput);
            if(combined.equals("bye")) {
                System.out.println("    ──────── ⋅ ∙ ∘ ☽ ༓ ☾ ∘ ⋅ ⋅ ─────────");
                System.out.println( "      Bye. Hope to see you again soon!");
                System.out.println("\n   ‧˚₊꒷꒦︶︶︶︶︶꒷꒦︶︶︶︶︶꒦꒷‧₊˚⊹");
                break;
            }
            //Displays the list of items 
            else if (combined.equals("list")){
                Task.displayList(array);
                
            } 
            //Mark the task as done
            else if(splitInput[0].equals("mark")){
                Task.markTask(array, splitInput);
            } 
            //Unmark the task as done
            else if(splitInput[0].equals("unmark")){
                Task.unmarkTask(array, splitInput);
            }
            //Creates a Deadline type Task 
            else if(splitInput[0].equals("deadline")){
                Deadline.createDeadlineTask(array, splitInput);
            } 
            //Creates an Event type Task
            else if(splitInput[0].equals("event")) {
                Event.createEventTask(array, splitInput);
            } 
            //Creates a Todo type Task
            else if(splitInput[0].equals("todo")) {
                Todo.createTodoTask(array, splitInput);
            }
            //Deletes a Task 
            else if(splitInput[0].equals("delete")) {
                Task.deleteTask(array, splitInput);
            } else {
                try {
                    throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (Exception e) {
                    System.out.println(divider);
                    System.out.println(e.getMessage());
                    System.out.println(divider);
                }
            }
        }
        

    }

    //get user input
    private static String[] getInput(){
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput.split("\\s+");
    }

    
}
