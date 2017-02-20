package top.goluck.interface_json_mdoel;

import java.util.List;
import top.goluck.tojson.util.JsonUtil;
import top.goluck.tojson.model.ListItem;

public class DataInfoMdeol implements ListItem {

    private List<Data> mData;

    @Override
    public DataInfoMdeol newObject() {
        return new DataInfoMdeol();
    }

    @Override
    public void praseFromJson(JsonUtil jsonUtil) {
        setData(jsonUtil.getList("data",new Data()));
    }

    public void setData(List<Data> data) {
        this.mData = data;
    }

    public List<Data> getData() {
        return this.mData;
    }

}