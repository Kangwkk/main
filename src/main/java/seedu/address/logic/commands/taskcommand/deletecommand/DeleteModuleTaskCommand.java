package seedu.address.logic.commands.taskcommand.deletecommand;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calender.Task;
import seedu.address.model.nusmodule.ModuleCode;

/**
 * Deletes a task of certain module identified using it's displayed index from the module book.
 */
public class DeleteModuleTaskCommand extends DeleteTaskCommand {

    public static final String MESSAGE_SUCCESS = "Task Deleted: ";
    public static final String MESSAGE_NO_SUCH_MODULE = "Module specified does not exist!";

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
            throw new CommandException(MESSAGE_NO_SUCH_MODULE);
        }

        if (targetIndex.getZeroBased() >= model.getSizeOfModuleTaskList(targetModule)) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_TASK_INDEX);
        }

        Task.sortDeadlineTaskList("date");
        Task.sortDeadlineTaskList("done");
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

