package domain.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.entities.core.Writable;

@ExtendWith(MockitoExtension.class)
public class EchoUsecaseTest {
    @Test
    void execute_invocation_shouldEchoPhrases() {
        final Writable writer = Mockito.mock(Writable.class);
        final EchoUsecase usecase = new EchoUsecase(writer);
        final String content = "hello world 123";
        usecase.execute(content.split(" "));
        Mockito.verify(writer, Mockito.times(1)).writeln(content);
    }
}
