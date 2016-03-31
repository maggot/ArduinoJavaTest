package RaspberryPi;

import java.io.BufferedReader;
import java.io.OutputStream;

public class SmartCarComm {
	public BufferedReader input;
	public OutputStream output;

	public SmartCarComm(){
		SerialConnect obj = new SerialConnect();
		obj.initialize();
		input = SerialConnect.input;
		output = SerialConnect.output;
		setSpeed(100);
		System.out.println("anadasd");
		obj.close();

	}

	public void writeData(String data) {
		try {
			System.out.println("Sending : " + data);
			output.write(data.getBytes());
		} catch (Exception e) {
			System.out.println("could not write to port");
		}
	}

	public void setSpeed(int speed){

		if((speed <= 100) && (speed >= -100)){
			writeData("w" + speed);
		}
	}
	public void setAngle(int angle){

		if(angle < 400 && angle > -400){
			writeData("a" + angle);
		}
	}
	public void setRotate(int angle){

		if(angle < 400 && angle > -400){
			writeData("r" + angle);
		}
	}
}
