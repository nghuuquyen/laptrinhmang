package networks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
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
		BufferedOutputStream out;
		byte[] buffer = new byte[1024 * 15];
		try {
			fis = new FileInputStream(new File(FILE_NAME));
			bis = new BufferedInputStream(fis);
			out = new BufferedOutputStream(socket.getOutputStream());
			int count;
			while ((count = bis.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
			
			// TODO: Truyen ky hieu ngat file.
			
			fis.close();
			bis.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
