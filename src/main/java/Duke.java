import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Leo: Yoooz it's your boy Leo! What's up?");
        Scanner sc = new Scanner(System.in);
        String[] strArr = new String[100];
        int count = 0;
        while(true) {
            System.out.print("You: ");
            String str = sc.nextLine();
            if(str.equals("bye")) {
                System.out.println("Leo: Good talk man, catch you again some other time!");
                break;
            } else if(str.equals("list")) {
                System.out.println("Leo: Here you go!");
                for(int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + strArr[i]);
                }
            } else {
                strArr[count] = str;
                count++;
                System.out.println("Leo: added '" + str + "'");
            }
        }
    }
}
