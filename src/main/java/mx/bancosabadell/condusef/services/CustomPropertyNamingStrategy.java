package mx.bancosabadell.condusef.services;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class CustomPropertyNamingStrategy extends PropertyNamingStrategy {

    //Clase personalizada para definirl la forma que se serializa a json

    @Override
    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
        return customName(defaultName);
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return customName(defaultName);
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return customName(defaultName);
    }

    private String customName(String input) {
        if ("product".equals(input)) {
            return input; 
        }
        return capitalize(input);
    }

    private String capitalize(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

}
