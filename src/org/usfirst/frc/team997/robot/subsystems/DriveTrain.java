package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.AverageCurrent;
import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.SmartDashboardAble;
import org.usfirst.frc.team997.robot.commands.DriveTrainCommand;
import org.usfirst.frc.team997.robot.commands.ToggleDriveCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem implements SmartDashboardAble {
	public SpeedController left, right;
	//public Encoder leftEncoder;
	private double leftV = 0;
	private double rightV = 0;
	private AverageCurrent leftCurrent = new AverageCurrent(RobotMap.PDP.leftMotor);
	private AverageCurrent rightCurrent = new AverageCurrent(RobotMap.PDP.rightMotor);
	
	public DriveTrain() {
		Robot.smartDashboardList.add(this);
		left = new Victor(RobotMap.leftMotor);
		right = new Victor(RobotMap.rightMotor);
		//leftEncoder = new Encoder(RobotMap.leftMotorEncoderA,RobotMap.leftMotorEncoderB);
	}

	public void smartDashboard() {
	//	SmartDashboard.putNumber("Drive Train Left Motor Rate", leftEncoder.getRate());
	//	SmartDashboard.putNumber("Drive Train Left Motor Distance", leftEncoder.getDistance());
		leftCurrent.poll();
		rightCurrent.poll();
		SmartDashboard.putNumber("Left Motor Output", leftV);
		SmartDashboard.putNumber("Left Motor Current", leftCurrent.getAverage());
		SmartDashboard.putNumber("Right Motor Output", rightV);
		SmartDashboard.putNumber("Right Motor Current", rightCurrent.getAverage());
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new DriveTrainCommand());
	}

	/** Sets the voltages of the motors to the respective arguments. */
	public void driveVoltage(double l, double r) {
		final double currentMultiplier = Robot.pdpCurrentMultiplier(); 
		leftV = -l * currentMultiplier * ToggleDriveCommand.forwards;
		rightV = r * currentMultiplier * ToggleDriveCommand.forwards;

		left.set(leftV);
		right.set(rightV);
	}
}
