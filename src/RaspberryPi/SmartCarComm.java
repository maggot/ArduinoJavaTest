package RaspberryPi;

import java.io.BufferedReader;
import java.io.OutputStream;

public class SmartCarComm {
	public BufferedReader input;
	public OutputStream output;
	SerialConnect obj;

	public SmartCarComm(){
		this.obj = new SerialConnect();
		try {
			obj.initialize();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		input = SerialConnect.input;
		output = SerialConnect.output;
	}

	public synchronized void writeData(String data) {
		try {
			System.out.println("Sending : " + data);
			output.write(data.getBytes());
			output.flush();
		} catch (Exception e) {
			System.out.println("could not write to port");
		}
	}

	public void setSpeed(int speed){
		if((speed <= 100) && (speed >= -100)){
			writeData("w" + speed + "/");
		}
	}

	public void setAngle(int angle){
		if(angle < 360 && angle > -360){
			writeData("a" + angle + "/");
		}
	}

	public void setRotate(int angle){
		if(angle < 360 && angle > -360){
			writeData("r" + angle + "/");
		}
	}

	public void close() {
		this.obj.close();
	}
}
