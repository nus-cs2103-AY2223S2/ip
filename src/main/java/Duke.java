import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String text;
        String horiLine = "___________________________";
        String terminate = "bye";
        Scanner s = new Scanner(System.in);
        String[] myList = new String[100];
        int counter = 0;
        boolean flag = true;

        System.out.println(horiLine);
        System.out.println("Hello! I'm Duke \nWhat can I do for you");
        System.out.println(horiLine);

        while (flag) {
            text = s.nextLine();
            if (text.equals(terminate)) {
                System.out.println(horiLine);
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println(horiLine);
                flag = false;
            } else if (text.equals("list")) {
                System.out.println(horiLine);
                for (int i=1; i<counter + 1; i++) {
                    System.out.format("%d. %s \n", i, myList[i - 1]);
                }
                System.out.println(horiLine);
            } else {
                System.out.println(horiLine);
                System.out.println("added: " + text);
                System.out.println(horiLine);
                myList[counter] = text;
                counter++;
            }
        }

    }
}
