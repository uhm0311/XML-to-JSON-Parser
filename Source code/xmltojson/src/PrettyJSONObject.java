import org.json.simple.JSONObject;


@SuppressWarnings("serial")
public class PrettyJSONObject extends JSONObject {

	public String toString()
	{
		return this.toJSONString();
	}
	public String toJSONString()
	{
		String str = "";
		try
		{
			JSONWriter writer = new JSONWriter();
			
			super.writeJSONString(writer);
			str = writer.toString();
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		}
		
		return str;
	}
}
