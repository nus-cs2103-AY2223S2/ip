package commands;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.Duke;

public class CommandStore {
  private final Map<String, Command> store;

  public CommandStore(Map<String, Command> store) {
    this.store = store;
  }

  public CommandStore(Stream<Command> cmds) {
    this(cmds.collect(
      Collectors.toUnmodifiableMap(Command::getLabel, Function.identity())
    ));
  }

  public Stream<String> executeCommand(String input, final Duke instance) {
    String[] tokens = input.split(" ");
    Command cmd = this.store.getOrDefault(tokens[0].toLowerCase(), null);

    return cmd == null
      ? Stream.of(String.format("Unknown command '%s' :(", tokens[0]))
      : cmd.apply(tokens, instance);
  }
}
