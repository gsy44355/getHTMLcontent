package HTML;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Htmljouptest {
	ArrayList<HashMap<String, Object>> listItems =  new ArrayList<HashMap<String,Object>>();
	public static void main(String[] args) {
		new Htmljouptest();
	}
	public Htmljouptest() {
		// TODO Auto-generated constructor stub
//		Log.i("TAGG","开始搜索");
		
        String searchURL = "http://zhannei.baidu.com/cse/search?s=920895234054625192&entry=1&q="+"w";
        String string = "";
        URL url = null;
        try {
            url = new URL(searchURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 3.0.04506)");
            InputStream content = conn.getInputStream();
            
            StringBuilder out = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = content.read(b)) != -1;) {
                out.append(new String(b, 0, n,"utf-8"));
            }
            string = out.toString();
//            System.out.println(string);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Log.i("????",String.valueOf(string.length()));
        Document doc = Jsoup.parse(string);
        Elements tbs = doc.getElementsByClass("result-item result-game-item");
        System.out.println(tbs.get(7).html());
//        Log.i("????",String.valueOf(tbs.size()));
        for(int i = 0;i<tbs.size();i++)
        {
        	HashMap<String, Object> map = new HashMap<String, Object>();
            Element table = tbs.get(i);
            Elements a = table.getElementsByTag("a");
            Elements pics = table.getElementsByTag("img");
            Elements span  = table.getElementsByTag("span");
//            System.out.println(pics.size());
            System.out.println("小说名字："+a.get(1).attr("title"));
//            map.put("title", a.get(1).attr("title"));
            System.out.println("作者："+span.get(1).text());
            System.out.println("最新章节："+a.get(2).text());
            System.out.println("小说网址："+a.get(0).attr("abs:href"));
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(a.get(0).attr("abs:href"));
            String picname = null;
            if (matcher.find()) {
            	picname = "pic"+matcher.group(0)+".jpg";
			}
            
            System.out.println(picname);
//            System.out.println("图片地址："+pics.get(0).attr("abs:src"));
//            saveToFile(pics.get(0).attr("abs:src"), "E:\\Andriod_Work\\Novel_Reader\\app\\src\\main\\res\\drawable\\"+picname);
            map.put("title",  a.get(1).attr("title")); // 书名
            map.put("author", span.get(1).text().trim()); // 作者
            map.put("updatesection",a.get(2).text());
            map.put("novelURL", a.get(0).attr("abs:href"));
            map.put("cover","");
        }
	}

	public void saveToFile(String destUrl,String file) {
		try {
			String imageUrl = destUrl;
			URL url = new URL(destUrl);
			//打开网络输入流
			DataInputStream dis = new DataInputStream(url.openStream());
			String newImageName=file;
			//建立一个新的文件
			FileOutputStream fos = new FileOutputStream(new File(newImageName));
			byte[] buffer = new byte[1024];
			int length;
			System.out.println("1");
			//开始填充数据
			while((length = dis.read(buffer))>0){
			fos.write(buffer,0,length);
			}
			System.out.println(2);
			dis.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
		  

}
