package ke4a11.nicoana;


import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.net.URL;
import java.net.URLConnection;


public class AsynCsv extends AsyncTask<String, Integer, String> {

    private MainActivity mainActivity;

    // コンストラクタ
    public AsynCsv(MainActivity ma) {
        mainActivity = ma;
    }

    // バックグラウンドで処理する（重い処理）
    @Override
    protected String doInBackground(String... params) {
        String uri = params[0];
        return httpGet(uri); //onPostExecute に渡されるString型の変数uri
    }

    // バックグラウンド処理が終了した後にメインスレッドに渡す処理
    @Override
    protected void onPostExecute(String result) {
        mainActivity.showtitle(result);
    }

    private String httpGet(String uri) {

        try {
            //初期値を入れないと怒られる
            String data = "";

            XmlPullParser xmlPullParser = Xml.newPullParser();

            URL url = new URL(uri);
            URLConnection connection = url.openConnection();
            xmlPullParser.setInput(connection.getInputStream(), "UTF-8");
            int eventType;
            boolean s = true;

            while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT) {
                //始まり、かつ、title要素があれば
                if (eventType == XmlPullParser.START_TAG && "title".equals(xmlPullParser.getName())) {
                    data += xmlPullParser.nextText();
                }
            }

            return data;

        } catch (Exception e) {
            Log.d("aaaaaaa",e.toString());
            return e.toString();
        }
    }
}
