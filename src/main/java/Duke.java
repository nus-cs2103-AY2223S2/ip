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
                    System.out.println(  index + ".[" + list.get(i).getStatusICon() + "] " + list.get(i).getName());
                }
            } else if(str.contains("unmark")) {
                String[] splitString = str.split(" ");
                int inputIndex = Integer.parseInt(splitString[1]) - 1;
                list.get(inputIndex).unmark();
                System.out.println( "OK, I've marked this task as not done yet:\n"
                        + "[" + list.get(inputIndex).getStatusICon() + "] " + list.get(inputIndex).getName());
            } else if(str.contains("mark")) {
                String[] splitString = str.split(" ");
                int inputIndex = Integer.parseInt(splitString[1]) - 1;
                list.get(inputIndex).markDone();
                System.out.println( "Nice! I've marked this task as done:\n"
                        + "[" + list.get(inputIndex).getStatusICon() + "] " + list.get(inputIndex).getName());
            } else {
                Task t = new Task(str, false);
                list.add(t);
                System.out.println( "added:" + str);
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
