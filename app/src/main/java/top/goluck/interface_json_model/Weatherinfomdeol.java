package top.goluck.interface_json_model;

import top.goluck.tojson.util.JsonUtil;
import top.goluck.tojson.model.ListItem;

public class Weatherinfomdeol implements ListItem {

    private Weatherinfo mWeatherinfo;

    @Override
    public Weatherinfomdeol newObject() {
        return new Weatherinfomdeol();
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