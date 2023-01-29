public enum State {
    LIST ("list") {
        @Override
        public void execute(String input) {
            Printer.printBar();
            Printer.printText("Here's everything I've noted down for you:");
            Printer.printText(list.toString());
            Printer.printBar();
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
                i++;
                while (i < strArr.length) {
                    sb.append(strArr[i]);
                    sb.append(" ");
                    i++;
                }
                String deadline = sb.toString().substring(0, sb.length() - 1);
                list.add(new Deadline(description, deadline));
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of this deadline is invalid.");
            }
            Printer.printBar();
            Printer.printText("Got it. I've added this task:");
            Printer.printText("  " + list.latestTaskToString());
            Printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
            Printer.printBar();
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
                j++;
                while (!strArr[j].equals("/to")) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String start = sb.toString().substring(0, sb.length() - 1);
                sb.setLength(0);
                j++;
                while (j < strArr.length) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String end = sb.toString().substring(0, sb.length() - 1);
                list.add(new Event(description, start, end));
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of this event is invalid.");
            }
            Printer.printBar();
            Printer.printText("Got it. I've added this task:");
            Printer.printText("  " + list.latestTaskToString());
            Printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
            Printer.printBar();
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
            Printer.printBar();
            Printer.printText("Got it. I've added this task:");
            Printer.printText("  " + list.latestTaskToString());
            Printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
            Printer.printBar();
        }
    },
    MARK ("mark") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(String.valueOf(input.substring(1))) - 1;
                list.mark(index);
                Printer.printBar();
                Printer.printText("Well Done! I've marked this task as done:");
                Printer.printText("  " + list.taskToString(index));
                Printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
                Printer.printBar();
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
                Printer.printBar();
                Printer.printText("OK, I've marked this task as not done yet:");
                Printer.printText("  " + list.taskToString(index));
                Printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
                Printer.printBar();
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
                Printer.printBar();
                Printer.printText("Noted. I've removed this task:");
                Printer.printText("  " + deleted.toString());
                Printer.printText("Now you have " + list.getUncompletedSize() + " task(s) in the list.");
                Printer.printBar();
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
            Printer.printBar();
            Printer.printText("Goodbye and have a pleasant day!");
            Printer.printBar();
        }
    };
    private String command;
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
