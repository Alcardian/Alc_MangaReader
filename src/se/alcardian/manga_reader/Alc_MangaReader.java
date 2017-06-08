package se.alcardian.manga_reader;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Alc_MangaReader {
	//TODO change to private
	public static Document getWebPage(String url){
		Response r;
		try {
			r = Jsoup.connect(url).method(Method.GET).execute();
			return r.parse();
		} catch (IOException e) {
			System.out.println("IO Exception when trying to read: "+url);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 */
	public static String getImageURL(String url){
		// use getWebPage() to get html
		Document doc = getWebPage(url);
		
		//This extracts the url of the image from the page.
		//System.out.println(doc.select("div#imgholder").select("a[href]").select("img[src]").attr("src")+"\n");
		
		return doc.select("div#imgholder").select("a[href]").select("img[src]").attr("src");
	}
	
	public static int getNumberOfPages(String url){
		Document doc = getWebPage(url);
		//System.out.println(doc.select("div[id=selectpage]").select("select").get(0).childNodeSize()/2+"\n");
		return doc.select("div[id=selectpage]").select("select").get(0).childNodeSize()/2;
	}
	
	public static ArrayList<String> getAllPages(String url){
		ArrayList<String> imageURLs = new ArrayList<String>();
		
		Document doc = getWebPage(url);
		
		//System.out.println(doc.select("div[id=selectpage]").select("select").get(0).childNodes()+"\n");
		
		for(Node x:doc.select("div[id=selectpage]").select("select").get(0).childNodes()){
			if(x.toString().length()>1){
				//System.out.println("http://www.mangareader.net/"+x.attr("value"));
				imageURLs.add("http://www.mangareader.net"+x.attr("value"));
			}
		}
		
		return imageURLs;
	}
	
	public static ArrayList<String> getChapters(String url){
		ArrayList<String> chapters = new ArrayList<String>();
		
		Document doc = getWebPage(url);
		//System.out.println(doc.select("table[id=listing]").select("td").select("a[href]").text());
		//Elements e;
		for(Element e:doc.select("table[id=listing]").select("td").select("a[href]")){
			//System.out.println(e);
			//System.out.println(e.text());
			//System.out.println(e.attr("href"));
			chapters.add("http://www.mangareader.net"+e.attr("href"));
		}
		
		return chapters;
	}
}
