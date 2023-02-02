import org.junit.jupiter.api.Test;
import page.PageException;
import page.QuestLog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class QuestLogTest {

    @Test
    public void addDeadline_validDateFormat_success() throws PageException {
        QuestLog questLog = new QuestLog();
        questLog.addDeadline("test", "2359 31/12/00");
        assertEquals("1: [D] [_] test by: 31 Dec 2000 11:59PM", questLog.toString());
    }

    @Test
    public void addDeadline_invalidDateFormat_exceptionThrown() {
        try {
            QuestLog questLog = new QuestLog();
            questLog.addDeadline("test", "23:59 Dec 31 2000");
            fail();
        } catch (PageException e) {
            assertEquals("Please format the date and time like this: 2359 31/12/99", e.getMessage());
        }
    }

}
