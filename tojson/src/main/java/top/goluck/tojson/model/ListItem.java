package top.goluck.tojson.model;

import java.io.Serializable;

import top.goluck.tojson.util.JsonUtil;


public interface ListItem extends Serializable {
	<T extends ListItem> T newObject();
	void praseFromJson(JsonUtil jsonUtil);
}
