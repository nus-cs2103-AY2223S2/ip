import java.util.*;

public class Duke {

    static String line = "      _____________________________________________________________________";
    static ArrayList<Tasks> list = new ArrayList<Tasks>(100);
    public static void main(String[] args) {
        greet();
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        //echo

        while(true) {
            String input = sc.nextLine();

            if (InputProcessor.is_Bye(input)) {
                System.out.println("        byebye! Have an exquisite day, cutiepatootie");
                break;
            }

            //list
            else if (InputProcessor.is_List(input)) {
                System.out.println(line);
                for(int i = 0; i < list.size(); i++) {
                    System.out.println("        " + (i + 1) + ". " + list.get(i));
                }
                System.out.println(line);
            }

            //mark
            else if (InputProcessor.is_Mark(input)) {
                String int_Str = input.split(" ", 2)[1];
                int index = Integer.parseInt(int_Str);
                if(list.size() != 0 && index > 0 && index <= list.size() ) {
                    list.get(index - 1 ).mark();
                } else {
                    System.out.println("Invalid Index!");
                }
            }

            //unmark
            else if (InputProcessor.is_Unmark(input)) {
                String int_Str = input.split(" ", 2)[1];
                int index = Integer.parseInt(int_Str);
                if(list.size() != 0 && index > 0 && index <= list.size()) {
                    list.get(index - 1).unmark();
                } else{
                    System.out.println("Invalid Index!");
                }
            }

            //todo
            else if (InputProcessor.is_toDo(input)) {
                String todo = input.split(" ", 2)[1];
                System.out.println("\n" + line);
                list.add(new ToDo(todo));
                echo(list.get(list.size() - 1));
                System.out.println(line);
            }

            //deadline
            else if (InputProcessor.is_Deadline(input)) {
                String deadline = input.split(" ", 2)[1];
                String[] deadline_Arr = deadline.split(" /by");

                if (deadline_Arr.length == 2) {
                    String content = deadline_Arr[0];
                    String date = deadline_Arr[1];
                    System.out.println("\n" + line);
                    list.add(new Deadline(content, date));
                    echo(list.get(list.size() - 1));
                    System.out.println(line);
                    } else {
                        System.out.println("Invalid Input! You need to specify date or content is empty!");
                }
            }

            //event
            else if (InputProcessor.is_Event(input)) {
                String event = input.split(" ", 2)[1];
                String[] event_Arr = event.split(" /from", 2);

                if (event_Arr.length == 2) {
                    String content = event_Arr[0];
                    String[] period_Arr = event_Arr[1].split(" /to");
                    if (period_Arr.length == 2) {
                        String from = period_Arr[0];
                        String to = period_Arr[1];
                        System.out.println("\n" + line);
                        list.add(new Event(content, from, to));
                        echo(list.get(list.size() - 1));
                        System.out.println(line);
                    } else {
                        System.out.println("Invalid Input! You need to specify a /from and /to or content is empty!");
                    }
                } else {
                    System.out.println("Invalid Input! You need to specify date or content is empty!");
                }
            }

            else {
                System.out.println("Invalid Input!");
            }
        }
        sc.close();
        System.out.println(line);
    }

    public static void echo(Tasks task) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(line);
    }

    static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.println("\nHello! I'm Oli\n" + "What can I do for you?");
    }

}
