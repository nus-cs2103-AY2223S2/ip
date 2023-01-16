import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static int numOfTasks = 0;
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        //open scanner to accept user input
        Scanner userInput = new Scanner(System.in);
        //greet user
        System.out.println(makeOutput(" Hello! I'm Duke\n" + " What can I do for you?"));

        while(true){
            String newInput = userInput.nextLine();
            String[] parsedInput = newInput.split(" ");
            String command = parsedInput[0];

            if(command.equalsIgnoreCase("bye")){
                break;
            } else if(command.equalsIgnoreCase("list")){
                showList();
            } else if(command.equalsIgnoreCase("mark")){
                markTask(Integer.parseInt(parsedInput[1]) - 1);
            } else if(command.equalsIgnoreCase("unmark")){
                unmarkTask(Integer.parseInt(parsedInput[1]) - 1);
            } else {
                addTask(newInput);
            }

        }

        //exit protocol
        System.out.println(makeOutput("Bye. Hope to see you again soon!"));
        userInput.close();
    }
    public static String makeOutput(String in){
        return "____________________________________________________________\n" + in +"\n" + "____________________________________________________________";
    }

    public static void addTask(String description){
        System.out.println(makeOutput("added: " + description));
        tasks[numOfTasks] = new Task(description);
        numOfTasks ++;
    }

    public static void showList(){
        String result = "Here are the tasks in your list:\n";
        for(int i = 0; i < numOfTasks - 1; i ++){
            result += Integer.toString(i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i] + "\n";
        }
        result += Integer.toString(numOfTasks) + "." + tasks[numOfTasks - 1].getStatusIcon() + " " + tasks[numOfTasks - 1];
        System.out.println(makeOutput(result));
    }

    public static void markTask(int taskNo){
        tasks[taskNo].mark();
        String result = "Nice! I've marked this task as done:\n";
        result += tasks[taskNo].getStatusIcon() + " " + tasks[taskNo];
        System.out.println(makeOutput(result));
    }

    public static void unmarkTask(int taskNo){
        tasks[taskNo].unmark();
        String result = "OK, I've marked this task as not done yet:\n";
        result += tasks[taskNo].getStatusIcon() + " " + tasks[taskNo];
        System.out.println(makeOutput(result));
    }
}
