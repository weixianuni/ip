package yappy.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import yappy.command.AddCommand;
import yappy.command.Command;
import yappy.command.DeleteCommand;
import yappy.command.ExitCommand;
import yappy.command.FindCommand;
import yappy.command.ListCommand;
import yappy.command.MarkCommand;
import yappy.command.UnmarkCommand;
import yappy.exception.YappyException;
import yappy.task.Deadline;
import yappy.task.Event;
import yappy.task.ToDo;

/**
 * The <code>Parser</code> class is responsible for parsing raw user input
 * strings and converting them into <code>Command</code> objects.
 * If the input does not match any known command format,
 * a {@link YappyException} is thrown.</p>
 *
 * <p>This class also validates the correctness of user input
 * (e.g., checking that an index is provided where required, or that
 * dates follow the expected format).</p>
 */
public class Parser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses the given raw input string and returns the corresponding <code>Command</code>
     * object that can be executed by the application.
     *
     * <p>Date/time inputs must follow the format <code>yyyy-MM-dd HH:mm</code>.</p>
     *
     * @param command The raw user input string.
     * @return The <code>Command</code> corresponding to the parsed input.
     * @throws YappyException If the input is invalid or does not match a known command.
     */
    public static Command parse(String command) throws YappyException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("find")) {
            return parseFindCommand(command);
        } else if (command.startsWith("mark")) {
            return parseMarkCommand(command);
        } else if (command.startsWith("unmark")) {
            return parseUnmarkCommand(command);
        } else if (command.startsWith("delete")) {
            return parseDeleteCommand(command);
        } else if (command.startsWith("todo")) {
            return parseTodoCommand(command);
        } else if (command.startsWith("event")) {
            return parseEventCommand(command);
        } else if (command.startsWith("deadline")) {
            return parseDeadlineCommand(command);
        } else {
            throw new YappyException("\t Sorry!! I do not know what that command is.");
        }
    }

    private static Command parseFindCommand(String command) throws YappyException {
        if (!command.trim().contains(" ")) {
            throw new YappyException("\t Please specify the query string!");
        } else {
            return new FindCommand(command.split(" ")[1].split(","));
        }
    }

    private static Command parseMarkCommand(String command) throws YappyException {
        if (!command.trim().contains(" ")) {
            throw new YappyException("\t Please specify the index of the task to mark as completed!");
        }
        try {
            return new MarkCommand(Integer.parseInt(command.split(" ")[1]) - 1);
        } catch (NumberFormatException e) {
            throw new YappyException("\t Please input an integer index!");
        }
    }

    private static Command parseUnmarkCommand(String command) throws YappyException {
        if (!command.trim().contains(" ")) {
            throw new YappyException("\t Please specify the index of the task you wish to mark as uncompleted!");
        }
        try {
            return new UnmarkCommand(Integer.parseInt(command.split(" ")[1]) - 1);
        } catch (NumberFormatException e) {
            throw new YappyException("\t Please input an integer index!");
        }
    }

    private static Command parseDeleteCommand(String command) throws YappyException {
        if (!command.trim().contains(" ")) {
            throw new YappyException("\t Please specify the index of the task you wish to delete!");
        }
        try {
            return new DeleteCommand(Integer.parseInt(command.split(" ")[1]) - 1);
        } catch (NumberFormatException e) {
            throw new YappyException("\t Please input an integer index!");
        }
    }

    private static Command parseTodoCommand(String command) throws YappyException {
        if (!command.trim().contains(" ")) {
            throw new YappyException("\t Please specify the todo task!");
        }
        String description = command.substring("todo".length()).trim().split("/from")[0];
        return new AddCommand(new ToDo(description, false));
    }

    private static Command parseEventCommand(String command) throws YappyException {
        if (!command.trim().contains(" ")) {
            throw new YappyException("\t Please specify the event task and start and end dates!");
        }
        String description = command.substring("event".length()).trim().split("/from")[0];
        String fromAndTo = command.split("/from")[1];
        LocalDateTime from = LocalDateTime.parse(fromAndTo.split("/to")[0].strip(), FORMATTER);
        LocalDateTime to = LocalDateTime.parse(fromAndTo.split("/to")[1].strip(), FORMATTER);
        return new AddCommand(new Event(description, false, from, to));
    }

    private static Command parseDeadlineCommand(String command) throws YappyException {
        if (!command.trim().contains(" ")) {
            throw new YappyException("\t Please specify the deadline task and deadline!");
        }
        String description = command.substring("deadline".length()).trim().split("/by")[0].strip();
        LocalDateTime by = LocalDateTime.parse(command.split("/by")[1].strip(), FORMATTER);
        return new AddCommand(new Deadline(description, false, by));
    }
}
