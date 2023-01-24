package enums;

import exceptions.DukeException;

import static ui.Ui.DATE_TIME_FORMAT;
import static ui.Ui.LS;
public enum CommandType {
    BYE("bye", new DukeException("")),
    LIST("list", new DukeException("")),
    MARK("mark", new DukeException("Invalid format for mark." + LS + "Usage: mark <number>")),
    UNMARK("unmark", new DukeException("Invalid format for unmark." + LS + "Usage: unmark <number>")),
    DELETE("delete", new DukeException("Invalid format for delete." + LS + "Usage: delete <number>")),
    TODO("todo", new DukeException("Invalid format for todo." + LS + "Usage: todo <task>")),
    DEADLINE("deadline", new DukeException("Invalid format for Deadline." + LS + "Usage: deadline <task> /by <" + DATE_TIME_FORMAT + ">")),
    EVENT("event", new DukeException("Invalid format for Event." + LS + "Usage: <task> /from <" + DATE_TIME_FORMAT +"> /to <" + DATE_TIME_FORMAT + ">")),
    INCORRECT("incorrect", new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-("));
    private String word;
    private DukeException e;
    CommandType(String word, DukeException e) {
        this.word = word;
        this.e = e;
    }

    public DukeException getErr() {
        return this.e;
    }

    @Override
    public String toString() {
        return this.word;
    }
}
