package xmlToJsonParser.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class PrettyJSONObject extends JSONObject {

	public String toString() {
		return this.toJSONString();
	}

	public String toJSONString() {
		String str = "";

		try {
			JSONWriter writer = new JSONWriter();

			super.writeJSONString(writer);
			str = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	@SuppressWarnings("unchecked")
	public Object put(Object key, Object value) {
		if (!super.containsKey(key)) { // �ߺ��� �ڽ� ��尡 �ƴ� ���
			return super.put(key, value);
		} else { // �ߺ��� �ڽ� ����� ���
			Object previousValue = super.get(key);
			JSONArray jsonArray = null;

			if (previousValue instanceof JSONArray) {
				jsonArray = (JSONArray) previousValue;
			} else {
				jsonArray = new JSONArray();
				jsonArray.add(previousValue);
			}

			if (value instanceof JSONArray) {
				jsonArray.addAll((JSONArray) value);
			} else {
				jsonArray.add(value);
			}

			return super.put(key, jsonArray);
		}
	}
}
