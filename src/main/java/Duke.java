import java.util.*;
public class Duke {
    private Scanner sc;

    public Duke(Scanner sc) {
        this.sc = sc;
    }
    public void start(Tasklist list) throws DukeException {
        while(sc.hasNext()) {
            String input = sc.nextLine();
            input = input.trim();
            String[] separateInput = input.split(" ");
            String str = separateInput[0];
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                return;
            } else if(input.equals("list")) {
                list.inString();
            } else if(input.contains("unmark")) {
                String[] splitString = input.split(" ");
                int index = Integer.parseInt(splitString[1]) - 1;
                list.unmarkTask(index);
            } else if(input.contains("mark")) {
                String[] splitString = input.split(" ");
                int index = Integer.parseInt(splitString[1]) - 1;
                list.markTaskAsDone(index);
            } else if(input.contains("delete")) {
                String[] splitString = input.split(" ");
                int index = Integer.parseInt(splitString[1]) - 1;
                list.deleteTask(index);

            } else if(str.equals("todo")) {
                int startIndex = str.length();
                if(startIndex >= input.length()) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String taskFullDetails = input.substring(startIndex);
                Todo task = new Todo(taskFullDetails);
                list.add(task);
                list.totalNumberOfTasks();
            } else if(str.equals("deadline")){
                int startIndex = str.length();
                if(startIndex >= input.length()) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                int detailIndex= input.lastIndexOf("deadline");
                String taskFullDetails = input.substring(detailIndex);
                String[] splitDetails = taskFullDetails.split("/");
                if(splitDetails.length < 2) {
                    throw new DukeException("☹ OOPS!!! The deadline must be specified.");
                }
                Deadline task = new Deadline(splitDetails[0], splitDetails[1]);
                list.add(task);
                list.totalNumberOfTasks();
            } else if(str.equals("event")) {
                int startIndex = str.length();
                if(startIndex >= input.length()) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                int detailIndex = input.lastIndexOf("event");
                String taskFullDetails = input.substring(detailIndex);
                String[] splitDetails = taskFullDetails.split("/");
                if(splitDetails.length < 3) {
                    throw new DukeException("☹ OOPS!!! The start and end both must be specified.");
                }
                Event task = new Event(splitDetails[0], splitDetails[1], splitDetails[2]);
                list.add(task);
                list.totalNumberOfTasks();
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry,try again later!");
            }
        }
    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke What can I do for you?");

        Tasklist list = new Tasklist();
        Scanner sc= new Scanner(System.in);
        Duke duke = new Duke(sc);
        try {
            duke.start(list);
        } catch(DukeException e) {
            System.out.println(e);
        }
    }
}
