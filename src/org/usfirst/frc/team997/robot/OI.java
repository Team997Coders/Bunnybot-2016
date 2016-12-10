package org.usfirst.frc.team997.robot;


import org.usfirst.frc.team997.robot.commands.SwitchDriveTrain;
import org.usfirst.frc.team997.robot.commands.ToggleController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick left, right, xbox;
	public boolean isXbox;
	public OI() {
		left = new Joystick(0);
		right = new Joystick(1);
		xbox = new Joystick(2);
		isXbox = false;
		SmartDashboard.putData("Arcade Tank Toggle", new ToggleController());
		SmartDashboard.putData("Drivetrain Switch", new SwitchDriveTrain());
	}
	
	public double getLeftY() {
		if (isXbox) {
			return xbox.getRawAxis(1);
		} else {
			return left.getRawAxis(1);
		}
	}
	/** You must use the two controller layout to use this function. */
	public double getRightY() {
		assert !isXbox;
		return right.getRawAxis(1);
	}
	/** You must use the one controller layout to use this function. */
	public double getRightX() {
		assert isXbox;
		return xbox.getRawAxis(2);
	}
}
