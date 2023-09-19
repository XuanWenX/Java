package ThreadDownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MultithreadsA {
	private Integer threadNum = 10;
	/**
	 * 
	 * @param source ԭͼ��ַ
	 * @param targetDir �����Ŀ¼��ַ
	 */
	public void download(String source, String targetDir) {
		InputStream is = null;
		OutputStream os = null;
		try {
			//��ȡͼƬ���������
			String fileName = source.substring(source.lastIndexOf("/")+1);
			File targetFile = new File(targetDir+ "/" + fileName);
			if(!targetFile.exists()) {
				targetFile.createNewFile();
			}
			//https://pic.netbian.com/uploads/allimg/230813/221347-16919360273e05.jpg
			URL url = new URL(source);
			//��������
			URLConnection connection = url.openConnection();
			//��ȡ���룬������ļ�
			is = connection.getInputStream();
			os = new FileOutputStream(targetFile);
			byte[] bs = new byte[1024];
			int len = 0;
			//�ж��ļ���ȡ�Ƿ����
			while((len = is.read(bs)) != -1) {
				os.write(bs,0,len);
				
			}
			System.out.println("[info]ͼƬ�������"+ source + "\n\t ->"
					+ targetFile.getPath() + "("+ Math.floor(targetFile.length()/1024) + "kb)");
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//�ر��ļ�
			try {
				if(os !=null) {
					os.close();
				}
				if(is !=null) {
					is.close();
				}
				
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param targetDir �����ļ��Ĵ洢Ŀ¼
	 * @param downloadTxt ����·��
	 */
	public void multiDownloadFromFile(String targetDir, String downloadTxt) {
		File dir = new File(targetDir);
		if(!dir.exists()) {
			dir.mkdirs();
			System.out.println("��������Ŀ¼["+ dir.getPath() +"] �����ڣ����Զ�����");
		}
		List<String> resources = new ArrayList<String>();
		BufferedReader reader = null;
		ExecutorService threadPool = null;
		try {
			reader = new BufferedReader(new FileReader(downloadTxt));
		    String line = null;
		    
		    while((line=reader.readLine()) != null) {
		    	resources.add(line);
		    	System.out.println(line);
		    }
		    threadPool = Executors.newFixedThreadPool(this.threadNum);
		    MultithreadsA that = this;
		    for(String res:resources) {
		    	threadPool.execute(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						that.download(res, targetDir);
					}
				});
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(threadPool!=null) {
				threadPool.shutdown();
			}
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void start(String propDir) {
		File propFile = new File(propDir + "\\config.properties");
    	Properties properties = new Properties();
    	Reader reader = null;
    	try {
    		reader = new FileReader(propFile);
			properties.load(reader);
			String threadNum = properties.getProperty("thread-num");
			this.threadNum = Integer.parseInt(threadNum);
			String threadDir = properties.getProperty("target-dir");
//			System.out.println(threadNum);
//			System.out.println(threadDir);
			this.multiDownloadFromFile(threadDir,propDir+"\\download.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
    public static void main(String[] args) {
    	
    	MultithreadsA downloads = new MultithreadsA();
    	downloads.start("F:\\Java\\Code\\Thread\\src");
    	
//    	downloads.download("https://pic.netbian.com/uploads/allimg/230813/221347-16919360273e05.jpg", "e:/Downloads");
	
}
}
