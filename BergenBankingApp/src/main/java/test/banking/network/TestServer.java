package test.banking.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

	
	public static void main(String[] args) throws IOException {
		int portNumber = 80;
		ServerSocket serverSocket = new ServerSocket(portNumber);
		while(true){
			System.out.println("Blocking to wait for connections on "+portNumber);
			Socket socket = serverSocket.accept();
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			String line = dis.readUTF();
			System.out.println("Received on server: "+line);
		}
			
	}
}
