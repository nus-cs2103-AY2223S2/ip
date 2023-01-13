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
        String[] list = new String[100];
        int index = 0;
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            switch(input) {
                case "list":
                    line();
                    printList(list, index);
                    line();
                    break;
                default:
                    line();
                    indent("added: " + input + "\n");
                    line();
                    list[index] = input;
                    index++;
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

    public static void printList(String[] list, int index){
        for (int i = 0; i < index ; i++){
            int num = i + 1;
            String output = num + ". " + list[i];
            indent(output);
        }
    }
}
