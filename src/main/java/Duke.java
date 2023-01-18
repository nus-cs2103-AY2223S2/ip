import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        chat();
    }

    private static void chat() {
        int count = 0;
        Task[] data = new Task[100];
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String echo = sc.nextLine();
            String[] command = echo.split(" ");

            if (command[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (command[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i+1) + "." + data[i].toString());
                }
                continue;
            }

            if (command[0].equals("mark") || command[0].equals("unmark")) {
                String action = command[0];
                int index = Integer.parseInt(command[1]) - 1;
                if (action.equals("mark")) {
                    data[index].markAsDone(); 
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(data[index].toString());
                } else {
                    data[index].unmarkTask();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(data[index].toString());
                }
                continue;
            }
            
            if (command[0].equals("todo")) {
                System.out.println("Got it. I've added this task:");
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < command.length; i++) {
                    sb.append(command[i]);
                    if (i + 1 != command.length) {
                        sb.append(" ");
                    }
                }
                data[count] = new Todo(sb.toString());
                System.out.println(data[count].toString());
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
                continue;
            }

            if (command[0].equals("event")) {
                System.out.println("Got it. I've added this task:");
                StringBuilder sb = new StringBuilder();
                StringBuilder from = new StringBuilder();
                StringBuilder to = new StringBuilder();
                int findex = 0;
                int tindex = 0;
                for (int i = 1; i < command.length; i++) {
                    if (command[i].equals("/from")) {
                        findex = i + 1;
                        sb.setLength(sb.length() - 1);
                        break;
                    }
                    sb.append(command[i]);
                    sb.append(" ");
                }
                for (int i = findex; i < command.length; i++) {
                    if (command[i].equals("/to")) {
                        tindex = i + 1;
                        from.setLength(from.length()- 1);
                        break;
                    }
                    from.append(command[i]);
                    from.append(" ");
                }
                for (int i = tindex; i < command.length; i++) {
                    to.append(command[i]);
                    if (i + 1 != command.length) {
                        to.append(" ");
                    }
                }
                data[count] = new Event(sb.toString(), from.toString(), to.toString());
                System.out.println(data[count].toString());
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
                continue;
            }

            if (command[0].equals("deadline")) {
                System.out.println("Got it. I've added this task:");
                StringBuilder sb = new StringBuilder();
                StringBuilder by = new StringBuilder();
                int bindex = 0;
                for (int i = 1; i < command.length; i++) {
                    if (command[i].equals("/by")) {
                        bindex = i + 1;
                        sb.setLength(sb.length() - 1);
                        break;
                    }
                    sb.append(command[i]);
                    sb.append(" ");
                }
                for (int i = bindex; i < command.length; i++) {
                    by.append(command[i]);
                    if (i + 1 != command.length) {
                        by.append(" ");
                    }
                }
                data[count] = new Deadline(sb.toString(), by.toString());
                System.out.println(data[count].toString());
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
                continue;
            }
        }
        sc.close();
    }
}
