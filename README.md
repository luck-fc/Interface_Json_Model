[![Build Status](https://travis-ci.org/luck-fc/Interface_Json_Model.svg?branch=master)](https://travis-ci.org/luck-fc/Interface_Json_Model)
[![](https://jitpack.io/v/luck-fc/Interface_Json_Model.svg)](https://jitpack.io/#luck-fc/Interface_Json_Model)

# Interface_Json_Mdoel
Interface to Json to Mdoel

* python  直接访问接口生成下列封装工具需要的实体类  （python2）
* android 利用系统自带的org.json解析json为java对象  (即：解析json的封装工具 tojson ）

## Android引入library 
root build.gradle加入
```xml
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```
项目 build.gradle加入
```xml
    compile 'com.github.luck-fc:Interface_Json_Model:tag1.0'
```
## 简要使用示例，详细请参考项目
~~~java
//这里的json是JSONObject、JSONArray 或 String(需要捕获异常) 类型。   Weatherinfo是json转换为示例java对象
JsonUtil mJsonUtil = null;
try {
    mJsonUtil=new JsonUtil(json);
} catch (JSONException e) {
    e.printStackTrace();
    //"非json数据，解析数据失败";
}
if(mJsonUtil!=null) {
    Weatherinfo mWeatherinfo1 = mJsonUtil.getT(new Weatherinfo());//无key对象获取 json必须是可转为无key的JSONObject
    Weatherinfo mWeatherinfo2 = mJsonUtil.getT("weatherinfo", new Weatherinfo());//有key对象获取 json必须是可转有key的JSONObject
    List<Weatherinfo> mWeatherinfos1 = mJsonUtil.getList(new Weatherinfo());//无key集合获取  json必须是可转无key的JSONArray
    List<Weatherinfo> mWeatherinfos2 = mJsonUtil.getList("weatherinfos",new Weatherinfo());//有key集合获取  json必须是可转有key的JSONArray
}
~~~

开发者 (Developer)
----------------

* luck-fc - <xiaoorchao@gmail.com>

**License**
=======

    Copyright 2017 luck-fc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
