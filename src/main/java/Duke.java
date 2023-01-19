import java.util.*;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke What can I do for you?");
        Scanner sc= new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        boolean exit = false;
        while(!exit) {
            String str = sc.nextLine();
            exit = isOffline(str,list);
        }

    }
    public static boolean isOffline(String str, ArrayList<Task> list) {
        if(str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        } else if(str.equals("list")) {
            System.out.println("Here are the tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                System.out.println(  index + ".[" + list.get(i).getStatusICon() + "] " + list.get(i).getName());
            }
            return false;
        } else if(str.contains("unmark")) {
            String[] splitString = str.split(" ");
            int inputIndex = Integer.parseInt(splitString[1]) - 1;
            list.get(inputIndex).unmark();
            System.out.println( "OK, I've marked this task as not done yet:\n"
                    + "[" + list.get(inputIndex).getStatusICon() + "] " + list.get(inputIndex).getName());
            return false;
        } else if(str.contains("mark")) {
            String[] splitString = str.split(" ");
            int inputIndex = Integer.parseInt(splitString[1]) - 1;
            list.get(inputIndex).markDone();
            System.out.println( "Nice! I've marked this task as done:\n"
                    + "[" + list.get(inputIndex).getStatusICon() + "] " + list.get(inputIndex).getName());
            return false;
        } else {
            Task t = new Task(str, false);
            list.add(t);
            System.out.println( "added:" + str);
            return false;
        }
    }
}
