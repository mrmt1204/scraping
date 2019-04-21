public class Main {
    public static void main(String[] args) throws IOException {
    	final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103";
    	final String LOGIN_FORM_URL = "https://github.com/login";
    	final String LOGIN_ACTION_URL = "https://github.com/session";
    	final String USERNAME = "";
    	final String PASSWORD = "";

    	// # Go to login page and grab cookies sent by server
    	Connection.Response loginForm = Jsoup.connect(LOGIN_FORM_URL)
    	                                     .method(Connection.Method.GET)
    	                                     .userAgent(USER_AGENT)
    	                                     .execute();
    	Document loginDoc = loginForm.parse(); // this is the document containing response html
    	HashMap<String, String> cookies = new HashMap<>(loginForm.cookies()); // save the cookies to be passed on to next request

    	// # Prepare login credentials
    	String authToken = loginDoc.select("#login > form > div:nth-child(1) > input[type=\"hidden\"]:nth-child(2)")
    	                           .first()
    	                           .attr("value");

    	HashMap<String, String> formData = new HashMap<>();
    	formData.put("commit", "Sign in");
    	formData.put("utf8", "e2 9c 93");
    	formData.put("login", USERNAME);
    	formData.put("password", PASSWORD);
    	formData.put("authenticity_token", authToken);

    	// # Now send the form for login
    	Connection.Response homePage = Jsoup.connect(LOGIN_ACTION_URL)
    	     .cookies(cookies)
    	     .data(formData)
    	     .method(Connection.Method.POST)
    	     .userAgent(USER_AGENT)
    	     .execute();

    	System.out.println(homePage.parse().html());


    	    /*Elements courses = document.select(".top-bootcamp-courses div.block-content h3");
    	    for (Element course : courses) {
    	        System.out.println(course.text());
    	    }*/
    }
}




    	Response res1 = Jsoup.connect("https://www.k.kyoto-u.ac.jp/student/la/top").userAgent(ua).timeout(500).execute();

    	Document doc = res1.parse();
    	//System.out.println(doc);

    	//ログインPOST実行
    	Response res2 = Jsoup.connect("https://www.k.kyoto-u.ac.jp/student/la/top").userAgent(ua).timeout(500)
    	.cookies(res1.cookies())
    	.data("KULASIS_account", "ecs-id")
    	.data("kulasis_password", "password")
    	.method(Method.POST)
    	.execute();

    	Document document = Jsoup.connect("https://www.k.kyoto-u.ac.jp/student/la/top").get();
        Elements elements = document.select(".top-bootcamp-courses div.block-content h3");
        for (Element element : elements) {
        	String href = element.attr("href");
        	System.out.println(href);
        }


    	//***** start_yearの取得 *****
    	elements = doc.getElementsByAttributeValue("name","start_year");
    	String startYear = new String();
    		for (Element element : elements) {
    		//System.out.println(element.outerHtml());
    		startYear = element.outerHtml();
    		}
    	//正規表現で「value=\"....\" selected」を検索
    	startYear = getPatternMatchFind(startYear,"value=\"....\" selected");
    		//valueの抽出（前後の切り落とし）
    	startYear = getSplitFrontBack(startYear, "value=\"", "\" selected");
    		//post形式に整形
    	startYear = "start_year=" + startYear;
    		System.out.println(startYear);

    		//***** start_monthの取得 *****
    		elements = doc.getElementsByAttributeValue("name","start_month");
    		String startMonth = new String();
    		for (Element element : elements) {
    			//System.out.println(element.outerHtml());
    			startMonth = element.outerHtml();
    		}
    		//正規表現で「value=\"..\" selected」を検索
    		startMonth = getPatternMatchFind(startMonth,"value=\"..\" selected");
    		//valueの抽出（前後の切り落とし）
    		startMonth = getSplitFrontBack(startMonth, "value=\"", "\" selected");
    		//post形式に整形
    		startMonth = "start_month=" + startMonth;
    		System.out.println(startMonth);

    }
}

public class Main {

public static void main(String[] args) throws IOException {
    Document document = Jsoup.connect("https://headlines.yahoo.co.jp/rss/list").get();
    Elements elements = document.select(".ymuiTitle  > .btn > a");
    for (Element element : elements) {
    	String href = element.attr("href");
    	System.out.println(href);
    }
}
}
public class Main {
public static void main(String[] args) throws IOException {
    Document document = Jsoup.connect("https://techacademy.jp/").get();
    Elements courses = document.select(".top-bootcamp-courses div.block-content h3");
    for (Element course : courses) {
        System.out.println(course.text());
    }

}
}
