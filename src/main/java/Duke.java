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
            if(str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                return;
            } else if(str.equals("list")) {
                list.inString();
            } else if(str.contains("unmark")) {
                String[] splitString = str.split(" ");
                int index = Integer.parseInt(splitString[1]) - 1;
                list.markTaskasDone(index);
            } else if(str.contains("mark")) {
                String[] splitString = str.split(" ");
                int index = Integer.parseInt(splitString[1]) - 1;
                list.markTaskasDone(index);
            } else if(str.contains("todo")) {
                int startIndex = str.length();
                if(startIndex >= str.length()) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Todo task = new Todo(str);
                list.add(task);
                list.totalNumberOfTasks();
            } else if(str.contains("deadline")){
                int startIndex = str.length();
                if(startIndex >= str.length()) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                int detailIndex= str.lastIndexOf("deadline");
                String taskFullDetails = str.substring(detailIndex);
                String[] splitDetails = taskFullDetails.split("/");
                if(splitDetails.length < 2) {
                    throw new DukeException("☹ OOPS!!! The deadline must be specified.");
                }
                Deadline task = new Deadline(splitDetails[0], splitDetails[1]);
                list.add(task);
                list.totalNumberOfTasks();
            } else if(str.contains("event")) {
                int startIndex = str.length();
                if(startIndex >= str.length()) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                int detailIndex = str.lastIndexOf("event");
                String taskFullDetails = str.substring(detailIndex);
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
