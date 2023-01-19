import java.security.spec.ECField;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();


    private void inputResponse() {

        while (true) {
            String input;
            input = this.sc.nextLine();
            String[] userInput = input.split(" ", 2);
            try {
                if (userInput[0].equals("bye")) {
                    System.out.println("\tBye. Hope to see you again soon!");
                    break;
                } else if (userInput[0].equals("list")) {
                    this.displayList();
                } else if (userInput[0].equals("mark")) {
                    this.markComplete(userInput);
                } else if (userInput[0].equals("unmark")) {
                    this.markInComplete(userInput);
                } else if (userInput[0].equals("todo")) {
                    this.addTodo(userInput);
                } else if (userInput[0].equals("deadline")) {
                    this.addDeadline(userInput);
                } else if (userInput[0].equals("event")) {
                    this.addEvent(userInput);
                } else {
                    throw new DukeInvalidCommandException();
                }
            } catch (DukeException e) {
                System.out.printf("\t%s\n", e);
            }
            this.printLine();
        }

    }

    private void displayList() {
        int listSize = this.list.size();
        for(int i = 1; i <= listSize; i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1).toString());
        }
    }

    private void markComplete(String[] userInput) throws DukeInvalidArgumentsException, DukeMissingArgumentException, DukeTaskArgumentException{
        try {
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.size()) {
                throw new DukeTaskArgumentException();
            }
            if (list.get(taskIndex - 1).getStatus()) {
                throw new DukeTaskArgumentException();
            }

            Task taskToBeMarked = this.list.get(taskIndex - 1);
            taskToBeMarked.changeStatus();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + taskToBeMarked.toString());
        } catch (NumberFormatException e){
            throw new DukeInvalidArgumentsException();
        } catch (IndexOutOfBoundsException e) {
            String task = "mark";
            throw new DukeMissingArgumentException(task);
        }
    }

    private void markInComplete(String[] userInput) throws DukeMissingArgumentException, DukeInvalidArgumentsException, DukeTaskArgumentException{
        try {
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.size()) {
                throw new DukeTaskArgumentException();
            }
            if (list.get(taskIndex - 1).getStatus() == false) {
                throw new DukeTaskArgumentException();
            }

            Task taskToBeUnmarked = this.list.get(taskIndex - 1);
            taskToBeUnmarked.changeStatus();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t" + taskToBeUnmarked.toString());
        }  catch(IndexOutOfBoundsException e) {
            String task = "unmark";
            throw new DukeMissingArgumentException(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
    }

    private void addTodo(String[] userInput) throws DukeMissingArgumentException{
        try {
            Todo todo = new Todo(userInput[1]);
            this.list.add(todo);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + todo.toString());
            displayTasks();
        } catch (IndexOutOfBoundsException e) {
            String task = "todo";
            throw new DukeMissingArgumentException(task);
        }

    }
    private void addDeadline(String[] userInput) throws DukeMissingArgumentException {
        try {
            int indexDeadline = userInput[1].indexOf("/by");
            String deadlineText = userInput[1].substring(0, indexDeadline - 1);
            String deadlineDate = userInput[1].substring(indexDeadline + 4);
            String userInp = String.format("%s (by: %s)", deadlineText, deadlineDate);

            Deadlines deadline = new Deadlines(userInp);
            this.list.add(deadline);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + deadline.toString());
            displayTasks();
        } catch(IndexOutOfBoundsException e) {
            String task = "deadline";
            throw new DukeMissingArgumentException(task);
        }
    }

    private void addEvent(String[] userInput) throws DukeMissingArgumentException{
        try {
            int indexFrom = userInput[1].indexOf("/from");
            int indexTo = userInput[1].indexOf("/to");
            String eventText = userInput[1].substring(0, indexFrom - 1);
            String eventTo = userInput[1].substring(indexTo + 4);
            String eventFrom = userInput[1].substring(indexFrom + 6, indexTo - 1);

            String userInp = String.format("%s (from: %s to: %s)", eventText, eventFrom, eventTo);
            Event event = new Event(userInp);
            this.list.add(event);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + event.toString());
            displayTasks();
        }catch (IndexOutOfBoundsException e) {
            String task = "event";
            throw new DukeMissingArgumentException(task);
        }
    }

    private void displayTasks() {
        int listSize = list.size();
        System.out.println(String.format("\tNow you have %d tasks in the list.", listSize));
    }

    private void printLine() {
        System.out.println("------------------------------------------------------------------");
    }

    public static void main(String[] args)  {
        Duke dukeObj = new Duke();

        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        dukeObj.printLine();
        dukeObj.inputResponse();
    }
}
