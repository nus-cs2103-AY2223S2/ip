package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DukeTest {

    @Test
    void getResponse_normal_success() {
        Duke d = new Duke();

        assertEquals(
                d.getResponse(""),
                "OOPS!!! I'm sorry, but I don't know what you just said means :-("
        );
        d.getResponse("");
    }
}
