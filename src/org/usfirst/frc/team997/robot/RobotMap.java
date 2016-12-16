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
	leftMotor = 0,
	rightMotor = 1,
	
	leftMotorEncoderA = 0,
	leftMotorEncoderB = 0,

	shooterFlywheel = 3,

	//// Spike
	shooterTrigger = 1 /* yes, they are on the same value.  Different motor types */;

	public static final double deadBandValue = .05;

	public static class PDP {
		public static final double maxCurrent = 180;
		public static final double overMaxMultiplier = .8;
		public static final int[]
		leftMotor = {0, 1, 2},
		rightMotor = {13, 14, 15},
		shooterFlywheel = {11},
		shooterTrigger = {4};
	}

	public static final double
	shootSpeed = 0.5; // 0.5 is the absolute maximum.
}
