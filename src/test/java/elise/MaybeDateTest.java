package elise;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

public class MaybeDateTest {
    @Test
    public void test1() {
        MaybeDate actual = new MaybeDate("01-12-2019");
        assertEquals(actual.toString(), "01-12-2019");
    }

    @Test
    public void test2() {
        LocalDateTime sample = LocalDateTime.of(2000, 2, 5, 11, 59);
        assertEquals(new MaybeDate(sample).toString(), "Feb 05 2000 11:59");
    }
}
