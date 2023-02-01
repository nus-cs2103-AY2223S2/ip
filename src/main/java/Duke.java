import java.util.*;
public class Duke {
	private static ArrayList<Task> list;
	
	public Duke() {
		this.list = new ArrayList<>();	
	}

	private static void line(int l) {
		System.out.print('\n');
		for (int i =0; i < l + 15; i++) {	
			System.out.print('_');
		}
		System.out.print('\n');
	}	

	private static void showList() {
		int j = 0;
		for (Task i: list) {
			j++;
			System.out.println(String.valueOf(j) + ". " + i);
		}
	}	
	
	private static void markTask(int i, boolean b) {
		int index = i - 1;
		list.get(index).markTask(b);
		System.out.println("Marked/Unmarked the task, task is in the state:");
		System.out.print("  " + list.get(index));
	}	
	
	private static void addList(Task task) {
		list.add(task);	
		System.out.println("added: " + task.getDescription());
		System.out.print("You have: " + list.size() + " task(s)");
	}	
					
	private static void deleteTask(int i) {
		int index = i - 1;
		System.out.println("removed: " + list.get(index).toString());
		System.out.print("You have: " + (list.size() - 1)+ " task(s)");
		list.remove(index);	

	}
	
	private static void parseIn(List<String> parm) throws DukeException {
			int byIndex;
			int fromIndex;
			String description;
			List<String> l;
			String deadline;
			
		switch(parm.get(0)){
			case "list":
				showList();
				break;
			case "todo":
					l = parm.subList(1, parm.size());
					if (parm.size() == 1) {
						throw new DukeException("to do must have description");
					}

					description = String.join(" ", l);
					addList(new Todo(description));
					
				break;

			case "deadline":
					try{
						//find the /by keyword
						byIndex = parm.indexOf("/by");
						l = parm.subList(1, byIndex);
						description = String.join(" ", l);
						l = parm.subList(byIndex + 1, parm.size());
						deadline = String.join(" ", l);
						addList(new Deadline(description,deadline));
					}	
					catch (IllegalArgumentException e) {
						throw new DukeException(e);	
					}

					catch (Exception e) {
						throw new DukeException(e);	
					}
				break;
			case "event":
					try{
						fromIndex = parm.indexOf("/from");
						byIndex = parm.indexOf("/to");
						l = parm.subList(1, fromIndex);
						description = String.join(" ", l);

						List<String> f = parm.subList(fromIndex + 1, byIndex);
						String fDescription = String.join(" ", f);

						List<String> t = parm.subList(byIndex + 1, parm.size());
						String tDescription =  String.join(" ", l);
						tDescription = String.join(" ", t);
						addList(new Event(description, fDescription, tDescription));
					}
					catch (IllegalArgumentException e) {
						throw new DukeException(e);	
					}


					catch (Exception e) {
						throw new DukeException(e);	
					}
					
				break;

			case "mark":
				try{
					markTask(Integer.parseInt(parm.get(1)), true);
				}
				catch (IndexOutOfBoundsException e) {
					throw new DukeException(e);
				
				}
				catch (NumberFormatException e) {
					throw new DukeException(e);
				
				}
				catch (Exception e) {
					throw new DukeException(e);	
				}
					
				break;
			case "unmark":
				try{
					markTask(Integer.parseInt(parm.get(1)), false);
				}
				catch (IndexOutOfBoundsException e) {
					throw new DukeException(e);
				
				}
				catch (NumberFormatException e) {
					throw new DukeException(e);
				
				}
				catch (Exception e) {
					throw new DukeException(e);	
				}
					
				break;
			
			case "delete":
				try{
					deleteTask(Integer.parseInt(parm.get(1)));
				}
				catch (IndexOutOfBoundsException e) {
					throw new DukeException(e);
				
				}
				catch (NumberFormatException e) {
					throw new DukeException(e);
				
				}
				catch (Exception e) {
					throw new DukeException(e);	
				}

				break;
					
			default:
				throw new DukeException();

		}

	}	

	public static void main(String[] args) throws DukeException{
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
		String[] parmArr = in.split("\\s+");
		List<String> parm = Arrays.asList(parmArr);
		try {
			parseIn(parm);
		}
		catch (DukeException e) {
			
		}

		line(in.length());

	}

	
    }
}
