package base.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
*@author chedhena Jayasinghe
*util class for  reflection methods

**/
public class ReflectionUtil {
    
	/**
	*Gives all fields of a class including inherited fields
	@param class of the object
	**/
	public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        
        return fields;
    }
	
    public static <o> String[] getReportFields(o object,List<Field> fields){
    	List<String> headarray=new ArrayList<String>();
		for (Field field : fields) {
			headarray.add(field.getName().toString());
		}	
		String[] reportFields = headarray.toArray(new String[headarray.size()]);
	
		return reportFields;
	}
    
    public static <o> List<Object> getValues(o object,List<Field> fields) throws Exception{
		List<Object> objValues=new ArrayList<Object>();
		for (Field field : fields) {
			field.setAccessible(true);
			objValues.add(field.get(object));
		}
		
		return objValues;
	}
    
    public static <o> Object getValueOfAField(o object,String fieldName) throws Exception{
		Object objValues=new Object();
		Field field=object.getClass().getField(fieldName);
		field.setAccessible(true);
		objValues=field.get(object);	
		
		return objValues;
	}
}
