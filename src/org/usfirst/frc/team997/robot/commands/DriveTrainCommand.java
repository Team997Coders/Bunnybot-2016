package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
    private TankDrive tankDrive = new TankDrive();
    private ArcadeDrive arcadeDrive = new ArcadeDrive();

    public Drive() {
    	requires(Robot.driveTrain);
    }
    
	protected void initialize() {
	}

	protected void execute() {
		if (Robot.oi.isXbox) {
			arcadeDrive.execute();
		} else {
			tankDrive.execute();
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		tankDrive.end();
	}

	protected void interrupted() {
		end();
	}
}
