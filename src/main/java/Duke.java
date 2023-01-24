import java.util.Scanner; 
import java.util.ArrayList; 
public class Duke {
	private static ArrayList<String> list;
	
	public Duke() {
		this.list = new ArrayList<String>();	
	}

	private static void line(int l) {
		System.out.print('\n');
		for (int i =0; i < l; i++) {	
			System.out.print('_');
		}
			System.out.print('\n');
	}	

	private static void showList() {
		int j = 0;
		for (String i: list) {
			j++;
			System.out.println(String.valueOf(j) + ". " + i);
		}
		
		
	}	
	
	private static void addList(String item) {
		list.add(item);	
		System.out.print("added: " + item);
	}	
	

	public static void main(String[] args) {
	Duke duke = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
	System.out.println("I will remember things now");

	while(true) {
		System.out.print('\n');
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();
		
		line(in.length());
		switch(in) {
			case "list":
				showList();
				break;
			default:
				addList(in);

		}

		line(in.length());

		if (in.equals("bye")) {
			System.out.println("No don't go!!");
			break;
		}
	}

	
    }
}
