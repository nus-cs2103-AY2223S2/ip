
import java.util.*;
import java.util.regex.*;


class Crystal {
    public static void main(String[] args) {
        System.out.println(" ____________________________________________________________");
        System.out.println(" Hi! I am CRYSTAL.\n How may I be of assistance?");
        System.out.println(" ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();




        //ArrayList<String> list = new ArrayList<>();
        ArrayList<Task> list = new ArrayList<>();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(" ____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                System.out.println(" ____________________________________________________________");
            } else if (input.contains("unmark")) {
                String getnum = input.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(getnum);
                Task t = list.get(num - 1);
                System.out.println(" ____________________________________________________________");
                System.out.println("Alright, I've marked this task as done: ");
                t.isDone = false;
                System.out.println(t.toString());
                System.out.println(" ____________________________________________________________");
            } else if (input.contains("mark")) {
                String getnum = input.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(getnum);
                Task t = list.get(num - 1);
                System.out.println(" ____________________________________________________________");
                System.out.println("Alright, I've marked the task as not done: ");
                t.isDone = true;
                System.out.println(t.toString());
                System.out.println(" ____________________________________________________________");
            }  else {
                System.out.println(" ____________________________________________________________");
                //list.add(input);
                Task t = new Task(input);
                list.add(t);
                System.out.println("added: " + input);
                System.out.println(" ____________________________________________________________");

            }

            input = sc.nextLine();


        }
        System.out.println(" ____________________________________________________________");
        System.out.println(" Thank You. Please come by again~!");
        System.out.println(" ____________________________________________________________");

    }
}