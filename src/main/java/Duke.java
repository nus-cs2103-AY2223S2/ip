import java.util.Scanner;

class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        String[] s = input.split(" ");
        Task[] list = new Task[100];
        int currentIndex = 0;
        while(!input.equals("bye")) {
           if (input.equals("list")) {
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < currentIndex; i++) {
                    System.out.println((i + 1) + "." + "[" +
                            list[i].getStatusIcon() + "] " + list[i].getDescription());
                }
                input = myObj.nextLine();
                s = input.split(" ");
            } else if (s[0].equals("unmark")) {
                    Task taskName = list[Integer.parseInt(s[1])];
                    taskName.unMark();
                    System.out.println("OK, I've marked this task as not done yet");
                        System.out.println("[" + taskName.getStatusIcon() + "] " +
                       taskName.getDescription());
                    input = myObj.nextLine();
                    s = input.split(" ");
                }
            else if (s[0].equals("mark")) {
                Task taskName = list[Integer.parseInt(s[1])];
                taskName.markAsDone();
               System.out.println("Nice! I've marked this task as done:");
               System.out.println("[" + taskName.getStatusIcon() + "] " +
                       taskName.getDescription());
                input = myObj.nextLine();
                s = input.split(" ");
            } else {
                list[currentIndex] = new Task(input);
                System.out.println("added: " + input);
                currentIndex++;
                input = myObj.nextLine();
                s = input.split(" ");
                continue;
            }

            }


        System.out.println("Bye. Hope to see you again soon!");
    }
}
