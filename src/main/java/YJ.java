import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class YJ {
    public static void main(String[] args) {

        System.out.println("Hello! I'm YJ. What can I do for you?");

        // ArrayList of String
        List<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if(input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + "." + task.getStatusIcon() + " " + task.description);
                }
            } else if(input.startsWith("mark")) {
                Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
                if(taskNumber != null && tasks.get(taskNumber - 1) != null){
                    Task task = tasks.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println("Niceoooo you're done wit this: ");
                    System.out.println(task.getStatusIcon() + " " + task.description);
                }
            } else if(input.startsWith("unmark")){
                Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
                if(taskNumber != null && tasks.get(taskNumber - 1) != null){
                    Task task = tasks.get(taskNumber - 1);
                    task.markAsNotDone();
                    System.out.println("Ok lah you haven't finish this yet");
                    System.out.println(task.getStatusIcon() + " " + task.description);
                }
            } else {
                tasks.add(new Task(input));
                System.out.println("Added: " + input);
            }

            input = sc.nextLine();
        }

        System.out.println("Byebye, YJ will miss you :(");
        sc.close();
    }
}

