package top.goluck.interface_json_mdoel;

import top.goluck.tojson.util.JsonUtil;
import top.goluck.tojson.model.ListItem;

public class WeatherInfoMdeol implements ListItem {

    private Weatherinfo mWeatherinfo;

    @Override
    public WeatherInfoMdeol newObject() {
        return new WeatherInfoMdeol();
    }

    @Override
    public void praseFromJson(JsonUtil jsonUtil) {
        setWeatherinfo(jsonUtil.getT("weatherinfo",new Weatherinfo()));
    }

    public void setWeatherinfo(Weatherinfo weatherinfo) {
        this.mWeatherinfo = weatherinfo;
    }

    public Weatherinfo getWeatherinfo() {
        return this.mWeatherinfo;
    }

}