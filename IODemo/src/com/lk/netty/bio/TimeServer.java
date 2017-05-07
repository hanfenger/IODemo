package com.lk.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞I/O的TimeServer 服务端
 * @author LK
 * @date 2017年5月7日22:58:45
 * @version 1.0
 */
public class TimeServer {
	public static void main(String[] args) throws IOException {
		int port = 8080;
		if(args!=null&&args.length>0){
			port = Integer.valueOf(args[0]);
		}
		ServerSocket server = null;
		try {
			//实例化一个ServerSocket对象，服务器就可以用这个端口监听从客户端发来的连接请求
			server = new ServerSocket(port);
			System.out.println("The time server is start in port :" + port);
			Socket socket = null;
			while(true){
				//调用accept()方法开始监听，等待客户端的连接
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(server!=null){
				System.out.println("The time server close");
				server.close();
				server = null;
			}
		}
		
	}
}
