package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCommand extends Command {
	public static boolean flywheelOn, triggerPulled;

	private static Timer t = new Timer();
	
	public static void startShoot() {
		t.reset();
		t.start();
		flywheelOn = true;
		triggerPulled = true;
	}

    public ShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flywheelOn = false;
    	triggerPulled = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (t.get() >= .3) {
    		triggerPulled = false;
    		flywheelOn = false;
    	}
    	if (flywheelOn) {
    		Robot.shooter.speedUpFlywheel();
    	} else {
    		Robot.shooter.slowDownFlywheel();
    	}
    	if (triggerPulled) {
    		Robot.shooter.pushTrigger();
    	} else {
    		Robot.shooter.releaseTrigger();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
