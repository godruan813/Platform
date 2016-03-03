package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FileUtils {
	
	public static String readFile(String filePath,boolean isIgnored){
		
		InputStreamReader reader;
		StringBuffer sb=new StringBuffer();
		try {
			reader = new InputStreamReader(new FileInputStream(new File(filePath)),"utf-8");
			int i;
			char c;
			while((i=reader.read())!=-1){
				c=(char)i;
				String str=String.valueOf(c);
				if(isIgnored){
					if(str.equals("\r")||str.equals("\r\n")||str.equals("\n")||str.equals("\t")||str.equals("\\s")){
						continue;
					}
				}
				sb.append(str);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	

	public static void main(String[] args) {
		System.out.println(readFile("./WebRoot/res/menu.txt",true));
	}
	
}
