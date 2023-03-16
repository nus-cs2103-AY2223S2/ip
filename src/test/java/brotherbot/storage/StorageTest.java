package brotherbot.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void taskToStringTest(){
        Task test1 = new Todo("eat");
        Task test2 = new Deadline("eat", "11/11/2022 1111");
        Task test3 = new Event("eat", "11/11/2022 1111", "11/11/2022 2222");

        assertEquals("[T] [ ] eat",test1.toString());
        assertEquals("[D] [ ] eat By: Nov 11 2022 1111", test2.toString());
        assertEquals("[E] [ ] eat From: Nov 11 2022 1111 To: Nov 11 2022 2222", test3.toString());
    }

    @Test
    public void markTaskAsDoneTest(){
        Task test1 = new Todo("eat");
        Task test2 = new Deadline("eat", "11/11/2022 1111");
        Task test3 = new Event("eat", "11/11/2022 1111", "11/11/2022 2222");

        test1.markAsDone();
        test2.markAsDone();
        test3.markAsDone();

        assertEquals("[T] [X] eat",test1.toString());
        assertEquals("[D] [X] eat By: Nov 11 2022 1111", test2.toString());
        assertEquals("[E] [X] eat From: Nov 11 2022 1111 To: Nov 11 2022 2222", test3.toString());
    }
}