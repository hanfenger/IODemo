package com.lk.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author LK
 * @date 2017年5月7日23:52:00
 */
public class TimeClient {
	public static void main(String[] args) {
		int port = 8080;
		if(args!=null&&args.length>0){
			port = Integer.valueOf(args[0]);
		}
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			//创建客户端Socket,指定服务器地址和端口
			socket = new Socket("127.0.0.1", port);
			//获取输入流，并读取服务器端的响应信息
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			//客户端通过PrintWriter向服务端发送 指令
			out.println("QUERY TIME ORDER");
			System.out.println("Send Order 2 server succeed.");
			//通过 readline 读取响应并打印
			String resq = in.readLine();
			System.out.println("Now is :" + resq);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
				out = null;
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}
}
