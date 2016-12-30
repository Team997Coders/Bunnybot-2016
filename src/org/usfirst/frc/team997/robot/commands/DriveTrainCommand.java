package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.DoubleReference;
import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrainCommand extends Command {
	private final DoubleReference lastLeft = new DoubleReference(0), lastRight = new DoubleReference(0);
	private final double maxAccel = 0.10;
	
    public DriveTrainCommand() {
    	requires(Robot.driveTrain);
    }

	protected void initialize() {}
	
	private double deadband(double d) { return Robot.deadband(d); }

//    private double algorithm(final double current, final DoubleReference last, final String side) {
//    	boolean maxedOut = true;
//    	double ret = current;
//    	if (current == 0) {
//    		last.value = 0;
//    		maxedOut = false;
//    	} else if (current - last.value > maxAccel) {
//    		last.value += maxAccel;
//    		ret = last.value;
//    	} else if (last.value - current > maxAccel) {
//    		last.value -= maxAccel;
//    		ret = last.value;
//    	} else {
//    		if ((last.value < 0 && current > 0) || (last.value > 0 && current < 0)) {
//    			maxedOut = true;
//    		} else {
//    			maxedOut = false;
//    		}
//    		last.value = current;
//    	}
//		SmartDashboard.putBoolean("Max out " + side, maxedOut);
//    	return ret;
//    }
	private double algorithm(double x, DoubleReference y, String z) { return x; }
    
	private void arcadeDrive() {
    	final double leftInput = Robot.oi.getLeftY();
    	final double rightInput = Robot.oi.getRightX();
    	double left = algorithm(deadband(leftInput + rightInput), lastLeft, "left");
    	double right = algorithm(deadband(leftInput - rightInput), lastRight, "right");
    	
    	Robot.driveTrain.driveVoltage(left, right);
	}

	private void tankDrive() {
		double left = algorithm(deadband(Robot.oi.getLeftY()), lastLeft, "left");
		double right = algorithm(deadband(-Robot.oi.getRightY()), lastRight, "right");

    	Robot.driveTrain.driveVoltage(left, right);
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
