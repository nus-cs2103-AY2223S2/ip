import java.util.*;
public class Duke {
    private Scanner sc;

    public Duke(Scanner sc) {
        this.sc = sc;
    }
    public void start(ArrayList<Task> list) {
        while(sc.hasNext()) {
            String str = sc.nextLine();
            if(str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                return;
            } else if(str.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    System.out.println( index + "." + list.get(i).toString());
                }
            } else if(str.contains("unmark")) {
                String[] splitString = str.split(" ");
                int inputIndex = Integer.parseInt(splitString[1]) - 1;
                list.get(inputIndex).unmark();
                System.out.println( "OK, I've marked this task as not done yet:\n"
                        + list.get(inputIndex).toString());
            } else if(str.contains("mark")) {
                String[] splitString = str.split(" ");
                int inputIndex = Integer.parseInt(splitString[1]) - 1;
                list.get(inputIndex).markDone();
                System.out.println( "Nice! I've marked this task as done:\n"
                        + list.get(inputIndex).toString());
            } else if(str.contains("todo")) {
                Todo t = new Todo(str);
                list.add(t);
                System.out.println( "Got it. I've added this task:\n" + t.toString()
                + "\nNow you have " + list.size() + " tasks in the list");
            } else if(str.contains("deadline")){
                int  detailIndex= str.lastIndexOf("deadline");
                String taskFullDetails = str.substring(detailIndex);
                String[] splitDetails = taskFullDetails.split("/");
                Deadline t = new Deadline(splitDetails[0], splitDetails[1]);
                list.add(t);
                System.out.println( "Got it. I've added this task:\n" + t.toString()
                        + "\nNow you have " + list.size() + " tasks in the list");
            } else if(str.contains("event")) {
                int detailIndex = str.lastIndexOf("event");
                String taskFullDetails = str.substring(detailIndex);
                String[] splitDetails = taskFullDetails.split("/");
                Event t = new Event(splitDetails[0], splitDetails[1], splitDetails[2]);
                list.add(t);
                System.out.println("Got it. I've added this task:\n" + t.toString()
                        +"\nNow you have " + list.size() + " tasks in the list");
            } else {
                System.out.println("Cannot!");
            }
        }
    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke What can I do for you?");

        ArrayList<Task> list = new ArrayList<Task>();
        Scanner sc= new Scanner(System.in);
        Duke duke = new Duke(sc);
        duke.start(list);

    }
}
