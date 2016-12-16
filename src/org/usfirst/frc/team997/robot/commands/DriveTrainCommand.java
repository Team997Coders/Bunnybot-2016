package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainCommand extends Command {
    public DriveTrainCommand() {
    	requires(Robot.driveTrain);
    }

	protected void initialize() {}
	
	private double deadband(double d) { return Robot.deadband(d); }

	private void arcadeDrive() {
    	double left = Robot.oi.getLeftY();
    	double right = Robot.oi.getRightX();
    	
    	Robot.driveTrain.driveVoltage(deadband(left + right), deadband(left - right));
	}

	private void tankDrive() {
		double left = Robot.oi.getLeftY();
		double right = Robot.oi.getRightY();

    	Robot.driveTrain.driveVoltage(deadband(left), deadband(right));
	}

	protected void execute() {
		if (Robot.oi.isXbox) {
			// Drivers only want arcade for xbox.
			arcadeDrive();
		} else {
			tankDrive();
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
    	Robot.driveTrain.driveVoltage(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
