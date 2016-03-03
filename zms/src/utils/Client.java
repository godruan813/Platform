package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


import net.sf.json.JSONObject;

public class Client {
	
	private Socket socket;
	private BufferedReader br;
	private PrintWriter os;
	private String sid;
	private String errorIp;
	private String errorMessage;
	private String ip;
	private String checkInfo;
	public String getCheckInfo() {
		return checkInfo;
	}

	public void setCheckInfo(String checkInfo) {
		this.checkInfo = checkInfo;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getErrorIp() {
		return errorIp;
	}

	public void setErrorIp(String errorIp) {
		this.errorIp = errorIp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Client(){
		
	}
	
	public void connect(String ip,int port) throws IOException{
		try {
			setIp(ip);
			socket =new Socket(ip, port);
			os=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			//br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(ip);
		}
	}
		
	public void close(){
		try {
			if(br!=null){
				br.close();
			}
			if(os!=null){
				os.close();
			}
			if(socket!=null){
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String send(String command){	
		SendThread st=new SendThread(socket, os, br, command);
		try {
			st.start();
			if(command.equals("stop")||command.contains("'operate':'check'")){
				st.join();
			}
			return st.getReceicve();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	class SendThread extends Thread{
		Socket socket;
		BufferedReader br;
		PrintWriter os;
		String command;
		String receive="终止成功.";
		public SendThread(Socket socket,PrintWriter os,BufferedReader br,String command){
			this.socket=socket;
			this.os=os;
			this.br=br;
			this.command=command;
		}
		
		@Override
		public void run(){
			try {
				os.println(command);
				os.flush();
				if(command.contains("stop")||command.contains("'operate':'check'")){
					br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
					receive=br.readLine();
				}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		public String getReceicve(){
			return this.receive;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		
//		Client client = new Client("192.168.0.199", 4702);
//		client.send("cmd:cmd /c java ");
		//client.send("stop");
	}
	
}
