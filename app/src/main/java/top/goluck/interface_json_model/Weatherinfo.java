package top.goluck.interface_json_model;

import top.goluck.tojson.util.JsonUtil;
import top.goluck.tojson.model.ListItem;

public class Weatherinfo implements ListItem {

    private String mCity;
    private String mWd;
    private String mWse;
    private String mTemp;
    private String mNjd;
    private String mQy;
    private String mIsradar;
    private String mRain;
    private String mCityid;
    private String mRadar;
    private String mWs;
    private String mTime;
    private String mSd;

    @Override
    public Weatherinfo newObject() {
        return new Weatherinfo();
    }

    @Override
    public void praseFromJson(JsonUtil jsonUtil) {
        setCity(jsonUtil.getString("city"));
        setWd(jsonUtil.getString("WD"));
        setWse(jsonUtil.getString("WSE"));
        setTemp(jsonUtil.getString("temp"));
        setNjd(jsonUtil.getString("njd"));
        setQy(jsonUtil.getString("qy"));
        setIsradar(jsonUtil.getString("isRadar"));
        setRain(jsonUtil.getString("rain"));
        setCityid(jsonUtil.getString("cityid"));
        setRadar(jsonUtil.getString("Radar"));
        setWs(jsonUtil.getString("WS"));
        setTime(jsonUtil.getString("time"));
        setSd(jsonUtil.getString("SD"));
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getCity() {
        return this.mCity;
    }

    public void setWd(String wd) {
        this.mWd = wd;
    }

    public String getWd() {
        return this.mWd;
    }

    public void setWse(String wse) {
        this.mWse = wse;
    }

    public String getWse() {
        return this.mWse;
    }

    public void setTemp(String temp) {
        this.mTemp = temp;
    }

    public String getTemp() {
        return this.mTemp;
    }

    public void setNjd(String njd) {
        this.mNjd = njd;
    }

    public String getNjd() {
        return this.mNjd;
    }

    public void setQy(String qy) {
        this.mQy = qy;
    }

    public String getQy() {
        return this.mQy;
    }

    public void setIsradar(String isradar) {
        this.mIsradar = isradar;
    }

    public String getIsradar() {
        return this.mIsradar;
    }

    public void setRain(String rain) {
        this.mRain = rain;
    }

    public String getRain() {
        return this.mRain;
    }

    public void setCityid(String cityid) {
        this.mCityid = cityid;
    }

    public String getCityid() {
        return this.mCityid;
    }

    public void setRadar(String radar) {
        this.mRadar = radar;
    }

    public String getRadar() {
        return this.mRadar;
    }

    public void setWs(String ws) {
        this.mWs = ws;
    }

    public String getWs() {
        return this.mWs;
    }

    public void setTime(String time) {
        this.mTime = time;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setSd(String sd) {
        this.mSd = sd;
    }

    public String getSd() {
        return this.mSd;
    }

}