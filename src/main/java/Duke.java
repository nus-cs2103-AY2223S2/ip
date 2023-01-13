import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        indent("Hi, I'm Duke ");
        indent("What can I do for you :) ?\n");
        line();

        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int index = 0;
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            if (input.contains("unmark")){
                int i = Integer.parseInt(input.substring(7,8));
                unmarkTask(list, i);
                line();
                indent("Alright! I've unmarked this task :(\n");
                indent("  " + list[i - 1]);
                line();
            }
            else if (input.contains("mark")){
                int i = Integer.parseInt(input.substring(5,6));
                markTask(list, i);
                line();
                indent("OK! I've marked this task as complete :)\n");
                indent("  " + list[i - 1]);
                line();
            }
            else{
                switch (input){
                    case "list":
                        line();
                        indent("Here are the remaining tasks in your list:\n");
                        printList(list, index);
                        line();
                        break;
                    default:
                        Task newTask;
                        if (input.contains("todo")){
                            newTask = new Todo(input.substring(5));
                        } else if (input.contains("deadline")){
                            String[] arr = input.substring(9).split("/");
                            newTask = new Deadline(arr[1], arr[0]);
                        } else {
                            String[] arr = input.substring(6).split("/");
                            newTask = new Event(arr[1], arr[2], arr[0]);
                        }
                        list[index] = newTask;
                        index ++;
                        line();
                        indent("Roger! I've added this task to the list:\n");
                        indent(newTask + "\n");
                        indent(String.format("Now you have %d tasks left in the list", index));
                        line();
                }

            }
            input = scanner.nextLine();
        }
        line();
        indent("Bye. Hope to hear from you again!");
        line();
    }

    public static void indent(String txt){
        System.out.println("     " + txt );
    }

    public static void line(){
        System.out.println("_________________________________________________________________");
    }

    public static void printList(Task[] list, int index){
        for (int i = 0; i < index ; i++){
            int num = i + 1;
            String output = num + ". " + list[i];
            indent(output);
        }
    }

    public static void markTask(Task[] list, int index){
        list[index - 1].mark();
    }

    public static void unmarkTask(Task[] list, int index){
        list[index - 1].unmark();
    }
}
