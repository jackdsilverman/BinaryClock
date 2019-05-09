package com.silverman.jack;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class BinaryClock {
	final GpioController gpio;
	private Pin[] pins = new Pin[] { RaspiPin.GPIO_14, RaspiPin.GPIO_15, RaspiPin.GPIO_18, RaspiPin.GPIO_23,
			RaspiPin.GPIO_11, RaspiPin.GPIO_05, RaspiPin.GPIO_06, RaspiPin.GPIO_24, RaspiPin.GPIO_25, RaspiPin.GPIO_08,
			RaspiPin.GPIO_07, RaspiPin.GPIO_13, RaspiPin.GPIO_19, RaspiPin.GPIO_26, RaspiPin.GPIO_12, RaspiPin.GPIO_16,
			RaspiPin.GPIO_20, RaspiPin.GPIO_21 };

	public BinaryClock() {
		gpio = GpioFactory.getInstance();
	}

	private String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		String time = sdf.format(now);
		return time;
	}

	private String[] splitTime() {
		String time = getTime();
		String[] splitStringTime = time.split(":");
		return splitStringTime;
	}

	private StringBuilder convertToBinary() {
		String[] splitStringTime = splitTime();
		int[] splitTime = new int[3];
		StringBuilder binaryTime = new StringBuilder();
		for (int i = 0; i < splitTime.length; i++) {
			splitTime[i] = Integer.parseInt(splitStringTime[i]);
			binaryTime.append(Integer.toBinaryString(splitTime[i]));
		}
		return binaryTime;
	}

	public void displayBinaryTime() {
		StringBuilder binaryTime = convertToBinary();
		for (int i = 0; i < pins.length; i++) {
			if (binaryTime.charAt(i) == '1') {
				GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(pins[i]);
				led.high();
			} else if (binaryTime.charAt(i) == '0') {
				GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(pins[i]);
				led.low();
			}
		}

	}
}
