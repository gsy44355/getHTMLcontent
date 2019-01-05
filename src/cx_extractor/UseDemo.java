package cx_extractor;

/**
 * @author Xin Chen
 * Created on 2009-11-11
 * Updated on 2010-08-09
 * Email:  xchen@ir.hit.edu.cn
 * Blog:   http://hi.baidu.com/����ͬ��_����
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
 * TextExtractor���ܲ�����.
 */

public class UseDemo {

	public static void main(String[] args) throws IOException {

		/*
		 * ������վ�� �ٶȲ��Ϳռ� http://hi.baidu.com/liyanhong/ ��������������������Ϣ
		 * http://ent.sina.com.cn/music/roll.html ��Ѷ������������Ϣ
		 * http://ent.qq.com/m_news/mnews.htm �Ѻ���������
		 * http://music.sohu.com/news.shtml ��������ҵ��ѧУ����Ϣ��
		 * http://today.hit.edu.cn/ ��������ҵ��ѧУ�������� http://news.hit.edu.cn/
		 */

		/* ע�⣺����ֻΪչʾ��ȡЧ������������ҳ�������⣬getHTMLֻ�ܽ���GBK�������ҳ�������������� */
		// String content = getHTML("http://ent.qq.com/a/20100417/000119.htm");

		// http://ent.sina.com.cn/y/2010-04-18/08332932833.shtml
		// http://ent.qq.com/a/20100416/000208.htm
		// http://ent.sina.com.cn/y/2010-04-18/15432932937.shtml
		// http://ent.qq.com/a/20100417/000119.htm
		// http://news.hit.edu.cn/articles/2010/04-12/04093006.htm

		/*
		 * ������ȡ����ҳ�����������ɿ�����ű���δ�޳�ʱ��ֻҪ�������ֵ���ɡ�
		 * �෴������Ҫ��ȡ���������ݳ��Ƚ϶̣�����ֻ��һ�仰�����ţ����С����ֵ���ɡ�
		 * ��ֵ����׼ȷ���������ٻ����½���ֵ��С��������󣬵����Ա�֤�鵽ֻ��һ�仰������
		 */
		// TextExtract.setThreshold(76); // Ĭ��ֵ86
		// File file = new File("E:/ѧϰ˼��/andriod_����/test1.txt");
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
		String Novel_text = TextExtract.parse(string).replaceAll("��+", "\n\n\t");
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
