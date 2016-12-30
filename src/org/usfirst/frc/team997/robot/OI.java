package org.usfirst.frc.team997.robot;


import org.usfirst.frc.team997.robot.commands.ToggleShooterFire;
import org.usfirst.frc.team997.robot.commands.ToggleDriveCommand;
import org.usfirst.frc.team997.robot.commands.ToggleController;
import org.usfirst.frc.team997.robot.commands.ToggleFlywheel;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements SmartDashboardAble {
	private Joystick left, right, xbox;
	private JoystickButton xboxSpinup, xboxShoot;
	public boolean isXbox;
	public OI() {
		Robot.smartDashboardList.add(this);
		left = new Joystick(0);
		right = new Joystick(1);
		xbox = new Joystick(2);
		isXbox = true;
		SmartDashboard.putData("Arcade Tank Toggle", new ToggleController());
		SmartDashboard.putData("Drivetrain Switch", new ToggleDriveCommand());

		xboxSpinup = new JoystickButton(xbox, 1);
		xboxSpinup.whenPressed(new ToggleFlywheel());
		xboxShoot = new JoystickButton(xbox, 3);
		xboxShoot.whenPressed(new ToggleShooterFire());
	}
	
	public double getLeftY() {
		if (isXbox) {
			return xbox.getRawAxis(2);
		} else {
			return -left.getRawAxis(1);
		}
	}
	/** You must use the two controller layout to use this function. */
	public double getRightY() {
		assert !isXbox;
		return -right.getRawAxis(1);
	}
	/** You must use the one controller layout to use this function. */
	public double getRightX() {
		assert isXbox;
		return -xbox.getRawAxis(1);
	}

	@Override
	public void smartDashboard() {
		SmartDashboard.putNumber("Left Input", getLeftY());
		SmartDashboard.putNumber("Right Input", isXbox ? getRightX() : getRightY());
	}
}
