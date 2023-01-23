package duke.commands;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.Duke;
import duke.main.Ui;

public class Parser implements BiConsumer<String, Duke> {
  private final Map<String, Command> store;

  public Parser(Map<String, Command> store) {
    this.store = store;
  }

  public Parser(Stream<Command> cmds) {
    this(cmds.collect(
      Collectors.toUnmodifiableMap(Command::getLabel, Function.identity())
    ));
  }

  public void executeCommand(String input, final Duke instance) {
    String[] tokens = input.split(" ");
    Command cmd = this.store.getOrDefault(tokens[0].toLowerCase(), null);

    if (cmd != null) {
      cmd.accept(tokens, instance);
    } else {
      Ui.print("Unknown command '%s' :(", input);
    }
  }

  @Override
  public void accept(String input, final Duke instance) { executeCommand(input, instance); }
}
