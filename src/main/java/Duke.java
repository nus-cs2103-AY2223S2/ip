import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] inputs = new Task[100];
        int curr = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        String currInput = scanner.nextLine();
        String[] splitStr = currInput.split(" ");

        while(!currInput.equals("bye")) {
            if(currInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < curr; i++) {
                    System.out.println((i + 1) + ". " + inputs[i]);
                }
            } else if(splitStr[0].equals("mark")) {
                Task taskToMark = inputs[Integer.parseInt(splitStr[1]) - 1];
                taskToMark.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskToMark + "\n");
            } else if(splitStr[0].equals("unmark")) {
                Task taskToUnmark = inputs[Integer.parseInt(splitStr[1]) - 1];
                taskToUnmark.unmarkDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskToUnmark + "\n");
            } else {
                System.out.println("added: " + currInput + "\n");
                inputs[curr++] = new Task(currInput);
            }
            currInput = scanner.nextLine();
            splitStr = currInput.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
