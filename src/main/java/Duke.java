import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello i'm\n" + logo);
        System.out.println("What can i do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        ArrayList<Task> list = new ArrayList<Task>(); 
        int counter = 0;

        while (!input.equals("bye")){
            try{
                if (input.equals("todo")){
                    input = scan.nextLine();
                    if (input.isEmpty() || input.isBlank()) { 
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty");
                    }
                    
                    Todo t = new Todo(input); 
                    list.add(t);
                    counter = counter + 1;
                    System.out.print("Got it. I've added this task:\n" + t.toString() + "\n" + "Now you have " + counter + " tasks in the list.\n");
                    input = scan.next();
                    continue;
                }
                
                //deadline return book /by Sunday
                else if (input.equals("deadline")){
                    input = scan.nextLine();
                    String[] arrOfStr = input.split("/by ");

                    // input1 is the task
                    String input1 = arrOfStr[0];
                    // input2 is the deadline
                    String input2 = arrOfStr[1];
                    Deadline d = new Deadline(input1, input2); 
                    list.add(d);
                    counter = counter + 1;
                    System.out.print("Got it. I've added this task:\n" + d.toString() + "\n" + "Now you have " + counter + " tasks in the list.\n");
                    input = scan.next();
                    continue;
                }

                //event project meeting /from Mon 2pm /to 4pm
                else if (input.equals("event")){
                    input = scan.nextLine();
                    String[] arrOfStr = input.split("/from|/to");

                    // input1 is the task
                    String input1 = arrOfStr[0];
                    // input2 is the start
                    String input2 = arrOfStr[1];
                    // input3 is the end
                    String input3 = arrOfStr[2];

                    Event e = new Event(input1, input2, input3); 
                    list.add(e);
                    counter = counter + 1;
                    System.out.print("Got it. I've added this task:\n" + e.toString() + "\n" + "Now you have " + counter + " tasks in the list.\n");
                    input = scan.next();
                    continue;
                }

                else if (input.equals("mark")){
                    int index = scan.nextInt();
                    Task t = list.get(index - 1);
                    t.markAsDone();
                    System.out.println( "Nice! I've marked this task as done:\n" + t.toString());
                    input = scan.next();
                    continue;
                }

                else if (input.equals("unmark")){
                    int index = scan.nextInt();
                    Task t = list.get(index - 1);
                    t.markAsNotDone();
                    System.out.println( "OK, I've marked this task as not done yet:\n" + t.toString());
                    input = scan.next();
                    continue;
                }

                else if (input.equals("delete")) {
                    int index = scan.nextInt();
                    Task t = list.get(index - 1);
                    list.remove(index - 1);
                    counter = counter - 1;
                    System.out.print("Noted. I've removed this task:\n" + t.toString() + "\n" + "Now you have " + counter + " tasks in the list.\n");
                    input = scan.next();
                    continue;
                }

                else if (input.equals("list")){
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + "." + list.get(i).toString());
                    }
                    input = scan.next();
                    continue;
                } 

                else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                
    
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            input = scan.next();
          
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
