package top.goluck.interface_json_model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

import top.goluck.tojson.util.JsonUtil;

public class MainActivity extends AppCompatActivity {

    private TextView main_json,main_tojson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_json = (TextView) findViewById(R.id.main_json);
        //http://www.weather.com.cn/data/sk/101010100.html
        String json = "\n{ \n" +
                "\"weatherinfo\": {\n" +
                        "\"city\": \"北京\",\n" +
                        "\"cityid\": \"101010100\",\n" +
                        "\"temp\": \"18\",\n" +
                        "\"WD\": \"东南风\",\n" +
                        "\"WS\": \"1级\",\n" +
                        "\"SD\": \"17%\",\n" +
                        "\"WSE\": \"1\",\n" +
                        "\"time\": \"17:05\",\n" +
                        "\"isRadar\": \"1\",\n" +
                        "\"Radar\": \"JC_RADAR_AZ9010_JB\",\n" +
                        "\"njd\": \"暂无实况\",\n" +
                        "\"qy\": \"1011\",\n" +
                        "\"rain\": \"0\"}\n" +
                        "}";
        main_json.setText("我是解析前的数据：\n"+json);
        main_tojson = (TextView) findViewById(R.id.main_tojson);
        JsonUtil mJsonUtil = null;
        try {
            mJsonUtil=new JsonUtil(json);
        } catch (JSONException e) {
            e.printStackTrace();
            main_tojson.setText("非json数据，解析数据失败");
        }
        if(mJsonUtil!=null) {
            Weatherinfo mWeatherinfo=mJsonUtil.getT("weatherinfo",new Weatherinfo());
            String tojson = "我是解析后的数据：";
            if(mWeatherinfo!=null) {
                tojson += "\n城市：" + mWeatherinfo.getCity();
                tojson += "\n城市ID：" + mWeatherinfo.getCityid();
                tojson += "\n风向：" + mWeatherinfo.getWd();
                tojson += "\n发布时间：" + mWeatherinfo.getTime();
            }else{
                tojson += "\n解析后的对象为null，请使用正常有值的json数据来解析";
            }
            main_tojson.setText(tojson);
        }
    }
}
