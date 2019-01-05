package HTML;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Getcatalog {
	public static void main(String[] args) {
		new Getcatalog();
	}

	public Getcatalog() {
		// TODO Auto-generated constructor stub
		String searchURL = "http://www.qu.la/book/24863/";
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
			// System.out.println(string);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document document = Jsoup.parse(string);
		document.setBaseUri("http://www.qu.la");
		ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
		Elements dl = document.getElementsByTag("dl");
		Elements a = dl.get(0).getElementsByTag("a");
		int count;
		for (int i = 0; i < a.size(); i++) {
			count = i + 1;
			catalogs.add(new Catalog("24863", a.get(i).text(), a.get(i).attr("abs:href"), count));

		}
		for (Catalog element : catalogs) {
			System.out.println(element);

		}

	}

}
