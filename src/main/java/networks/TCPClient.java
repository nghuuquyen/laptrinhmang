package networks;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {
	private static Socket soc;
	private static final String FILE_NAME = "/tmp/ds_canbo_va_phongthi.xlsx";

	public static void main(String[] args) {
		try {
			soc = new Socket("127.0.0.1", 5000);
			sendFile(soc);

			DataInputStream dis = new DataInputStream(soc.getInputStream());
			System.out.println(dis.readUTF());
			
			soc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void sendFile(Socket socket) {
		FileInputStream fis;
		BufferedInputStream bis;
		byte[] buffer = new byte[1024 * 15];
		try {
			File f = new File(FILE_NAME);
			fis = new FileInputStream(f);
			bis = new BufferedInputStream(fis);
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			int count;
			
			dos.writeLong(f.length());
			
			while ((count = bis.read(buffer)) > 0) {
				dos.write(buffer, 0, count);
			}
			
			fis.close();
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
