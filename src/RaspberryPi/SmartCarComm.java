package RaspberryPi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.Math;


public class SmartCarComm {
	public static BufferedReader input;
	public static OutputStream output;

	public static synchronized void writeData(String data) {
		System.out.println("Sent: " + data);
		try {
			output.write(data.getBytes());
		} catch (Exception e) {
			System.out.println("could not write to port");
		}
	}

	public void setSpeed(int speed){

		if(speed > 0){
			writeData("s" + speed);
		}
		else{
			writeData("s" + Math.abs(speed));
		}
	}
	public void setAngle(int angle){

		if(angle < 400 && angle > -400){
			writeData("a" + angle);
		}
		else{
			//writeData("a" + Math.abs(angle));
		}
	}



	public static void main(String[] ag) {

		try {
			SerialConnect obj = new SerialConnect();
			int c = 0;
			obj.initialize();
			input = SerialConnect.input;
			output = SerialConnect.output;
			InputStreamReader Ir = new InputStreamReader(System.in);
			BufferedReader Br = new BufferedReader(Ir);
			while (c != 7) {
				System.out.print("Enter your choice :");
				c = Integer.parseInt(Br.readLine());

				switch (c) {
				case 1:
					writeData("w100");
					break;
				case 2:
					writeData("a90");
					break;
				case 3:
					writeData("d90");
					break;
				case 4:
					writeData("x100");
					break;
				case 5:
					writeData("q30");
					break;
				case 6:
					writeData("e30");
					break;
				case 7:
					System.exit(0);
				}
			}

			String inputLine = input.readLine();
			System.out.println(inputLine);

			obj.close();

		} catch (Exception e) {
		}

	}
}
