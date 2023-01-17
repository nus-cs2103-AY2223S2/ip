import java.util.*;

public class Duke {

    static String line = "      -----------------------------------------------------------------";
    static ArrayList<Tasks> list = new ArrayList<Tasks>(100);
    public static void main(String[] args) {
        greet();
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        //echo

        while(true) {
            String input = sc.nextLine();
            String[] input_List = input.split(" ");
            String firstWord = "";
            String secondWord = "";

            if (input_List.length == 2 ) {
                firstWord = input_List[0];
                secondWord = input_List[1];
            }


            if (input.equalsIgnoreCase("bye")) {
                System.out.println("        byebye! Have an exquisite day, cutiepatootie");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                for(int i = 0; i < list.size(); i++) {
                    System.out.println("        " + (i + 1) + ". " + list.get(i));
                }
                System.out.println(line);
            } else if (firstWord.equalsIgnoreCase("mark")) {
                String int_Str = secondWord;
                int index = Integer.parseInt(int_Str);
                if(list.size() != 0 && index > 0 && index <= list.size() ) {
                    list.get(index - 1 ).mark();
                } else{
                    System.out.println("Invalid Index!");
                }
            } else if (firstWord.equalsIgnoreCase("unmark")) {
                String int_Str = secondWord;
                int index = Integer.parseInt(int_Str);
                if(list.size() != 0 && index > 0 && index <= list.size()) {
                    list.get(index).unmark();
                } else{
                    System.out.println("Invalid Index!");
                }
            }
                else {
                //echoes input
                System.out.println("\n" + line);
                addToList(input);
                System.out.println(line);
            }
        }
        sc.close();
        System.out.println(line);
    }

    public static void addToList(String name) {
        if (name == "" || name == null) {
            System.out.println("Empty input!");
        } else {
            System.out.println();
            System.out.println("        added: " + name);
            list.add(new Tasks(name));
        }
    }

    public static void echo(String name) {
        if (name == "" || name.isEmpty()) {
            System.out.println("Empty input!");
        } else {
            System.out.println();
            System.out.println(name);
        }
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
