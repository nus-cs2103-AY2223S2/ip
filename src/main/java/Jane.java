import java.util.Scanner;

public class Jane {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String output = "";
        Scanner in = new Scanner(System.in);
        int count = 1;
        String[] str = new String[101];
        while (in.hasNext()) {
            output = in.nextLine();
            if (output.equals("bye")) {
                break;
            }
            if (!output.equals("list") ) {
                str[count] = output;
                count+=1;
                System.out.println("added: "+ output);
            } else {
                for (int i = 1; i < count; i++) {
                    String s = String.format("%d. %s", i, str[i]);
                    System.out.println(s);
                }

            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }


}