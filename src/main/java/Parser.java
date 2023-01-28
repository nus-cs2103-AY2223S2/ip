public class Parser {

    static Command parse(String line) throws DukeException {
        String[] chunked = line.split(" ");
        String command = chunked[0];
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("mark")) {
            if (chunked.length == 1) {
                throw new DukeInvalidCommandException("Huh? You didn't give me a task to mark!");
            } else {
                return new MarkCommand(Integer.parseInt(chunked[1]));
            }
        } else if (command.equals("unmark")) {
            if (chunked.length == 1) {
                throw new DukeInvalidCommandException("Huh? You didn't give me a task to unmark!");
            } else {
                return new UnmarkCommand(Integer.parseInt(chunked[1]));
            }
        } else if (command.equals("delete")) {
            if (chunked.length == 1) {
                throw new DukeInvalidCommandException("Huh? You didn't give me a task to delete!");
            } else {
                return new DeleteCommand(Integer.parseInt(chunked[1]));
            }
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("todo")) {
            String rest = line.substring(4).trim();
            if (rest.isBlank()) {
                throw new DukeInvalidCommandException("Hey! You didn't give me a task description.");
            } else {
                return new AddToDoCommand(rest);
            }
        } else if (command.equals("deadline")) {
            String rest = line.substring(8).trim();

            // to fix
            // not perfect error checking; might be e.g. "deadline /from x /to x"
            if (rest.isEmpty()) {
                throw new DukeInvalidCommandException("Hey! You didn't give me a task description.");
            }
            // possible improvement: regex, throw error as long as doesn't meet format
            // and ui shows some sort of msg with the correct format to follow?
            String[] d = rest.split(" /by ");
            if (d.length != 2) {
                throw new DukeInvalidCommandException("Hmm! You're missing a deadline D:");
            }
            String[] datetime = d[1].split(" ");
            if (datetime.length != 2) {
                throw new DukeInvalidCommandException("Hmm! You're missing either a date or a time D:");
            }
            String date = datetime[0];
            String[] ddmmyyyy = date.split("/");
            if (ddmmyyyy.length != 3) {
                throw new DukeInvalidCommandException("Hey, get your date format right!");
            }
            String time = datetime[1];
            if (time.length() != 4) {
                throw new DukeInvalidCommandException("Hey, get your time format right!");
            }
            return new AddDeadlineCommand(d[0], date, time);
        } else if (command.equals("event")) {
            // similar issues to deadline above; to fix
            String rest = line.substring(5).trim();

            if (rest.isEmpty()) {
                throw new DukeInvalidArgumentException("Hey! You didn't give me a task description.");
            }
            String[] d = rest.split(" /from ");
            if (d.length != 2) {
                throw new DukeInvalidArgumentException("Hmm! You're missing a from parameter D:");
            }
            String[] fromTo = d[1].split(" /to ");
            if (fromTo.length != 2) {
                throw new DukeInvalidArgumentException("Hmm! You're missing a to parameter D:");
            }

            String from = fromTo[0];
            String to = fromTo[1];

            String[] datetimeFrom = from.split(" ");
            if (datetimeFrom.length != 2) {
                throw new DukeInvalidArgumentException("Hmm! You're missing either a from date or a from time D:");
            }

            String[] datetimeTo = to.split(" ");
            if (datetimeTo.length != 2) {
                throw new DukeInvalidArgumentException("Hmm! You're missing either a to date or a to time D:");
            }

            String[] dateFrom = datetimeFrom[0].split("/");
            if (dateFrom.length != 3) {
                throw new DukeInvalidArgumentException("Hey, get your from date format right!");
            }
            String timeFrom = datetimeFrom[1];
            if (timeFrom.length() != 4) {
                throw new DukeInvalidArgumentException("Hey, get your from time format right!");
            }

            String[] dateTo = datetimeTo[0].split("/");
            if (dateTo.length != 3) {
                throw new DukeInvalidArgumentException("Hey, get your to date format right!");
            }
            String timeTo = datetimeTo[1];
            if (timeTo.length() != 4) {
                throw new DukeInvalidArgumentException("Hey, get your to time format right!");
            }

            return new AddEventCommand(d[0], datetimeFrom[0], timeFrom, datetimeTo[0], timeTo);
        } else {
            throw new DukeInvalidCommandException("Huh? Sorry, I don't know what this means :(");
        }
    }
}
