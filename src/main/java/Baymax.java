import java.util.*;
public class Baymax {
    public static void main(String[] args) {
        System.out.println("Hello, I am Baymax your personal Chatbot Companion. \nWhat can I do for you today?");
        makeDecision();
        System.out.println("See you soon!");
    }

    public static void makeDecision() {
        String exit = "bye";
        String outputL = "list";
        String mark = "mark";
        String unmark = "unmark";

        Scanner input = new Scanner(System.in);
        String currentInput;
        List<Task> myList = new ArrayList<>();
        int Counter = 0;
        while (true) {
            currentInput = input.nextLine();
            if (exit.equals(currentInput)) {
                break;
            } else if (outputL.equals(currentInput)) {
                int index = 1;
                for (Task s : myList) {
                    System.out.println((index++) + ": " + s.description + "[" + s.getStatusIcon() + "]");
                }
            } else if (mark.equals(currentInput.split("\\s+")[0])) {
                Task t = myList.get(Integer.valueOf(currentInput.split("\\s+")[1]) - 1);
                t.markAsDone();
            } else if (unmark.equals(currentInput.split("\\s+")[0])) {
                Task t = myList.get(Integer.valueOf(currentInput.split("\\s+")[1]) - 1);
                t.markAsNotDone();
            } else {
                Task task = new Task(currentInput);
                task.description = currentInput;
                task.isDone = false;
                myList.add(Counter, task);
                System.out.println("added: " + task.description);
                Counter++;
            }
        }
    }
}

