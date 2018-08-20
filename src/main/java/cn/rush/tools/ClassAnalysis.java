package cn.rush.tools;

import cn.rush.annotation.Explanation;
import cn.rush.exception.ClassParseException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassAnalysis {

    public static List<String> getDescs(Object o) {
        if (o == null) return null;
        Class c = o.getClass();
        List<String> result = new ArrayList<String>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Explanation.class)) {
                Explanation explanation = field.getAnnotation(Explanation.class);
                result.add(explanation.desc());
            }
        }
        return result;
    }

    public static List<String> getFieldsValue(Object o) {
        if (o == null) return null;
        Class c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        List<String> result = new ArrayList<String>();
        for (Field field : fields) {
            try {
                if (field.isAnnotationPresent(Explanation.class)) {
                    result.add(parse(o, field.getName()));
                }
            } catch (Exception e) {
                throw new ClassParseException("class parse error", e);
            }
        }
        return result;
    }

    public static String parse(Object o, String fieldName)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        Class c = o.getClass();
        Field field = c.getDeclaredField(fieldName);
        Method fieldMethod = c.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);
        Object result = fieldMethod.invoke(o, null);
        if (field.isAnnotationPresent(Explanation.class)) {
            Explanation explanation = field.getAnnotation(Explanation.class);
            result = getExplanation(o,result, explanation);
        }
        return result == null ? "" : result.toString();
    }

    public static Object getExplanation(Object parent, Object o, Explanation explanation)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        String methodName = explanation.method();
        if (methodName.length() > 0) {
            String className = methodName.substring(0, methodName.lastIndexOf("#"));
            Class cl = Class.forName(className);
            methodName = methodName.substring(methodName.lastIndexOf("#") + 1);
            String[] params = explanation.param();
            String[] newParam = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                String param = params[i];
                if (param.charAt(0) == '$') {
                    className = param.substring(2, param.lastIndexOf("#"));
                    String paramValue = param.substring(param.lastIndexOf("#") + 1, param.length() - 1);
                    cl = Class.forName(className);
                    Field field = cl.getDeclaredField(paramValue);
                    newParam[i] = String.valueOf(field.get(cl));
                }else if(param.charAt(0) == '#'){
                    Class c = parent.getClass();
                    String fieldName = param.substring(2,param.length()-1);
                    Method fieldMethod = c.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);
                    newParam[i] =  String.valueOf(fieldMethod.invoke(parent, null));
                } else {
                    newParam[i] = param;
                }
            }
            Method method = cl.getDeclaredMethod(methodName, o.getClass(), newParam.getClass());
            return method.invoke(null, o, newParam);
        }
        return o;
    }

    public static void main(String[] args) throws Exception {
        Class cl = Class.forName("cn.rush.tools.NumberUtils");
        Method method = cl.getDeclaredMethod("round", Double.class, int.class);
        System.out.println(method.invoke(null, 2.1314, 2));
    }

}
