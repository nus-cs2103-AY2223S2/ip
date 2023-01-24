public class Parser {
    public static Command parse(String command) throws CrystalException {
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.contains("unmark")) {
            String getnum = command.replaceAll("[^0-9]", "");
            int num = Integer.parseInt(getnum);
            return new UnmarkCommand(num);
        } else if (command.contains("mark")) {
            String getnum = command.replaceAll("[^0-9]", "");
            int num = Integer.parseInt(getnum);
            return new MarkCommand(num);
        } else if (command.contains("todo")) {
            String s = command.replace("todo", "");
            if (s.length() == 0) {
                throw new CrystalException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new TodoCommand(s);
        } else if (command.contains("deadline")) {
            String s = command.replace("deadline", "");
            int index = s.lastIndexOf("/by");
            String description = s.replace(s.substring(index), ""); //description of task
            s = s.substring(index + 3); //date/time for by portion
            return new DeadlineCommand(description, s);
        } else if (command.contains("event")) {
            String s = command.replace("event", "");
            String[] arr = s.split("/");
            String subs = arr[0]; //description
            String n = arr[1];
            String subsubs = n.replace("from", ""); //from
            String t = arr[2];
            String sublastsub = t.replace("to", ""); //to
            return new EventCommand(subs, subsubs, sublastsub);
        } else if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.contains("delete")) {
            String getnum = command.replaceAll("[^0-9]", "");
            int num = Integer.parseInt(getnum);
            return new DeleteCommand(num);
        } else {
            throw new CrystalException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

