package json.models;

import com.google.gson.annotations.SerializedName;

public class Menu{

	@SerializedName("popup")
	private Popup popup;

	@SerializedName("id")
	private String id;

	@SerializedName("value")
	private String value;

	public void setPopup(Popup popup){
		this.popup = popup;
	}

	public Popup getPopup(){
		return popup;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"Menu{" + 
			"popup = '" + popup + '\'' + 
			",id = '" + id + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}