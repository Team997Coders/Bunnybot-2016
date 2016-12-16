package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.SmartDashboardAble;
import org.usfirst.frc.team997.robot.commands.ShooterCommand;

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
    	setDefaultCommand(new ShooterCommand());
    }

    public void pushTrigger() {
    	trigger.set(Relay.Value.kForward);
    	triggered = true;
	}

    public void releaseTrigger() {
    	trigger.set(Relay.Value.kOff);
    	triggered = false;
    }
    
    public void speedUpFlywheel() {
    	flySpeed = RobotMap.shootSpeed;// * Robot.pdpCurrentMultiplier();
    	shootWheel.set(flySpeed);
    }
    
    public void slowDownFlywheel() {
    	flySpeed = 0;
    	shootWheel.set(flySpeed);
    }
}

