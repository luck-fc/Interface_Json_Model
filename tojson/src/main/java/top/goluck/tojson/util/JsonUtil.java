package top.goluck.tojson.util;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import top.goluck.tojson.model.ListItem;

public class JsonUtil {

    private final static String tag = "tojson";
    private JSONObject object;

    /**
     * 用于内部jsonObject数据的解析
     * @param jsonObject 要解析的JSONObject
     */
    public JsonUtil(JSONObject jsonObject) {
        object = jsonObject;
    }

    /**
     * 用于内部jsonObject数据的解析
     * @param jsonObject 要解析的JSONObject
     */
    public JsonUtil(Object jsonObject) throws JSONException {
        if(jsonObject!=null)
        object =  new JSONObject(jsonObject.toString());
    }

    /**
     * 判断jsonData是否存在key的对象
     * @param key 需要判断的key
     * @return 是否存在
     */
    public static boolean hasKey(JSONObject jsonData,String key) {
        if (jsonData==null||!jsonData.has(key) || jsonData.equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取泛型对象( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @param key 解析获取的key
     * @param t 泛型
     * @param <T> 继承自ListItem的对象
     * @return 解析后的泛型对象
     */
    public static <T extends ListItem> T getT(JSONObject jsonData, String key, T t){
       if(!hasKey(jsonData,key)){
           return null;
       }
        T nt = t.newObject();
        try {
            if (jsonData.getJSONObject(key) == null) {
                return null;
            }
            nt.praseFromJson(new JsonUtil(jsonData.getJSONObject(key)));
        } catch (JSONException e) {
            e.printStackTrace();
            nt = null;
        }
        return nt;
    }

    /**
     * 获取泛型集合数据( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @param key 解析获取的key
     * @param t 泛型
     * @param <T> 继承自ListItem的对象
     * @return 解析后的List<T>数据
     */
    public static <T extends ListItem> List<T> getList(JSONObject jsonData, String key, T t) {
        if(!hasKey(jsonData,key)){
            return null;
        }
        try {
            JSONArray jsArr = jsonData.getJSONArray(key);
            if (jsArr == null || jsArr.length() == 0) {
                return null;
            }
            List<T> res = new ArrayList<T>();
            T nt = t;
            for (int i = 0; i < jsArr.length(); i++) {
                if (nt == null) {
                    nt = t.newObject();
                }
                nt.praseFromJson(new JsonUtil(jsArr.getJSONObject(i)));
                res.add(nt);
                nt = null;
            }
            return res;
        } catch (JSONException e) {
            Log.e(tag, e.getMessage());
        }
        return null;
    }

    /**
     * 获取泛型集合数据( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @param key 解析获取的key
     * @return 解析后的List<String>数据
     */
    public static List<String> getList(JSONObject jsonData,String key) {
        if(!hasKey(jsonData,key)){
            return null;
        }
        try {
            JSONArray jsArr = jsonData.getJSONArray(key);
            if (jsArr == null || jsArr.length() == 0) {
                return null;
            }
            List<String> res = new ArrayList<String>();
            for (int i = 0; i < jsArr.length(); i++) {
                res.add(jsArr.getString(i));
            }
            return res;
        } catch (JSONException e) {
            Log.e(tag, e.getMessage());
        }
        return null;
    }

    /**
     *  获取双集合String数据( 外部json数据专用 )
     *  @param jsonData 需要解析的JSONObject数据
     * @param key 解析获取的key
     * @return 解析后的List<List<String>>>数据
     */
    public static List<List<String>> getLists(JSONObject jsonData,String key) {
        if(!hasKey(jsonData,key)){
            return null;
        }
        try {
            JSONArray jsArr = jsonData.optJSONArray(key);
            if (jsArr == null || jsArr.length() == 0) {
                return null;
            }
            List<List<String>> res = new ArrayList<>();
            for (int i = 0; i < jsArr.length(); i++) {
                JSONArray sonArr = jsArr.getJSONArray(i);
                List<String> son = new ArrayList<String>();
                for (int j = 0; j < sonArr.length(); j++) {
                    son.add(sonArr.getString(j));
                }
                res.add(son);
            }
            return res;
        } catch (JSONException e) {
            Log.e(tag, e.getMessage());
        }
        return null;
    }

    /**
     * 将JSONArray直接转换为List<T>( 外部json数据专用 )
     * @param jsArr 需要解析的JSONArray数据
     * @param t 需要转换成的泛型对象
     * @param <T> 继承自ListItem的对象
     * @return 解析后的List<T>>数据
     */
    public static <T extends ListItem> List<T> getList(JSONArray jsArr, T t) {
        if (jsArr == null) {
            return null;
        }
        try {
            if (jsArr == null || jsArr.length() == 0) {
                return null;
            }
            List<T> res = new ArrayList<T>();
            T nt = t;
            for (int i = 0; i < jsArr.length(); i++) {
                if (nt == null) {
                    nt = t.newObject();
                }
                nt.praseFromJson(new JsonUtil(jsArr.getJSONObject(i)));
                res.add(nt);
                nt = null;
            }
            return res;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(tag, e.getMessage());
        }
        return null;
    }

    /**
     * 将JSONObject直接转换为Map<String, Object>( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @return 解析后的Map<String, Object>数据
     */
    public static Map<String, Object> getMap(JSONObject jsonData){
        return getMap(jsonData,null);
    }

    /**
     * 将JSONObject对应key的JSONObject直接转换为Map<String, Object>( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @param key 需要获取的key
     * @return 解析后的Map<String, Object>数据
     */
    public static Map<String, Object> getMap(JSONObject jsonData,String key) {
        if(!hasKey(jsonData,key)){
            return null;
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = TextUtils.isEmpty(key)?jsonData:jsonData.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Iterator<String> keyIter = jsonObject.keys();
        String itemkey;
        Object value = null;
        Map<String, Object> valueMap = new HashMap<String, Object>();
        while (keyIter.hasNext()) {
            itemkey = keyIter.next();
            try {
                value = jsonObject.get(itemkey);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            valueMap.put(itemkey, value);
        }
        return valueMap;
    }


    /**
     * 将JSONObject直接转换为Map<String, String>( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @return 解析后的Map<String, String>数据
     */
    public static Map<String, String> getMapString(JSONObject jsonData){
        return getMapString(jsonData,null);
    }

    /**
     * 将JSONObject对应key的JSONObject直接转换为Map<String, String>( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @param key 需要获取的key
     * @return 解析后的Map<String, String>数据
     */
    public static Map<String, String> getMapString(JSONObject jsonData,String key) {
        if(!hasKey(jsonData,key)){
            return null;
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = TextUtils.isEmpty(key)?jsonData:jsonData.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Iterator<String> keyIter = jsonObject.keys();
        String itemkey;
        String value = null;
        Map<String, String> valueMap = new HashMap<String, String>();
        while (keyIter.hasNext()) {
            itemkey = keyIter.next();
            try {
                value = (String) jsonObject.get(itemkey);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            valueMap.put(itemkey, value);
        }
        return valueMap;
    }

    /**
     * 获取JSONObject对应key的JSONObject对象( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @param key 需要获取的key
     * @return key值对应的JSONObject对象
     */
    public static JSONObject getJSONObject(JSONObject jsonData,String key) {
        if (!jsonData.has(key)) {
            return null;
        }
        try {
            return jsonData.getJSONObject(key);
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * 获取JSONObject对应key的JSONArray对象( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @param key 需要获取的key
     * @return key值对应的JSONArray对象
     */
    public static JSONArray getArray(JSONObject jsonData,String key) {
        if (!hasKey(jsonData,key)){
            return null;
        }
        try {
            return jsonData.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取JSONObject中为key值对应的Int数据( 外部json数据专用 )
     * @param jsonData 需要解析的JSONObject数据
     * @param key 需要获取的key
     * @return key值对应的int数据
     */
    public static int getInt(JSONObject jsonData,String key) {
        if (!hasKey(jsonData,key)){
            return 0;
        }
        try {
            return jsonData.getInt(key);
        } catch (JSONException e) {
            return 0;
        }
    }

    /**
     * 获取JSONObject中为key值对应的boolean数据 (外部json数据专用)
     * @param key 需要获取的key
     * @return key值对应的boolean数据
     */
    public static boolean getBoolean(JSONObject jsonData,String key) {
        if (!hasKey(jsonData,key)) {
            return false;
        }
        try {
            return jsonData.getBoolean(key);
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * 获取JSONObject中为key值对应的double数据(外部json数据专用)
     * @param key 需要获取的key
     * @return key值对应的double数据
     */
    public static double getDouble(JSONObject jsonData,String key)  {
        if (!hasKey(jsonData,key)) {
            return 0;
        }
        try {
            return jsonData.getDouble(key);
        } catch (JSONException e) {
            return 0;
        }
    }

    /**
     * 获取JSONObject中为key值对应的long数据(外部json数据专用)
     * @param key 需要获取的key
     * @return key值对应的long数据
     */
    public static long getLong(JSONObject jsonData,String key) {
        if (!hasKey(jsonData,key)) {
            return 0;
        }
        try {
            return jsonData.getLong(key);
        } catch (JSONException e) {
            return 0;
        }
    }

    /**
     * 获取JSONObject中为key值对应的String数据(外部json数据专用)
     * @param key 需要获取的key
     * @return key值对应的String数据
     */
    public static String getString(JSONObject jsonData,String key) {
        if (!hasKey(jsonData,key)) {
            return null;
        }
        try {
            return jsonData.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前JsonUtil中为key值对应的JSONObject对象
     * @param key 需要获取的key
     * @return key值对应的JSONObject对象
     */
    public JSONObject getJSONObject(String key) {
        return getJSONObject(object,key);
    }

    /**
     * 获取当前JsonUtil中为key值对应的JSONArray对象
     * @param key 需要获取的key
     * @return key值对应的JSONArray对象
     */
    public JSONArray getArray(String key) {
        return getArray(object,key);
    }

    /**
     * 获取当前JsonUtil中为key值对应的Int数据
     * @param key 需要获取的key
     * @return key值对应的int数据
     */
    public int getInt(String key) {
        return getInt(object,key);
    }


    /**
     * 获取当前JsonUtil中为key值对应的boolean数据
     * @param key 需要获取的key
     * @return key值对应的boolean数据
     */
    public boolean getBoolean(String key) {
        return getBoolean(key);
    }

    /**
     * 获取当前JsonUtil中为key值对应的double数据
     * @param key 需要获取的key
     * @return key值对应的double数据
     */
    public double getDouble(String key) {
        return getDouble(object,key);
    }

    /**
     * 获取当前JsonUtil中为key值对应的long数据
     * @param key 需要获取的key
     * @return key值对应的long数据
     */
    public long getLong(String key) {
        return getLong(object,key);
    }

    /**
     * 获取当前JsonUtil中为key值对应的String值
     * @param key 需要获取的key
     * @return  key值对应的String值
     */
    public String getString(String key) {
        return getString(object, key);
    }

    /**
     * 获取当前JsonUtil中为key值对应的 List<T> 值
     * @param key 需要获取的key
     * @param t 泛型
     * @param <T> 继承自ListItem的对象
     * @return key值对应的List<T>值
     */
    public <T extends ListItem> List<T> getList(String key, T t) {
        return getList(object, key, t);
    }

    /**
     * 获取当前JsonUtil中为key值对应的泛型集合数据
     * @param key 解析获取的key
     * @return 解析后的List<String>数据
     */
    public List<String> getList(String key) {
        return getList(object, key);
    }

    /**
     *   获取当前JsonUtil中为key值对应的双集合String数据
     * @param key 解析获取的key
     * @return 解析后的List<List<String>>>数据
     */
    public List<List<String>> getLists(String key) {
        return getLists(object, key);
    }

    /**
     * 获取当前JsonUtil中为key值对应的泛型对象
     * @param key 解析获取的key
     * @param t 泛型
     * @param <T> 继承自ListItem的对象
     * @return 解析后的泛型对象
     */
    public <T extends ListItem> T getT(String key, T t) {
        return getT(object, key, t);
    }

    /**
     * 获取当前JsonUtil中为key值对应的Map<String, Object>
     * @param key 需要获取的key
     * @return 解析后的Map<String, Object>数据
     */
    public Map<String, Object> getMap(String key) {
        return getMap(object,key);
    }

    /**
     * 获取当前JsonUtil中为key值对应的Map<String, String>
     * @param key 需要获取的key
     * @return 解析后的Map<String, String>数据
     */
    public Map<String, String> getMapString(String key) {
        return getMapString(object,key);
    }

    /**
     * 判断当前JsonUtil中是否存在key的对象
     * @param key 需要判断的key
     * @return 是否存在
     */
    public boolean hasKey(String key) {
        return hasKey(object,key);
    }
}
