package ThreadDemo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GenericSample {
	public static void main(String[] args) {
        InputStream is = null;
        OutputStream os = null;
		try {
			URL url = new URL("https://www.toopic.cn/public/uploads/small/1642749405357164274940535.jpg");
		    URLConnection connection = url.openConnection();
		    is = connection.getInputStream();
		    os = new FileOutputStream("e:/Edge/sea.jpg");
		    byte[] bs = new byte [1024];
		    int len =0;
		    while((len = is.read(bs)) != -1) {
		    	System.out.println(len);
		    	os.write(bs,0,len);
		    }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(os != null) {
				    os.close();}
				if(is != null) {
					is.close();}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}

}
