package org.usfirst.frc.team997.robot;

import java.util.ArrayList;

public class AverageCurrent {
	private int[] port;
	private ArrayList<Double> averages = new ArrayList<>();
	public AverageCurrent(int[] port) {this.port = port;}

	public void poll() {
		double total = 0;
		for (int i = 0; i != port.length; ++i) {
			total += Robot.pdp.getCurrent(port[i]);
		}
		total /= port.length;
		if (averages.size() < 10) {
			averages.add(total);
		} else {
			averages.remove(0);
			averages.add(total);
		}
	}
	
	public double getAverage() {
		double total = 0;
		for (int i = 0; i != averages.size(); ++i) {
			total += averages.get(i);
		}
		return total / averages.size();
	}
}
