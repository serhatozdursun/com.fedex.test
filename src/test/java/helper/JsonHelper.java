package helper;


import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import static com.jayway.jsonpath.JsonPath.read;
import static com.jayway.jsonpath.JsonPath.using;


public class JsonHelper {


    protected DocumentContext getJsonDocumentContext(String json) {
        Configuration configuration = Configuration.builder()
                .jsonProvider(new JacksonJsonNodeJsonProvider())
                .mappingProvider(new JacksonMappingProvider())
                .build();
        return using(configuration).parse(json);
    }

    public String getJsonValueAsString(String json, String jsonKey) {
        return read(json, jsonKey);
    }





}
