import java.util.Scanner;
public class Duke {
    static String[] tasks = new String[100];
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

            if(newInput.equalsIgnoreCase("bye")){
                break;
            } else if(newInput.equalsIgnoreCase("list")){
                showList();
            }else {
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

    public static void addTask(String task){
        System.out.println(makeOutput("added: " + task));
        tasks[numOfTasks] = task;
        numOfTasks ++;
    }

    public static void showList(){
        String result = "";
        for(int i = 0; i < numOfTasks - 1; i ++){
            result += Integer.toString(i + 1) + ". " + tasks[i] + "\n";
        }
        result += Integer.toString(numOfTasks) + ". " + tasks[numOfTasks - 1];
        System.out.println(makeOutput(result));
    }
}
