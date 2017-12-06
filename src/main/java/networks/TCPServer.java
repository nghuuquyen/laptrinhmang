package networks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;

public class TCPServer {
	private static ServerSocket server;

	public static void main(String[] args) {
		System.out.println("Server running at port 5000.");

		try {
			server = new ServerSocket(5000);
			server.setSoTimeout(2000);

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
				DataInputStream dis = new DataInputStream(soc.getInputStream());
				String t = dis.readUTF();

				if (t.compareTo("get time") != 0)
					soc.close();

				try {
					DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
					String st = new Date().toString();
					// Test delay for do something.
					Thread.sleep(10 * 1000);

					dos.writeUTF(st);
				} catch (SocketTimeoutException e) {
					soc.close();
				}

				soc.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
