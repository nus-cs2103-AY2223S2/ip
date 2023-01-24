import java.time.format.DateTimeParseException;

public class Parser {

    // default constructor
    public boolean command(String userinput, TaskList l) {
        try {
            String[] parts = userinput.split(" ");
            Commands command = Commands.valueOf(parts[0]);

            switch (command) {
                case bye:
                    System.out.println("Roger. Agent Bond signing off ~");
                    return false;

                case missions:
                    System.out.println(l.print());
                    return true;

                case unmark:
                    l.unmark(Integer.parseInt(parts[1]));
                    return true;

                case mark:
                    l.mark(Integer.parseInt(parts[1]));
                    return true;

                case todo:
                    l.add(new Todo(userinput.substring(5)));
                    return true;

                case deadline:
                    parts = userinput.split("/");
                    if (parts.length != 2) {
                        throw new DukeException("Please enter valid end date.");
                    }
                    l.add(new Deadline(parts[0], parts[1]));
                    return true;

                case event:
                    parts = userinput.split("/");
                    if (parts.length != 3) {
                        throw new DukeException("Please enter valid start and end dates.");
                    }
                    l.add(new Event(parts[0], parts[1], parts[2]));
                    return true;

                case delete:
                    l.delete(Integer.parseInt(parts[1]));
                    return true;

                default:
                    System.out.println("Something has gone wrong!");
                    return false;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date format");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please key in a positive value that is less than " + l.size());
        } catch (NumberFormatException e) {
            System.out.println("Please key in an integer number");
        }
        return true;
    }
}
