import java.util.Scanner;
import java.lang.StringBuilder;

public class Duke {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        int numOfTasks = 0;
        String dog = "⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⣀⣀⣀⣀⢀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣀⣰⣿⣿⡻⠟⠋⠉⠉⣻⠟⠉⠉⠉⠛⢯⡛⢿⣿⣷⣤⣀⠀⠀⠀⠀⠀\n" +
                "⠀⣠⣴⠾⠛⢋⣿⠟⠋⠀⠀⠀⠀⢀⡟⠀⠀⠀⠀⠀⠀⠈⠂⣹⣿⡈⠙⠻⢶⣄⡀⠀\n" +
                "⣸⠏⠀⠀⠀⣾⣋⣀⣀⡀⠀⠀⠀⢸⠁⠀⠀⢀⣀⣀⣀⡀⠀⠈⠻⣧⠀⠀⠀⠉⠻⣦\n" +
                "⢿⡀⠀⣿⣿⠟⣫⣽⣿⣿⣿⣿⣶⣶⣶⡶⠛⣻⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⢀⣿\n" +
                "⠸⣧⠀⠈⣿⢸⣿⣿⣿⣿⣿⣿⣿⠁⢹⡇⣼⣿⣿⣿⣿⣿⣿⣿⠁⣼⡇⠀⠀⠀⣼⠇\n" +
                "⠀⠹⣷⡀⢹⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⠏⠀⣿⡇⠀⣠⡾⠋⠀\n" +
                "⠀⠀⠈⢿⣿⢿⡿⠿⠿⣿⠟⠉⠀⠀⠀⠀⠙⠛⢿⡿⠿⠛⠉⠀⠀⡿⣷⣾⠏⠀⠀⠀\n" +
                "⠀⠀⠀⠈⠋⠘⣷⠀⢀⡿⢰⣾⣟⣛⣿⣿⣷⡄⠀⢻⣆⠀⠀⠀⢰⡇⠘⠋⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠹⣧⣼⠃⠈⣧⣼⣿⣇⣈⣿⠃⠀⠀⣿⣀⣀⣴⠟⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠙⢿⣆⠀⠈⠙⢿⡛⠉⠁⠀⠀⣠⡿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡿⢶⣶⣾⣿⣶⣤⣤⣶⢿⣼⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣧⡼⠁⠉⠏⠁⠈⢹⣠⣾⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣧⠀⠀⠀⠀⠀⣸⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⣤⣤⣤⡾⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⢶⣾⣶⠾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
        System.out.println("RUFF ta see yer bro, Tom's at yer service.\n" + dog);
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            String[] inputArr;
            String desc = "variable not initialised";
            inputArr = input.split(" ");
            if (inputArr[0].equals("bye")) {
                System.out.println("See yer again RUFF!");
                break;
            } else if (inputArr[0].equals("list")) {
                System.out.println("Here are the tasks in your list dawg:");
                for (int i = 0; i < numOfTasks; i++) System.out.println( (i+1) + "." + taskList[i]);
            } else if (inputArr[0].equals("mark")) {
                taskList[Integer.parseInt(inputArr[1]) - 1].markStatus(true);
                System.out.println("The task is marked, dawg");
            } else if (inputArr[0].equals("unmark")){
                taskList[Integer.parseInt(inputArr[1]) - 1].markStatus(false);
                System.out.println("Gotcha dawg, unmarked.");
            } else if (inputArr[0].equals("todo")) {
                StringBuilder sb = new StringBuilder();
                // task is from index 1
                for (int i = 1; i < inputArr.length; i++) {
                    sb.append(inputArr[i]);
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Todo todo = new Todo(sb.toString());
                taskList[numOfTasks++] = todo;
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + numOfTasks + " in the list!");
            } else if (inputArr[0].equals("deadline")){
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/by")) {
                        desc = sb.toString();
                        sb.setLength(0);
                        continue;
                    }
                    sb.append(inputArr[i]);
                    // No need for whitespace at the end
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Deadline deadline = new Deadline(desc, sb.toString());
                taskList[numOfTasks++] = deadline;
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + numOfTasks + " in the list!");
            } else if (inputArr[0].equals("event")) {
                StringBuilder sb = new StringBuilder();
                String from = "local variable not initialised";
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/from")) {
                        desc = sb.toString();
                        sb.setLength(0);
                        continue;
                    } else if (inputArr[i].equals("/to")) {
                        from = sb.toString();
                        sb.setLength(0);
                        continue;
                    }
                    sb.append(inputArr[i]);
                    // No need for whitespace at the end
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Event event = new Event(desc, from, sb.toString());
                taskList[numOfTasks++] = event;
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + event);
                System.out.println("Now you have " + numOfTasks + " in the list!");
            }
        }

    }
}
