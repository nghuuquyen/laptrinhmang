package networks;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import business.CanBoManager;

public class TCPServer {
	private static ServerSocket server;
	private static final String FILE_NAME = "/tmp/ds_canbo_va_phongthi_temp.xlsx";

	public static void main(String[] args) {
		System.out.println("Server running at port 5000.");

		try {
			server = new ServerSocket(5000);

			while (true) {
				Socket soc = server.accept();
				System.out.println("Having Connect: " + soc.getInetAddress());

				new Thread(new XuLi2(soc)).start();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public class XuLi extends Thread {
		Socket socket;

		public XuLi(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			super.run();

		}
	}

	static public class XuLi2 implements Runnable {
		Socket soc;

		public XuLi2(Socket socket) {
			this.soc = socket;
		}

		@Override
		public void run() {
			try {
				DataInputStream dataIn = new DataInputStream(soc.getInputStream());
				FileOutputStream fos = new FileOutputStream(FILE_NAME);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				
				long fileSize = dataIn.readLong();
				
				byte[] bytes = new byte[(int) fileSize];
				int count = dataIn.read(bytes);
				
				bos.write(bytes, 0, count);
				
				bos.close();

				CanBoManager cbMgm = new CanBoManager(new FileInputStream(new File(FILE_NAME)));
				System.out.println("CB Size: " + cbMgm.getRandomListCB().size());

				DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
				dos.writeUTF("Can Bo Size: " + cbMgm.getRandomListCB().size());
				
				soc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
