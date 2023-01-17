import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] userTasks = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        boolean continueRunning = true;
        String greeting = "______________________________________\n"
                + "Hey there buddy! I'm Duke\n"
                + "What can I do for you today?\n"
                + "______________________________________\n";
        System.out.print(greeting);
        Scanner scanner = new Scanner(System.in);

        while(continueRunning){
            String userInput = scanner.nextLine();
            String resultString = "";
            if (userInput.equals("bye")){
                resultString = "______________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "______________________________________\n";
                continueRunning = false;
            } else if(userInput.equals("list")){
                StringBuilder listOfInputs = new StringBuilder();
                for(int i = 0; i < Task.getNumTasks(); i++){
                    listOfInputs.append(i + 1)
                            .append(".")
                            .append(userTasks[i]).append("\n");
                }
                resultString = "______________________________________\n"
                        + "Here are the tasks in your list:\n"
                        + listOfInputs
                        + "______________________________________\n";
            } else if((userInput.split(" ")[0]).equals("mark")){
                int numToMark = Integer.parseInt(userInput.split(" ")[1]);
                userTasks[numToMark-1].setIsDone(true);
                resultString = "______________________________________\n"
                        + "Nice! I've marked this task as done:\n"
                        + userTasks[numToMark-1] + "\n"
                        + "______________________________________\n";

            } else if((userInput.split(" ")[0]).equals("unmark")){
                int numToUnmark = Integer.parseInt(userInput.split(" ")[1]);
                userTasks[numToUnmark-1].setIsDone(false);
                resultString = "______________________________________\n"
                        + "OK, I've marked this task as not done yet:\n"
                        + userTasks[numToUnmark-1] + "\n"
                        + "______________________________________\n";
            }
            else if(userInput.split(" ")[0].equals("todo")){
                int indexOfFirstSpace = userInput.indexOf(" ");
                String taskDescription = userInput.substring(indexOfFirstSpace+1);
                Todo newTodo = new Todo(taskDescription);
                userTasks[Task.getNumTasks()] = newTodo;
                Task.addTask();
                resultString = "______________________________________\n"
                        + "Got it. I've added this task:\n"
                        + newTodo + "\n"
                        + "Now you have " + Task.getNumTasks() + " tasks in the list.\n"
                        + "______________________________________\n";
            }
            else if(userInput.split(" ")[0].equals("deadline")){
                int indexOfBy = userInput.indexOf("/by");
                int indexOfFirstSpace = userInput.indexOf(" ");
                String deadline = userInput.substring(indexOfBy+4);
                String taskDescription = userInput.substring(indexOfFirstSpace+1,indexOfBy-1);
                Deadline newDeadline = new Deadline(taskDescription , deadline);
                userTasks[Task.getNumTasks()] = newDeadline;
                Task.addTask();
                resultString = "______________________________________\n"
                        + "Got it. I've added this task:\n"
                        +  newDeadline + "\n"
                        + "Now you have " + Task.getNumTasks() + " tasks in the list.\n"
                        + "______________________________________\n";
            }
            else if(userInput.split(" ")[0].equals("event")){
                int indexOfFrom = userInput.indexOf("/from");
                int indexOfTo = userInput.indexOf("/to");
                int indexOfFirstSpace = userInput.indexOf(" ");
                String eventStart = userInput.substring(indexOfFrom+6, indexOfTo-1);
                String eventEnd = userInput.substring(indexOfTo+4);
                String taskDescription = userInput.substring(indexOfFirstSpace+1,indexOfFrom-1);
                Event newEvent = new Event(taskDescription, eventStart, eventEnd);
                userTasks[Task.getNumTasks()] = newEvent;
                Task.addTask();
                resultString = "______________________________________\n"
                        + "Got it. I've added this task:\n"
                        + newEvent + "\n"
                        + "Now you have " + Task.getNumTasks() + " tasks in the list.\n"
                        + "______________________________________\n";
            }
            System.out.print(resultString);
        }
    }
}
