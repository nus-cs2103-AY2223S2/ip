package duke;

import java.util.ArrayList;

public class Parser {


    public static void parseInput(String[] splitInput) {
        ArrayList<Task> array = TaskList.getList();
        String combined = String.join(" ", splitInput);
        if(combined.equals("bye")) {
            Ui.bye();
        }
        //Displays the list of items 
        else if (combined.equals("list")){
            Ui.displayList(array);
            
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
                System.out.println(e.getMessage());
            }
        }
    }
    
}
