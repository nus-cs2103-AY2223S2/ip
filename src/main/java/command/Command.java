package command;
import collections.TaskList;
import exceptions.SundayException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import utilities.Ui;
import java.text.ParseException;

public enum Command {
    INITIALIZE ("init") {
        @Override
        public void execute(String filepath) throws SundayException {
            Ui.printWelcome();
            boolean isFirstLaunch = list.load();
            if (isFirstLaunch) {
                Ui.printCreatedSaveFile();
            } else {
                Ui.printLoadedSaveFile();
            }
        }
    },
    LIST ("list") {
        @Override
        public void execute(String input) {
            if (list.isEmpty()) {
                Ui.printEmptyTaskList();
            } else {
                Ui.printTaskList(list);
            }
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
                while (!(strArr[i].equals("/by") || strArr[i].equals("(by:"))) {
                    sb.append(strArr[i]);
                    sb.append(" ");
                    i++;
                }
                String description = sb.toString().substring(0, sb.length() - 1);

                sb.setLength(0);
                i++; // skip "/by" or "(by:"
                while (i < strArr.length) {
                    sb.append(strArr[i]);
                    sb.append(" ");
                    i++;
                }
                String by = sb.toString().substring(0, sb.length() - 1);

                Task deadline = new Deadline(description, by);
                list.add(deadline);
                Ui.printAddedTask(deadline, list.getUncompletedSize());
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of this deadline is invalid.");
            } catch (ParseException e) {
                throw new SundayException(
                        "OOPS! It appears the deadline given was not of the format dd/mm/yyyy hhmm");
            }
        }
    },
    EVENT ("event") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                input = input.substring(1);
                String[] strArr = input.split(" ");
                int j = 0;
                StringBuilder sb = new StringBuilder();
                while (!(strArr[j].equals("/from") || strArr[j].equals("(from:"))) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String description = sb.toString().substring(0, sb.length() - 1);

                sb.setLength(0);
                j++; // skip "/from" or "(from:"
                while (!(strArr[j].equals("/to") || strArr[j].equals("to:"))) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String start = sb.toString().substring(0, sb.length() - 1);

                sb.setLength(0);
                j++; // skip "/to" or "to:"
                while (j < strArr.length) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String end = sb.toString().substring(0, sb.length() - 1);
                Task event = new Event(description, start, end);
                list.add(event);
                Ui.printAddedTask(event, list.getUncompletedSize());
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of this event is invalid.");
            } catch (ParseException e) {
                throw new SundayException(
                        "OOPS! It appears the deadline given was not of the format dd/mm/yyyy hhmm");
            }
        }
    },
    TODO ("todo") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                String description = input.substring(1);
                Task toDo = new ToDo(description);
                list.add(toDo);
                Ui.printAddedTask(toDo, list.getUncompletedSize());
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of a todo cannot be empty.");
            }
        }
    },
    MARK ("mark") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(input.substring(1)) - 1;
                Task marked = list.mark(index);
                Ui.printMarkedTask(marked, list.getUncompletedSize());
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
                int index = Integer.parseInt(input.substring(1)) - 1;
                Task unmarked = list.unmark(index);
                Ui.printUnmarkedTask(unmarked, list.getUncompletedSize());
            }  catch (NumberFormatException e) {
                throw new SundayException("OOPS!!! You did not specify which task you wanted me to unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! Seems like that task does not exist.");
            }
        }
    },
    DELETE ("delete") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(String.valueOf(input.substring(1))) - 1;
                Task deleted = list.delete(index);
                Ui.printDeletedTask(deleted, list.getUncompletedSize());
            } catch (NumberFormatException e) {
                throw new SundayException("OOPS!!! You did not specify which task you wanted me to delete");
            } catch (IndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! Seems like that task does not exist");
            }
        }
    },
    FIND("find") {
        @Override
        public void execute(String input) {
            String keyword = input.substring(1);
            TaskList found = list.find(keyword);
            Ui.printListFound(found);
        }
    },
    BYE ("bye") {
        @Override
        public void execute(String input) throws SundayException {
            boolean didSave = list.save();
            Ui.printGoodbye(didSave);
        }
    };
    private String command;
    private static TaskList list = new TaskList();
    Command(String command) {
        this.command = command;
    }
    public abstract void execute(String input) throws SundayException;
}
