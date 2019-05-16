package com.silverman.jack;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			BinaryClock binaryClock = new BinaryClock();
			binaryClock.displayBinaryTime();
			Thread.sleep(200);
		}
	}
}
