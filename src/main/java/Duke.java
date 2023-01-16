import java.util.Scanner;
public class Duke {
    private static int index;
    private static String[] list = new String[100];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while(true){
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter a Command!: ");
            String s = reader.nextLine();
            if(s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if(s.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i+1 + "." + list[i]);
                }

            }
            else {
                list[index] = s;
                index++;
                System.out.println("added: " + s);
            }
        }
    }
}
