package enums;

import java.util.ArrayList;
import java.util.List;

public enum Countries {
    TURKEY(new ArrayList<>() {{
        add("English");
        add("Türkçe");
    }}),
    UNITED_STATES(new ArrayList<>() {{
        add("English");
        add("Español");
    }});
    //todo all language should added
    private final List<String> values;

    Countries(List<String> values) {
        this.values = values;
    }

    public List<String> languages(){
        return values;
    }

}
