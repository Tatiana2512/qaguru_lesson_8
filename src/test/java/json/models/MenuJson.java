package json.models;

import com.google.gson.annotations.SerializedName;

public class MenuJson{

	@SerializedName("menu")
	private Menu menu;

	public void setMenu(Menu menu){
		this.menu = menu;
	}

	public Menu getMenu(){
		return menu;
	}

	@Override
 	public String toString(){
		return 
			"MenuJson{" + 
			"menu = '" + menu + '\'' + 
			"}";
		}
}