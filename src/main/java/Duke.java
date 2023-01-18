import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        ArrayList<String> stringInputs = new ArrayList<String>();
        boolean[] taskStatus = new boolean[100];
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, I'm Nero and I am an automated chat bot" + "\n" + "What would you like to do?");
        while (true) {
            String[] input = sc.nextLine().split(" ");
            if (input[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!" + "\n");
                break;
            } else if (input[0].equals("list")) {
                System.out.println("Here are all your tasks: ");
                for (int i = 0; i < stringInputs.size(); i++) {
                    int index = i + 1;
                    if (taskStatus[i]) {
                        System.out.println(index + ". [X] " + stringInputs.get(i));
                    } else {
                        System.out.println(index + ". [ ] " + stringInputs.get(i));
                    };
                }
            } else if (input[0].equals("mark")) {
                int taskToMark = Integer.parseInt(input[1]) - 1;
                taskStatus[taskToMark] = true;
                System.out.println("Great job on completing this task! " + "\n" + "[X] " + stringInputs.get(taskToMark));
            } else if (input[0].equals("unmark")) {
                int taskToUnmark = Integer.parseInt(input[1]) - 1;
                taskStatus[taskToUnmark] = false;
                System.out.println("Remember to complete this task!! " + "\n" + "[ ] " + stringInputs.get(taskToUnmark));
            } else {
                String toAdd = "";
                for (int i = 0; i < input.length; i++) {
                    toAdd += input[i] + " ";
                }
                stringInputs.add(toAdd);
                System.out.println(toAdd + "\n");
            }
        }
    }
}
