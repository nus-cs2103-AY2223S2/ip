import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Leo: Yoooz it's your boy Leo! What's up?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tArr = new ArrayList<>();
        while (true) {
            System.out.print("You: ");
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Leo: Good talk man, catch you again some other time!");
                break;
            } else if (str.equals("list")) {
                System.out.println("Leo: Here you go!");
                for(int i = 0; i < tArr.size(); i++) {
                    System.out.println(i + 1 + ". " + tArr.get(i));
                }
            } else if (str.split(" ")[0].equals("mark")) {
                int index = Integer.parseInt(str.split(" ")[1]) - 1;
                System.out.println("Good job man!");
                System.out.println(tArr.get(index).mark());
            } else if (str.split(" ")[0].equals("unmark")) {
                int index = Integer.parseInt(str.split(" ")[1]) - 1;
                System.out.println("Did you forget something?");
                System.out.println(tArr.get(index).unmark());
            } else {
                tArr.add(new Task(str));
                System.out.println("Leo: added '" + str + "'");
            }
        }
    }
}
