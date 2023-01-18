import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
		ToDo td = new ToDo();
		
        String dukeLogo = " ____        _\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
		
		String cbotLogo = "  _____  _            _\n"
				+ " /  ___]| |   ^-^   _| |_\n"
				+ "|  / :D | |___  ___[_ + _]\n"
				+ "|  \\___ | / . \\/ . \\ | |\n"
				+ " \\_____]|_,_*_/\\_*_/ |_/\n";
		
		// Frequently Used Strings
		String indent = "      ~ ";
		String prompt = " v v\n";
		String stall = "\n   o\n   o\n   o\n\n";
		
		// Command Words
		String endString = "bye";
		String listTasks = "list";
		
        System.out.println(stall + indent + "Hey! D:< I'm not\n" + dukeLogo);
		System.out.println(indent + "I'm\n" + cbotLogo);
		System.out.println(stall + indent
				+ "How can I help you today?\n" + prompt);
		
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		
		while (!userInput.equals(endString)) {
			System.out.println();
			
			if (userInput.equals(listTasks)) {
				System.out.println(indent + "Here's what you have:");
				td.printTasks();
				System.out.println();
			} else {
				System.out.println(indent + td.addTask(userInput));
			}
			
			System.out.println(prompt);
			userInput = sc.nextLine();
		}
		
		System.out.println(stall + indent + "See you again!");
    }
}