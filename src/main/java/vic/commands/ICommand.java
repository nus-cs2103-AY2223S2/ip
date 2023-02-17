package vic.commands;

import vic.exceptions.DukeException;
import vic.exceptions.InvalidCommandException;
import vic.utilities.Parser;
/**
 * Represents action command. A <code>ICommand</code> abstract class corresponds to
 * the action to execute
 */
public abstract class ICommand {
    /**
     * Represents type of actions
     */
    public enum Type {
        EVENT {
            @Override
            public ICommand createCommand(Parser parser) {
                return new AddEvent(parser);
            }
        },
        DEADLINE {
            @Override
            public ICommand createCommand(Parser parser) {
                return new AddDeadline(parser);
            }
        },
        TODO {
            @Override
            public ICommand createCommand(Parser parser) {
                return new AddTodo(parser);
            }
        },
        DELETE {
            @Override
            public ICommand createCommand(Parser parser) {
                return new Delete(parser);
            }
        },
        BYE {
            @Override
            public ICommand createCommand(Parser parser) {
                return new Exit(parser);
            }
        },
        FIND {
            @Override
            public ICommand createCommand(Parser parser) {
                return new Find(parser);

            }
        },
        LIST {
            @Override
            public ICommand createCommand(Parser parser) {
                return new ListTasks(parser);
            }
        },
        MARK {
            @Override
            public ICommand createCommand(Parser parser) {
                return new Mark(parser);
            }
        },
        UNMARK {
            @Override
            public ICommand createCommand(Parser parser) {
                return new Unmark(parser);
            }
        },
        UNDO {
            @Override
            public ICommand createCommand(Parser parser) {
                return new Undo(parser);
            }
        };

        /**
         * Converts String command type into Enum type
         *
         * @param type string representation of Enum type
         * @throws InvalidCommandException IF enum type not found
         */
        public static Type valueOfOrElse(String type) throws DukeException {
            try {
                return Type.valueOf(type);
            } catch (IllegalArgumentException e) {
                throw new InvalidCommandException(type);
            }
        }
        public abstract ICommand createCommand(Parser parser);

    }

    private String msg = "Starting Command";

    private final Parser parser;

    /**
     * Creates a ICommand object which manipulate the task
     *
     * @param parser in-charge to convert user input to valid program input command
     */
    public ICommand(Parser parser) {
        this.parser = parser;
    }

    public Parser getParser() {
        return parser;
    }

    /**
     * Sets the task message once the task is done
     *
     * @return true if exit
     * @throws DukeException IF error occur during execution of task.
     */
    public abstract boolean run() throws DukeException;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
