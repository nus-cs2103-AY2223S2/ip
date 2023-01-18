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
                    continue;
                }
                this.markInComplete(taskIndex);
            } else {
                System.out.println("\tadded: " + userInput);
                this.list.add(new Task(userInput));
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


    public static void main(String[] args) {
        Duke dukeObj = new Duke();

        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        dukeObj.inputResponse();
    }
}
