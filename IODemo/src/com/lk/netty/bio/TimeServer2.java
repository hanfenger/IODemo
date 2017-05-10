package com.lk.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 采用线程池和任务队列可以实现一种叫做伪异步的I/O通信框架
 * @author LK
 * @date 2017年5月9日00:33:19
 */
public class TimeServer2 {
	public static void main(String[] args) {
		int port = 8080;
		if(args!=null&&args.length>0){
			port = Integer.valueOf(args[0]);
		}
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			Socket socket = null;
			System.out.println("The time server is start in port:"+port);
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,10000);
			while(true){
				socket = serverSocket.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(serverSocket!=null){
				System.out.println("The time server close");
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				serverSocket = null;
			}
		}
		
	}
}
