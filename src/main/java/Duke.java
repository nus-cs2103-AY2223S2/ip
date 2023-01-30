import java.util.*;


public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");

        List<Task> lst = new ArrayList<>();



        while (true) {
            String input = sc.nextLine().toLowerCase();
            String[] inputArr = input.split(" ");
            String action = inputArr[0];

            if (action.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (action.equals("list")) { // list
                for (int i = 1; i <= lst.size(); i ++){
                    System.out.println(i + ". " + lst.get(i - 1));
                }
            } else if (action.equals("mark")) {
                String details = inputArr[1];
                int taskNum = Integer.parseInt(details);
                Task currTask = lst.get(taskNum - 1);
                currTask.mark();
                System.out.println("Nice! I've marked this task as done" + '\n' + currTask);
            } else if (action.equals("unmark")) {
                String details = inputArr[1];
                int taskNum = Integer.parseInt(details);
                Task currTask = lst.get(taskNum - 1);
                currTask.unMark();
                System.out.println("Nice! I've marked this task as not done yet" + '\n' + currTask);
            }else { // add task
                Task newTask = new Task(input);
                lst.add(newTask);
                System.out.println("added: " + input);
            }

        }
    }

}

