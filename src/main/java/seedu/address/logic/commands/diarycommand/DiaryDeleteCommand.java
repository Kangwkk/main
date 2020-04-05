package seedu.address.logic.commands.diarycommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENTRY_ID;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class DiaryAddCommand extends Command {

    public static final String COMMAND_WORD = "diaryDelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Allows for deleting diary entries "
            + "Parameters: "
            + PREFIX_ENTRY_ID + "ENTRY CONTENT"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ENTRY_ID + "1";

    public static final String MESSAGE_SUCCESS = "Diary entry deleted";

    private final int entryId;

    public DiaryAddCommand(int entryId) {
        this.entryId = entryId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isInvalidEntryId(entryId)) {
            throw new CommandException("The diary entry ID is invalid!");
        }

        model.deleteDiaryEntry(entryId);
        String messageResult = "Diary entry " + entryId + " deleted."
        return new CommandResult(messageResult);
    }
}