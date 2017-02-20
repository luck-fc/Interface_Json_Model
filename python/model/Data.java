package top.goluck.interface_json_mdoel;

import top.goluck.tojson.util.JsonUtil;
import top.goluck.tojson.model.ListItem;

public class Data implements ListItem {

    private String mCity;
    private String mMessage;

    @Override
    public Data newObject() {
        return new Data();
    }

    @Override
    public void praseFromJson(JsonUtil jsonUtil) {
        setCity(jsonUtil.getString("city"));
        setMessage(jsonUtil.getString("message"));
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getCity() {
        return this.mCity;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public String getMessage() {
        return this.mMessage;
    }

}