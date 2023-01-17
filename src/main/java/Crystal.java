
import java.util.*;

class Crystal {
    public static void main(String[] args) {
        System.out.println(" ____________________________________________________________");
        System.out.println(" Hi! I am CRYSTAL.\n How may I be of assistance?");
        System.out.println(" ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();


        ArrayList<Task> list = new ArrayList<>();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println(" ____________________________________________________________");
                    System.out.println("Here are your current tasks:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    System.out.println(" ____________________________________________________________");
                } else if (input.contains("unmark")) {
                    String getnum = input.replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(getnum);
                    Task t = list.get(num - 1);
                    System.out.println(" ____________________________________________________________");
                    System.out.println("Alright, I've marked this task as not done: ");
                    t.isDone = false;
                    System.out.println(t.toString());
                    System.out.println(" ____________________________________________________________");
                } else if (input.contains("mark")) {
                    String getnum = input.replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(getnum);
                    Task t = list.get(num - 1);
                    System.out.println(" ____________________________________________________________");
                    System.out.println("Alright, I've marked the task as done: ");
                    t.isDone = true;
                    System.out.println(t.toString());
                    System.out.println(" ____________________________________________________________");
                } else if (input.contains("todo")) {
                    String s = input.replace("todo", "");
                    if (s.length() == 0) {
                        throw new CrystalException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    System.out.println(" ____________________________________________________________");
                    Todo t = new Todo(s);
                    t.isSet = true;
                    System.out.println("Alright, I've added this task: ");
                    System.out.println(t.toString());
                    list.add(t);
                    System.out.println("Current number of tasks : " + list.size());
                    System.out.println(" ____________________________________________________________");
                } else if (input.contains("deadline")) {
                    System.out.println(" ____________________________________________________________");
                    String s = input.replace("deadline", "");
                    int index = s.indexOf("/");
                    String subs = s.substring(0, index);
                    String subsubs = s.substring(index + 3);
                    Deadline d = new Deadline(subs, subsubs);
                    d.isSet = true;
                    System.out.println("Alright, I've added this task: ");
                    System.out.println(d.toString());
                    list.add(d);
                    System.out.println("Current number of tasks : " + list.size());
                    System.out.println(" ____________________________________________________________");
                } else if (input.contains("event")) {
                    System.out.println(" ____________________________________________________________");
                    String s = input.replace("event", "");
                    String temp = s;
                    int index = s.indexOf("/");
                    String subs = s.substring(0, index);
                    temp = temp.substring(temp.indexOf("/") + 1);
                    temp = temp.substring(0, temp.indexOf("/"));
                    String subsubs = temp.substring(4);
                    String lastsub = s.substring(s.lastIndexOf("/") + 1);
                    String sublastsub = lastsub.substring(3);

                    Event e = new Event(subs, subsubs, sublastsub);
                    e.isSet = true;
                    System.out.println("Alright, I've added this task: ");
                    System.out.println(e.toString());
                    list.add(e);
                    System.out.println("Current number of tasks : " + list.size());
                    System.out.println(" ____________________________________________________________");

                } else if (input.equals("bye")) {
                    System.out.println(" ____________________________________________________________");
                    System.out.println(" Thank You. Please come by again~!");
                    System.out.println(" ____________________________________________________________");

                } else {
                    throw new CrystalException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }


            } catch (CrystalException e) {
                System.out.println(" ____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println(" ____________________________________________________________");
                input = sc.nextLine();
                continue;


            }
            input = sc.nextLine();


        }


    }
}