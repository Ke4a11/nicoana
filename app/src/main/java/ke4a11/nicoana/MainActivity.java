package ke4a11.nicoana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private TextView show_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_title = (TextView)findViewById(R.id.show_title);
        xmlparce();

    }

    private void xmlparce(){
        AsynCsv asynCsv = new AsynCsv(this);
//        asynCsv.execute("http://www.nicovideo.jp/ranking/fav/hourly/all?rss=2.0");
        asynCsv.execute("http://ext.nicovideo.jp/api/getthumbinfo/sm9");

    }

    void showtitle(String data){
        show_title.setText(data);
    }
}

