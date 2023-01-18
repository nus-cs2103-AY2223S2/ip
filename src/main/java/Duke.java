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
		String toMark = "mark";
		int markLen = toMark.length();
		String toUnmark = "unmark";
		int unmarkLen = toUnmark.length();
		
        System.out.println(stall + indent + "Hey! D:< I'm not\n" + dukeLogo);
		System.out.println(indent + "I'm\n" + cbotLogo);
		System.out.println(stall + indent
				+ "How can I help you today?\n" + prompt);
		
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		
		while (!userInput.equals(endString)) {
			System.out.println();
			
			if (userInput.equals(listTasks)) {
				if (td.getCount() == 0) {
					System.out.println(indent + "Freedom! You have no tasks :D");
				} else {
					System.out.println(indent + "Here's what you have:");
					td.printTasks();
					System.out.println();
				}
			} else if (userInput.length() > markLen + 1
					&& userInput.substring(0, markLen).equals(toMark)) {
				try {
					int num = Integer.valueOf(userInput.substring(markLen + 1));
					System.out.println(indent + td.mark(num));
				} catch (NumberFormatException ex) {
					System.out.println("Hm, that's not a valid index!");
				}
			} else if (userInput.length() > unmarkLen + 1
					&& userInput.substring(0, unmarkLen).equals(toUnmark)) {
				try {
					int num = Integer.valueOf(userInput.substring(unmarkLen + 1));
					System.out.println(indent + td.unmark(num));
				} catch (NumberFormatException ex) {
					System.out.println("Hm, that's not a valid index!");
				}
			} else {
				System.out.println(indent + td.addTask(userInput));
			}
			
			System.out.println(prompt);
			userInput = sc.nextLine();
		}
		
		System.out.println(stall + indent + "See you again!");
    }
}