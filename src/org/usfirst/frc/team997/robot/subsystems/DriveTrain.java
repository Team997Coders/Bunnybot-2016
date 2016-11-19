package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.SmartDashboardAble;
import org.usfirst.frc.team997.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem implements SmartDashboardAble {
	public SpeedController left, right;
	public Encoder leftEncoder;
	private double leftV = 0;
	private double rightV = 0;
	
	public DriveTrain() {
		Robot.smartDashboardList.add(this);
		left = new Victor(RobotMap.leftMotor);
		right = new Victor(RobotMap.rightMotor);
		//leftEncoder = new Encoder(RobotMap.leftMotorEncoderA,RobotMap.leftMotorEncoderB);
	}

	public void smartDashboard() {
	//	SmartDashboard.putNumber("Drive Train Left Motor Rate", leftEncoder.getRate());
	//	SmartDashboard.putNumber("Drive Train Left Motor Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Left Motor Current", leftV);
		SmartDashboard.putNumber("Right Motor Current", rightV);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	/** Sets the voltages of the motors to the respective arguments. */
	public void driveVoltage(double l, double r) {
		leftV = l * Robot.pdpCurrentMultiplier();
		rightV = r * Robot.pdpCurrentMultiplier();
		
		left.set(leftV);
		right.set(rightV);
		
	}
}
