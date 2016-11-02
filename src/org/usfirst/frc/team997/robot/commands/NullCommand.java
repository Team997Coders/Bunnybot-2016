package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/** This Command serves to do nothing -- to mark an action as having no effect,
 * but not being `null`.  The problem with `null` is that usages of it result
 * in NullPointerExceptions, but this instead implements its functionality as
 * doing nothing and immediately ending. */
public class NullCommand extends Command {
	protected void initialize() {}
	protected void execute() {}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {}
	protected void interrupted() {}
}
