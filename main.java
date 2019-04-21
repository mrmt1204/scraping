package sample.jsoup;

import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Main {

	public static void main(String[] args) throws IOException {

		final String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100";
    	final String  base_url = "https://www.k.kyoto-u.ac.jp";
    	final String login_url = base_url + "/student/la/top";
    	final String main_url = base_url + "/student/la";
    	//ユーザーエージェント、各種URLを指定

    	String username = "username";
    	String password = "password";
    	//id, passwordを指定

        Response res1 = Jsoup.connect(login_url)
                .userAgent(ua)
                .timeout(0)
                .execute();
     // ログイン画面取得

        Document doc1 = res1.parse();
        //System.out.println(doc1);

        Response res2 = Jsoup.connect(login_url)
                .userAgent(ua)
                .timeout(0)
                .cookies(res1.cookies())
                .data("j_username", username)
                .data("j_password", password)
                .data("_eventId_proceed", "ログイン")
                .followRedirects(false)
                .userAgent(ua)
                .method(Method.POST)
                .execute();
        Document doc2 = res2.parse();
        //System.out.println(doc2);
      //ログインPOST実行


		FileWriter fw = null;
		//ファイルに書き込む変数の宣言と初期化

		try {
			fw = new FileWriter("Path", true);
            //ファイルに書き込む処理

            //Document document = Jsoup.connect(main_url).userAgent(ua).get();
            //HTMLの読み込み
            Response res3 = Jsoup.connect(main_url)
                    .userAgent(ua)
                    .timeout(0)
                    .cookies(res2.cookies())
                    .method(Method.GET)
                    .execute();
            Document document = res3.parse();


            for (Element e: document.select("div")) {
                String name = e.text();
                String link = e.attr("abs:href");
                fw.write("{\"Name\":\"" + name + "\",\"Link\":\""+ link + "\"}\n");
                System.out.println(name);
                System.out.println(link);
                System.out.println();
            }

            fw.flush();
            //各行をファイルに書き出し

		}catch(IOException e) {

			System.out.println("error");
			//例外処理

		}finally {

  			if(fw != null) {
  					try {
  						if(fw != null) {
  							fw.close();
  						}
  			//ファイルを閉じる

  					}catch(IOException e2) {}
  			}
		}
	}
}
