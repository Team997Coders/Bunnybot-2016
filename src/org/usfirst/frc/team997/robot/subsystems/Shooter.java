package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.SmartDashboardAble;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem implements SmartDashboardAble{
    public Relay trigger;
    public Talon shootWheel;
    public boolean triggered;
    private double flySpeed;
	
    public Shooter() {
    	Robot.smartDashboardList.add(this);
    	trigger = new Relay(RobotMap.shooterTrigger);
    	shootWheel = new Talon(RobotMap.shooterFlywheel);
    }

    public void smartDashboard() {
    	SmartDashboard.putBoolean("Shooting", triggered);
    	SmartDashboard.putNumber("Flywheel Speed", flySpeed);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }


    public void pushTrigger() {
    	trigger.set(Relay.Value.kOn);
    	triggered = true;
	
	}

    public void unTrigger() {
    	trigger.set(Relay.Value.kOff);
    	triggered = false;
    }
    
    public void speedUp() {
    	flySpeed = RobotMap.Values.shootSpeed * Robot.pdpCurrentMultiplier();
    	shootWheel.set(flySpeed);
    }
    
    public void unSpeedUp() {
    	flySpeed = 0;
    	shootWheel.set(flySpeed);
    }
}

