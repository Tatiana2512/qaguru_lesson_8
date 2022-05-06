package json.models;

import com.google.gson.annotations.SerializedName;

public class MenuitemItem {

    @SerializedName("onclick")
    private String onclick;

    @SerializedName("value")
    private String value;

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return
                "MenuitemItem{" +
                        "onclick = '" + onclick + '\'' +
                        ",value = '" + value + '\'' +
                        "}";
    }
}