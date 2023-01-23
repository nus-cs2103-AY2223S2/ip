import java.util.Scanner; 
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
	System.out.println("I will repeat what you say");
	
	while(true) {
		Scanner sc = new Scanner(System.in);
		String userName = sc.nextLine();
		System.out.println(userName);
	}
    }
}
