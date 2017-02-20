package top.goluck.interface_json_model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;

import java.util.List;

import top.goluck.tojson.util.JsonUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //有key值的json数据，解析java对象
        analysis1();
        //无key值的son数据，解析java对象
        analysis2();
        //有key值的son数组数据，解析java集合对象
        analysis3();
        //无key值的son数组数据，解析java集合对象
        analysis4();
    }

    private void analysis4() {
        TextView main_json4= (TextView) findViewById(R.id.main_json4);
        TextView main_tojson4 = (TextView) findViewById(R.id.main_tojson4);
        String json = "[\n" +
                "{\n" +
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
                "\"rain\": \"0\"\n" +
                "},\n" +
                "{\n" +
                "\"city\": \"上海\",\n" +
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
                "\"rain\": \"0\"\n" +
                "}\n" +
                "]";
        JsonUtil mJsonUtil = null;
        String error ="";
        try {
            mJsonUtil=new JsonUtil(json);
        } catch (JSONException e) {
            e.printStackTrace();
            error = "非json数据，解析数据失败";
        }
        List<Weatherinfo> mWeatherinfos = null;
        if(mJsonUtil!=null) {
            mWeatherinfos = mJsonUtil.getList(new Weatherinfo());
        }
        String tojson = "我是解析后的数据：";
        if(mWeatherinfos!=null && mWeatherinfos.size()>=2) {
            tojson += "\n解析后得到的集合大小是：" + mWeatherinfos.size();
            tojson += "\n集合对象的第一个对象的城市：" + mWeatherinfos.get(0).getCity();
            tojson += "\n集合对象的第二个对象的城市：" + mWeatherinfos.get(1).getCity();
        }else{
            tojson += "\n解析后的对象为null，请使用正常有值的json数组且集合大小大于等于2的weatherinfo对象的json数据来解析";
        }
        tojson(main_json4,json,error,tojson,main_tojson4);
    }

    private void analysis3() {
        TextView main_json3= (TextView) findViewById(R.id.main_json3);
        TextView main_tojson3 = (TextView) findViewById(R.id.main_tojson3);
        String json = "{\n" +
                "\"weatherinfos\": [\n" +
                "{\n" +
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
                "\"rain\": \"0\"\n" +
                "},\n" +
                "{\n" +
                "\"city\": \"上海\",\n" +
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
                "\"rain\": \"0\"\n" +
                "}\n" +
                "]\n" +
                "}";
        JsonUtil mJsonUtil = null;
        String error ="";
        try {
            mJsonUtil=new JsonUtil(json);
        } catch (JSONException e) {
            e.printStackTrace();
            error = "非json数据，解析数据失败";
        }
        List<Weatherinfo> mWeatherinfos = null;
        if(mJsonUtil!=null) {
            mWeatherinfos = mJsonUtil.getList("weatherinfos",new Weatherinfo());
        }
        String tojson = "我是解析后的数据：";
        if(mWeatherinfos!=null && mWeatherinfos.size()>=2) {
            tojson += "\n解析后得到的集合大小是：" + mWeatherinfos.size();
            tojson += "\n集合对象的第一个对象的城市：" + mWeatherinfos.get(0).getCity();
            tojson += "\n集合对象的第二个对象的城市：" + mWeatherinfos.get(1).getCity();
        }else{
            tojson += "\n解析后的对象为null，请使用正常有值的json数组且集合大小大于等于2的weatherinfo对象的json数据来解析";
        }
        tojson(main_json3,json,error,tojson,main_tojson3);
    }

    private void analysis2() {
        TextView main_json2= (TextView) findViewById(R.id.main_json2);
        TextView main_tojson2 = (TextView) findViewById(R.id.main_tojson2);
        String json = "{\n" +
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
                "\"rain\": \"0\"\n" +
                "}";
        JsonUtil mJsonUtil = null;
        String error ="";
        try {
            mJsonUtil=new JsonUtil(json);
        } catch (JSONException e) {
            e.printStackTrace();
            error = "非json数据，解析数据失败";
        }
        Weatherinfo mWeatherinfo = null;
        if(mJsonUtil!=null) {
            mWeatherinfo = mJsonUtil.getT(new Weatherinfo());

        }
        String tojson = "我是解析后的数据：";
        if(mWeatherinfo!=null) {
            tojson += "\n城市：" + mWeatherinfo.getCity();
            tojson += "\n城市ID：" + mWeatherinfo.getCityid();
            tojson += "\n风向：" + mWeatherinfo.getWd();
            tojson += "\n发布时间：" + mWeatherinfo.getTime();
        }else{
            tojson += "\n解析后的对象为null，请使用正常有值的weatherinfo对象的json数据来解析";
        }
        tojson(main_json2,json,error,tojson,main_tojson2);
    }

    private void analysis1() {
        TextView main_json1= (TextView) findViewById(R.id.main_json1);
        TextView main_tojson1 = (TextView) findViewById(R.id.main_tojson1);
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
        JsonUtil mJsonUtil = null;
        String error ="";
        try {
            mJsonUtil=new JsonUtil(json);
        } catch (JSONException e) {
            e.printStackTrace();
            error = "非json数据，解析数据失败";
        }
        Weatherinfo mWeatherinfo = null;
        if(mJsonUtil!=null) {
           mWeatherinfo = mJsonUtil.getT("weatherinfo", new Weatherinfo());
        }
        String tojson = "我是解析后的数据：";
        if(mWeatherinfo!=null) {
            tojson += "\n城市：" + mWeatherinfo.getCity();
            tojson += "\n城市ID：" + mWeatherinfo.getCityid();
            tojson += "\n风向：" + mWeatherinfo.getWd();
            tojson += "\n发布时间：" + mWeatherinfo.getTime();
        }else{
            tojson += "\n解析后的对象为null，请使用正常有值的weatherinfo对象的json数据来解析";
        }
        tojson(main_json1,json,error,tojson,main_tojson1);
    }

    private void tojson(TextView textView1,String json,String error,String tojson,TextView textView2) {
        //http://www.weather.com.cn/data/sk/101010100.html
        textView1.setText("我是解析前的数据：\n"+json);
        textView2.setText(error);
        textView2.setText(tojson);
    }
}
