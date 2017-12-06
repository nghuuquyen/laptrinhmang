package networks;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
	private static Socket soc;
	private static final String FILE_NAME = "/tmp/ds_canbo_va_phongthi.xlsx";

	public static void main(String[] args) {
		try {
			soc = new Socket("localhost", 5000);
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());

			FileInputStream fis = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;

			fis = new FileInputStream(FILE_NAME);
			inputStreamReader = new InputStreamReader(fis);
			bufferedReader = new BufferedReader(inputStreamReader);

			// TODO: Chuyen nguyen cai file do len mang
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			System.out.println(dis.readUTF());

			dis.close();
			dos.close();
			soc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
