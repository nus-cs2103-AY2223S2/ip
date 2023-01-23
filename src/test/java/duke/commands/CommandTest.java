package duke.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.Duke;

public class CommandTest {
  @Test
  public void checkValidation() {
    Command test = new Command("") {
      @Override
      public void execute(String[] tokens, Duke instance) throws ValidationException {
        validate(false, "Error Message");
      } 
    };

    assertThrows(Command.ValidationException.class, () -> test.execute(null, null));
  }
}
