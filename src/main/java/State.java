public enum State {
    LIST ("list") {
        @Override
        public void execute(String input) {
            printer.printBar();
            printer.printText("Here's everything I've noted down for you:");
            printer.printText(list.toString());
            printer.printBar();
        }
    },
    DEADLINE ("deadline") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                input = input.substring(1);
                String[] strArr = input.split(" ");
                int i = 0;
                StringBuilder sb = new StringBuilder();
                while (!strArr[i].equals("/by")) {
                    sb.append(strArr[i]);
                    sb.append(" ");
                    i++;
                }
                String description = sb.toString().substring(0, sb.length() - 1);
                sb.setLength(0);
                while (i < strArr.length) {
                    sb.append(strArr[i]);
                    sb.append(" ");
                    i++;
                }
                String deadline = sb.toString().substring(4, sb.length() - 1);
                list.add(new Deadline(description, deadline));
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of this deadline is invalid.");
            }
            printer.printBar();
            printer.printText("Got it. I've added this task:");
            printer.printText("  " + list.latestTaskToString());
            printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
            printer.printBar();
        }
    },
    EVENT ("event") {
        @Override
        public void execute(String input) throws SundayException{
            try {
                input = input.substring(1);
                String[] strArr = input.split(" ");
                int j = 0;
                StringBuilder sb = new StringBuilder();
                while (!strArr[j].equals("/from")) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String description = sb.toString().substring(0, sb.length() - 1);
                sb.setLength(0);
                while (!strArr[j].equals("/to")) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String start = sb.toString().substring(6, sb.length() - 1);
                sb.setLength(0);
                while (j < strArr.length) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String end = sb.toString().substring(4, sb.length() - 1);
                list.add(new Event(description, start, end));
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of this event is invalid.");
            }
            printer.printBar();
            printer.printText("Got it. I've added this task:");
            printer.printText("  " + list.latestTaskToString());
            printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
            printer.printBar();
        }
    },
    TODO ("todo") {
        @Override
        public void execute(String input) throws SundayException{
            try {
                String description = input.substring(1);
                list.add(new ToDo(description));
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of a todo cannot be empty.");
            }
            printer.printBar();
            printer.printText("Got it. I've added this task:");
            printer.printText("  " + list.latestTaskToString());
            printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
            printer.printBar();
        }
    },
    MARK ("mark") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(String.valueOf(input.substring(1))) - 1;
                list.mark(index);
                printer.printBar();
                printer.printText("Well Done! I've marked this task as done:");
                printer.printText("  " + list.taskToString(index));
                printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
                printer.printBar();
            } catch (NumberFormatException e) {
                throw new SundayException("OOPS!!! You did not specify which task you wanted me to mark");
            } catch (IndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! Seems like that task does not exist.");
            }
        }
    },
    UNMARK ("unmark") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(String.valueOf(input.substring(1))) - 1;
                list.unmark(index);
                printer.printBar();
                printer.printText("OK, I've marked this task as not done yet:");
                printer.printText("  " + list.taskToString(index));
                printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
                printer.printBar();
            }  catch (NumberFormatException e) {
                throw new SundayException("OOPS!!! You did not specify which task you wanted me to unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! Seems like that task does not exist.");
            }
        }
    },
    DELETE ("delete") {
        @Override
        public void execute(String input) throws SundayException{
            try {
                int index = Integer.parseInt(String.valueOf(input.substring(1))) - 1;
                Task deleted = list.delete(index);
                printer.printBar();
                printer.printText("Noted. I've removed this task:");
                printer.printText("  " + deleted.toString());
                printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
                printer.printBar();
            } catch (NumberFormatException e) {
                throw new SundayException("OOPS!!! You did not specify which task you wanted me to delete");
            } catch (IndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! Seems like that task does not exist");
            }
        }
    },
    BYE ("bye") {
        @Override
        public void execute(String input) {
            printer.printBar();
            printer.printText("Goodbye and have a pleasant day!");
            printer.printBar();
        }
    };
    private String command;
    private static Printer printer = new Printer();
    private static Record list = new Record();
    State(String command) {
        this.command = command;
    }
    public abstract void execute(String input) throws SundayException;
    public static State determine(String command) throws SundayException{
        try {
            return State.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SundayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
