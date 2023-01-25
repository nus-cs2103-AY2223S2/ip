import java.util.Scanner; 
import java.util.ArrayList; 
public class Duke {
	private static ArrayList<Task> list;
	
	public Duke() {
		this.list = new ArrayList<Task>();	
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
		for (Task i: list) {
			j++;
			System.out.println(String.valueOf(j) + ". " + "["+i.getStatusIcon() +"] "  + i);
		}
	}	
	
	private static void markTask(int i, boolean b) {
		int index = i - 1;
		list.get(index).markTask(b);
		System.out.println("Marked/Unmarked the task, task is in the state:");
		System.out.println("  " + "["+list.get(index).getStatusIcon() +"] "+ list.get(index));
	}	
	
	private static void addList(String item) {
		list.add(new Task(item));	
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

		if (in.equals("bye")) {
			System.out.println("No don't go!!");
			break;
		}
		
		line(in.length());
		String[] parm = in.split("\\s+");
		
		switch(parm[0]) {
			case "list":
				showList();
				break;
			case "mark":
				markTask(Integer.parseInt(parm[1]), true);
				break;
			case "unmark":
				markTask(Integer.parseInt(parm[1]), false);
				break;
			default:
				addList(in);

		}

		line(in.length());

	}

	
    }
}
