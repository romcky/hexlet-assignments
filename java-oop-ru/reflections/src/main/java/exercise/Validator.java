package exercise;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
// BEGIN
public class Validator {

	public static List<String> validate(Object obj){
		var results = new ArrayList<String>();
		var aClass = obj.getClass();
		for (var field : aClass.getDeclaredFields()) {
			field.setAccessible(true);
            try {
                if (field.getAnnotation(NotNull.class) != null && field.get(obj) == null) {
                    results.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
		return results;
	}
	
	public static Map<String, List<String>> advancedValidate(Object obj) {
		var results = new HashMap<String, List<String>>();
		var aClass = obj.getClass();
		for (var field : aClass.getDeclaredFields()) {
			var messages = new ArrayList<String>();
			field.setAccessible(true);
            try {
				var fieldNotNull = field.getAnnotation(NotNull.class);
				var fieldMinLength = field.getAnnotation(MinLength.class);
				var fieldValue = field.get(obj);
				if (fieldNotNull != null && fieldValue == null) {
					messages.add("can not be null");
				}
				if (fieldMinLength != null && fieldValue != null) {
					String str = (String) fieldValue;
					if (str.length() < fieldMinLength.minLength()) {
						messages.add("length less than " + fieldMinLength.minLength());
					}
				}
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

			if (messages.size() > 0) {
				results.put(field.getName(), messages);
			}
		}
		return results;
	}
}
// END
