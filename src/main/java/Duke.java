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
                            .append("[")
                            .append(userTasks[i].getStatusIcon())
                            .append("] ")
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
                        + " [X] " + userTasks[numToMark-1] + "\n"
                        + "______________________________________\n";

            } else if((userInput.split(" ")[0]).equals("unmark")){
                int numToUnmark = Integer.parseInt(userInput.split(" ")[1]);
                userTasks[numToUnmark-1].setIsDone(false);
                resultString = "______________________________________\n"
                        + "OK, I've marked this task as not done yet:\n"
                        + " [ ] " + userTasks[numToUnmark-1] + "\n"
                        + "______________________________________\n";
            }
            else {
                Task newTask = new Task(userInput);
                userTasks[Task.getNumTasks()] = newTask;
                Task.addTask();
                resultString = "______________________________________\n"
                        + "added: "
                        + userInput + "\n"
                        + "______________________________________\n";
            }
            System.out.print(resultString);
        }
    }
}
