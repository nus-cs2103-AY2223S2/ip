package Duke.Command;

import Duke.Exceptions.DontKnow;

/**
 * Class designed for duke for a range of command input by users
 */

public enum Command {
  TODO("todo"),
  DEADLINE("deadline"),
  EVENT("event"),
  LIST("list"),
  MARK("mark"),
  UNMARK("unmark"),
  DELETE("delete"),
  FIND("find"),
  BYE("bye"),
  SORTEVENT("sortEvent"),
  SORTDEADLINE("sortDeadline"),
  HELP("help");

    private final String input;

    /**
   * Constructor for Command
   *
   * @param input receives String input from the user.
   */
    Command(String input) {
        this.input = input;
    }

    /**
   * match user's string input with command type.
   * if command does not exist, throw a "don't know" error.
   *
   * @param input receives String input from the user
   */
    public static Command searchCommand(String input) throws DontKnow {
        for (Command c : values()) {
            if (c.input.equals(input)) {
                return c;
            }
        }
        throw new DontKnow();
    }
}
