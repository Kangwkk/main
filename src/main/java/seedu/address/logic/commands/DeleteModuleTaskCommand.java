package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.nusmodule.ModuleCode;

public class DeleteModuleTaskCommand extends DeleteTaskCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a module task "
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + PREFIX_TASK_INDEX + "index "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2103T "
            + PREFIX_TASK_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Task Deleted: ";

    private final Index targetIndex;
    private final ModuleCode targetModule;

    public DeleteModuleTaskCommand(ModuleCode moduleCode, Index targetIndex) {
        requireNonNull(targetIndex);
        requireNonNull(moduleCode);
        this.targetIndex = targetIndex;
        this.targetModule = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(targetModule)) {
            throw new CommandException("Module specified does not exist!");
        }

        if (targetIndex.getZeroBased() >= model.getSizeOfModuleTaskList(targetModule)) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_TASK_INDEX);
        }

        model.deleteModuleTask(targetModule, targetIndex);
        return new CommandResult(MESSAGE_SUCCESS + " " + targetModule + " task number "
                + targetIndex.getOneBased());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleTaskCommand // instanceof handles nulls
                && targetModule.equals(((DeleteModuleTaskCommand) other).targetModule)
                && targetIndex.equals(((DeleteModuleTaskCommand) other).targetIndex));
    }
}

