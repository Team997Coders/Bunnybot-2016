package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.SmartDashboardAble;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem implements SmartDashboardAble {
	/*TODO add motors... two Victors */
	/*TODO add encoder for the left Victor*/
	public DriveTrain() {
		/*TODO setup motors, use RobotMap values*/
	}

	public void smartDashboard() {/*TODO -- log encoder values*/}

	protected void initDefaultCommand() {/*TODO Set the default command*/}

	/** Sets the voltages of the motors to the respective arguments. */
	public void driveVoltage(double left, double right) {/*TODO*/}
}
