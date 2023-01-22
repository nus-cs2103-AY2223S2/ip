package duke.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
  @Test
  public void testToString() {
    LocalDateTime by = LocalDateTime.parse("2022-01-01T00:00");
    assertEquals("[D][ ] test (by: Jan 01 2022 12:00AM)", new Deadline("test", by).toString());
  }
}
