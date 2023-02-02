import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    // deals with making sense of the user command
    private Scanner input;
    private String currentInput;


    public ArrayList<Task> makeSense(ArrayList<Task> myList) {
        String exit = "bye";
        String outputL = "list";
        String mark = "mark";
        String unmark = "unmark";
        String to = "todo";
        String dead = "deadline";
        String event = "event";
        String delete = "delete";
        input = new Scanner(System.in);
        while (true) {
            currentInput = input.nextLine();
            if (exit.equals(currentInput)) {
                return myList;
            } else if (outputL.equals(currentInput)) {
                int index = 1;
                for (Task s : myList) {
                    System.out.println((index++) + ": " + s.toString());
                }
            } else if (mark.equals(currentInput.split("\\s+")[0])) {
                Task t = myList.get(Integer.valueOf(currentInput.split("\\s+")[1]) - 1);
                t.markAsDone();
                t.markAsDoneMessage();
            } else if (unmark.equals(currentInput.split("\\s+")[0])) {
                Task t = myList.get(Integer.valueOf(currentInput.split("\\s+")[1]) - 1);
                t.markAsNotDone();
                t.markAsNotDoneMessage();
            } else if (delete.equals(currentInput.split("\\s+")[0])) {
                myList.remove(Integer.valueOf(currentInput.split(" ", 2)[1]) - 1);
                System.out.println("Done. I've deleted the task");
            } else {
                if (to.equals(currentInput.split("\\s+", 2)[0])) {
                    Task todo = new Todo(currentInput.split(" ", 2)[1]);
                    myList.add(todo);
                    System.out.println("Done. I've added the task: " + todo.toString());
                } else if (dead.equals(currentInput.split("\\s+", 2)[0])) {
                    Task deadline = new Deadline(currentInput.split(" ", 2)[1].split(" /by ")[0], currentInput.split(" ", 2)[1].split(" /by ")[1]);
                    myList.add(deadline);
                    System.out.println("Done. I've added the deadline: " + deadline.toString());
                } else if (event.equals(currentInput.split("\\s+", 2)[0])) {
                    Task even = new Event(currentInput.split(" ", 2)[1].split(" /from ", 2)[0], currentInput.split(" ", 2)[1].split(" /from ", 2)[1].split(" /to ", 2)[0], currentInput.split(" ", 2)[1].split(" /from ", 2)[1].split(" /to ", 2)[1]);
                    myList.add(even);
                    System.out.println("Done. I've added the Event: " + even.toString());
                }
            }
        }
    }

}
