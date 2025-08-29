package yappy.ui;

import yappy.command.*;
import yappy.exception.YappyException;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testParse_ExitCommand_returnExitCommand() throws YappyException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void testParse_ListCommand_returnListCommand() throws YappyException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    @Test
    public void testParseUnknownCommand() {
        YappyException e = assertThrows(YappyException.class, () -> Parser.parse("foobar"));
        assertEquals("\t Sorry!! I do not know what that command is.", e.getMessage());
    }
}
