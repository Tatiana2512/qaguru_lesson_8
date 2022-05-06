package json.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Popup{

	@SerializedName("menuitem")
	private List<MenuitemItem> menuitem;

	public void setMenuitem(List<MenuitemItem> menuitem){
		this.menuitem = menuitem;
	}

	public List<MenuitemItem> getMenuitem(){
		return menuitem;
	}

	@Override
 	public String toString(){
		return 
			"Popup{" + 
			"menuitem = '" + menuitem + '\'' + 
			"}";
		}
}