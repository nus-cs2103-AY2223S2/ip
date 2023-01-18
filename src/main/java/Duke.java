import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 0;

        while(true) {
            String message = scanner.nextLine();

            if(message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(message.equals("list")){
                for(int i = 0; i < counter; i++) {
                    int label = i + 1;
                    System.out.println(label + ". " + list[i].toString());
                }
            } else if(message.startsWith("mark") || message.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(message.split(" ")[1]);
                if(taskNumber > counter) {
                    System.out.println("Task " + taskNumber + " does not exist");
                    continue;
                }

                Task currTask = list[taskNumber - 1];
                if(message.startsWith("mark")) {
                    currTask.setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currTask);
                } else {
                    currTask.setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currTask.toString());
                }
            } else if(message.startsWith("todo")) {
                String info = message.split(" ", 2)[1];
                Todo todo = new Todo(info);
                list[counter] = todo;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println("Now you have " + counter + " task in the list");
            } else if(message.startsWith("deadline")) {
                String info = message.split(" ", 2)[1];
                String[] info_parts = info.split("/", 2);
                Deadline deadline = new Deadline(info_parts[0], info_parts[1]);
                list[counter] = deadline;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println("Now you have " + counter + " task in the list");
            } else if(message.startsWith("event")) {
                String info = message.split(" ", 2)[1];
                String[] info_parts = info.split("/", 3);
                Event event = new Event(info_parts[0],info_parts[1],info_parts[2]);
                list[counter] = event;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + counter + " task in the list");
            } else {
                list[counter] = new Task(message);
                counter++;
                System.out.println("added: " + message);
            }
        }
    }
}
