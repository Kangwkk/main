package seedu.address.logic.commands.modulecommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULETASK_TIMING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESC;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.nusmodule.ModuleTask;

public class ModuleTaskCommand extends Command {

    public static final String COMMAND_WORD = "moduleTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to one of your modules "
            + "Parameters: "
            + PREFIX_TASK_DESC + "Description "
            + PREFIX_MODULE_CODE + "Module related "
            + PREFIX_MODULETASK_TIMING + "Timing of the task "
            + PREFIX_PRIORITY + "Priority "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_DESC + "Tutorial 08 "
            + PREFIX_MODULE_CODE + "CS2030 "
            + PREFIX_MODULETASK_TIMING + "2020-01-02 "
            + PREFIX_PRIORITY + "5 ";

    public static final String MESSAGE_SUCCESS = "New task added:  ";
    public static final String MESSAGE_NO_SUCH_MODULE = "This module does not exist, maybe you can add the module first";

    private final ModuleTask toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ModuleTaskCommand(ModuleTask task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(toAdd.getModuleRelated())) {
            throw new CommandException(MESSAGE_NO_SUCH_MODULE);
        }

       model.addModuleTask(toAdd);
        return new CommandResult(MESSAGE_SUCCESS + " " +  toAdd);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTaskCommand // instanceof handles nulls
                && toAdd.equals(((ModuleTaskCommand) other).toAdd));
    }

}
