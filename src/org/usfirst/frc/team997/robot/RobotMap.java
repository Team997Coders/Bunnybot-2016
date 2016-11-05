package org.usfirst.frc.team997.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int
	//// PWM
	// Drivetrain values
	leftMotor = 1,
	rightMotor = 2,
	
	leftMotorEncoder,

	shooterFlywheel = 0,

	//// Spike
	shooterTrigger = 0 /* yes, they are on the same value.  Different motor types */;

	public static class PDP {
		public static final int[]
		leftMotor = {0, 1, 2},
		rightMotor = {13, 14, 15};

		public static final int
		shooterFlywheel = 11,
		shooterTrigger = 4;
	}
}
