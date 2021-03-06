package cx_extractor;

/**
 * @author Xin Chen
 * Created on 2009-11-11
 * Updated on 2010-08-09
 * Email:  xchen@ir.hit.edu.cn
 * Blog:   http://hi.baidu.com/爱心同盟_陈鑫
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * TextExtractor功能测试类.
 */

public class UseDemo {

	public static void main(String[] args) throws IOException {

		/*
		 * 测试网站： 百度博客空间 http://hi.baidu.com/liyanhong/ 新浪娱乐音乐新闻与信息
		 * http://ent.sina.com.cn/music/roll.html 腾讯娱乐新闻与信息
		 * http://ent.qq.com/m_news/mnews.htm 搜狐音乐新闻
		 * http://music.sohu.com/news.shtml 哈尔滨工业大学校内信息网
		 * http://today.hit.edu.cn/ 哈尔滨工业大学校内新闻网 http://news.hit.edu.cn/
		 */

		/* 注意：本处只为展示抽取效果，不处理网页编码问题，getHTML只能接收GBK编码的网页，否则会出现乱码 */
		// String content = getHTML("http://ent.qq.com/a/20100417/000119.htm");

		// http://ent.sina.com.cn/y/2010-04-18/08332932833.shtml
		// http://ent.qq.com/a/20100416/000208.htm
		// http://ent.sina.com.cn/y/2010-04-18/15432932937.shtml
		// http://ent.qq.com/a/20100417/000119.htm
		// http://news.hit.edu.cn/articles/2010/04-12/04093006.htm

		/*
		 * 当待抽取的网页正文中遇到成块的新闻标题未剔除时，只要增大此阈值即可。
		 * 相反，当需要抽取的正文内容长度较短，比如只有一句话的新闻，则减小此阈值即可。
		 * 阈值增大，准确率提升，召回率下降；值变小，噪声会大，但可以保证抽到只有一句话的正文
		 */
		// TextExtract.setThreshold(76); // 默认值86
		// File file = new File("E:/学习思考/andriod_开发/test1.txt");
		// Reader reader = new FileReader(file);
		// BufferedReader bufferedReader = new BufferedReader(new
		// InputStreamReader(new FileInputStream(file), "utf-8"));
		// String string;
		// StringBuilder sb = new StringBuilder();
		// while((string = bufferedReader.readLine()) != null)
		// {
		// sb.append(string);
		// sb.append("\n");
		// }
		//// System.out.println(sb.toString());
		// System.out.println("got html");
		// System.out.println("\t"+TextExtract.parse(sb.toString()).replaceAll("
		// +","\n\n\t"));
		String searchURL = "http://www.qu.la/book/5098/5307804.html";
		String string = "";
		URL url = null;
		try {
			url = new URL(searchURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 3.0.04506)");
			InputStream content = conn.getInputStream();
			StringBuilder out = new StringBuilder();
			byte[] b = new byte[4096];
			for (int n; (n = content.read(b)) != -1;) {
				out.append(new String(b, 0, n, "utf-8"));
			}
			string = out.toString();
			System.out.println(string);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String Novel_text = TextExtract.parse(string).replaceAll("　+", "\n\n\t");
		System.out.println(Novel_text);
	}

	public static String getHTML(String strURL) throws IOException {
		URL url = new URL(strURL);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		String s = "";
		StringBuilder sb = new StringBuilder("");
		while ((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}
		return sb.toString();
	}
}
