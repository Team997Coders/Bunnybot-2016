
package org.usfirst.frc.team997.robot;

import java.util.ArrayList;

import org.usfirst.frc.team997.robot.commands.ArcadeDrive;
import org.usfirst.frc.team997.robot.commands.NullCommand;
import org.usfirst.frc.team997.robot.commands.TankDrive;
import org.usfirst.frc.team997.robot.subsystems.DriveTrain;
import org.usfirst.frc.team997.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Shooter shooter;

	/** This should enable y'alls to choose between tank drive (joysticks?) and arcade drive (gamepads?). 
	 *  Works just like selecting an autonomous.
	 */
	public static Command driveCommand;
	private SendableChooser driveChoose;

    private Command autonomousCommand;
    private SendableChooser chooser;
    public static ArrayList<SmartDashboardAble> smartDashboardList;
    public static PowerDistributionPanel pdp;
    /*TODO add DriveTrain module, add it to smartDashboardList (.add())*/

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	smartDashboardList = new ArrayList<SmartDashboardAble>();
		oi = new OI();
		try {
			driveTrain = new DriveTrain();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//shooter = new Shooter();
		} catch (Exception e) {
			e.printStackTrace();
		}
        chooser = new SendableChooser();
        chooser.addDefault("Nothing Auto", new NullCommand());
        //chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        /*TODO add all subsystems to smartDashboardList*/
        
        try {
        	pdp = new PowerDistributionPanel();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		smartDashboard();
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();

        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	smartDashboard();
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	smartDashboard();
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void smartDashboard() {
    	for (SmartDashboardAble s : smartDashboardList) {
    		s.smartDashboard();
    	}
    }
    
    public static double pdpCurrentMultiplier() {
    	double totalCurrent = pdp.getTotalCurrent();
    	SmartDashboard.putNumber("Total Current", totalCurrent);
    	if(totalCurrent >= 115) {
    		return 0.8;
    	} else {
    		return 1;
    	}
    }
    
    public static double deadband(double x) {
    	if (Math.abs(x) < .05) { 
    		return 0;
    	}
    	return x;
    }
}
