import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();


    private void inputResponse(){

        while(true) {
            String userInput;
            userInput = this.sc.nextLine();
            if(userInput.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else if(userInput.equals("list")) {
                this.displayList();
            } else if(userInput.substring(0, 5).equals("mark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(5));
                if(list.get(taskIndex - 1).getStatus()) {
                    System.out.println("\tTask already marked!");
                    continue;
                }
                this.markComplete(taskIndex);
            } else if(userInput.substring(0, 7).equals("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(7));
                if(list.get(taskIndex - 1).getStatus() == false) {
                    System.out.println("\tTask already unmarked!");
                    continue;                }
                this.markInComplete(taskIndex);
            } else if(userInput.substring(0, 5).equals("todo ")) {
                String textInput = userInput.substring(5);
                this.addTodo(textInput);
            } else if(userInput.substring(0, 9).equals("deadline ")) {
                String textInput = userInput.substring(9);
                this.addDeadline(textInput);
            } else if(userInput.substring(0, 6).equals("event ")) {
                String textInput = userInput.substring(6);
                this.addEvent(textInput);
            }


        }
    }

    private void displayList() {
        int listSize = this.list.size();
        for(int i = 1; i <= listSize; i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1).toString());
        }
    }

    private void markComplete(int taskIndex) {
        Task taskToBeMarked = this.list.get(taskIndex - 1);
        taskToBeMarked.changeStatus();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" +  taskToBeMarked.toString());
    }

    private void markInComplete(int taskIndex) {
        Task taskToBeUnmarked = this.list.get(taskIndex - 1);
        taskToBeUnmarked.changeStatus();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" +  taskToBeUnmarked.toString());
    }

    private void addTodo(String userInput) {
        Todo todo = new Todo(userInput);
        this.list.add(todo);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + todo.toString());
        displayTasks();
    }
    private void addDeadline(String userInput) {
        int indexDeadline = userInput.indexOf("/by");
        String deadlineText = userInput.substring(0, indexDeadline - 1);
        String deadlineDate = userInput.substring(indexDeadline + 4);
        userInput = String.format("%s (by: %s)", deadlineText, deadlineDate);
        Deadlines deadline = new Deadlines(userInput);
        this.list.add(deadline);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + deadline.toString());
        displayTasks();
    }

    private void addEvent(String userInput) {
        int indexFrom = userInput.indexOf("/from");
        int indexTo = userInput.indexOf("/to");
        String eventText = userInput.substring(0, indexFrom - 1);
        String eventTo= userInput.substring(indexTo + 4);
        String eventFrom = userInput.substring(indexFrom + 6, indexTo - 1);

        userInput = String.format("%s (from: %s to: %s)", eventText, eventFrom, eventTo);
        Event event = new Event(userInput);
        this.list.add(event);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + event.toString());
        displayTasks();
    }

    private void displayTasks() {
        int listSize = list.size();
        System.out.println(String.format("\tNow you have %d tasks in the list.", listSize));
    }
    
    public static void main(String[] args) {
        Duke dukeObj = new Duke();

        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        dukeObj.inputResponse();
    }
}
