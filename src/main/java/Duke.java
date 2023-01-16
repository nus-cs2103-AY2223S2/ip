import java.util.Scanner;
class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        String[] list = new String[100];
        int currentIndex = 0;
        while(!input.equals("bye")) {
            if (!input.equals("list")) {
                list[currentIndex] = input;
                System.out.println("added: " + input);
                currentIndex++;
                input = myObj.nextLine();
                continue;
            } else {
                for (int i = 0; i < currentIndex; i++) {
                        System.out.println((i + 1) + ". " + list[i]);
                }
                input = myObj.nextLine();
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
