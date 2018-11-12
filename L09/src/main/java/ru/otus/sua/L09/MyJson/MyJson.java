package ru.otus.sua.L09.MyJson;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.lang.reflect.Field;
import java.util.Collection;

public class MyJson {

    public String toJson(String subject) {
        if (subject == null) return "null";
        return "\"" + subject + "\"";
    }

    public String toJson(Number subject) {
        if (subject == null) return "null";
        return subject.toString();
    }

    public String toJson(Character subject) {
        if (subject == null) return "null";
        return "\"" + subject.toString() + "\"";
    }

    public <T> String toJson(T[] subject) {
        if (subject == null) return "null";
        StringBuilder jsonArray = new StringBuilder("[");
        for (T t : subject) {
            jsonArray.append(this.toJson(t)).append(",");
        }
        jsonArray.deleteCharAt(jsonArray.lastIndexOf(","));
        jsonArray.append("]");
        return jsonArray.toString();
    }


    public String toJson(Object subject) {
        if (subject == null) return "null";

        StringBuilder jsonObject = new StringBuilder("{");

        for (Field field : subject.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            ParsedField f = new ParsedField(field, subject);
            if (f.isSkippable()) continue;
            if (f.isWrapperField() && f.isChar()) return this.toJson((Character) f.getValue());
            if (f.isWrapperField() && f.isNumber()) return this.toJson((Number) f.getValue());

            if (f.isString()) {
                add(jsonObject, f.getName(), this.toJson((String) f.getValue()));
            } else if (f.isArray()) {
                add(jsonObject, f.getName(), this.toJson(f.getValueAsArray()));
            } else if (f.isChar()) { // fix "cha":S issue
                add(jsonObject, f.getName(), this.toJson((Character) f.getValue()));
            } else if (f.isPrimitive()) {
                add(jsonObject, f.getName(), f.getValue());
            } else if (f.isCollection()) {
                JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder((Collection<?>) f.getValue());
                add(jsonObject, f.getName(), jsonArrayBuilder.build().toString());

                // no work as desired
//            } else if (f.isPrimitive()) {
//                add(jsonObject, f.getName(), f.getTypedValue(f.getType()));

                // this block covered by f.isPrimitive() // TODO for remove all
//            } else if (f.isBool()) {
//                add(jsonObject, f.getName(), (boolean) f.getValue());
//            } else if (f.isByte()) {
//                add(jsonObject, f.getName(), (byte) f.getValue());
//            } else if (f.isShort()) {
//                add(jsonObject, f.getName(), (short) f.getValue());
//            } else if (f.isInt()) {
//                add(jsonObject, f.getName(), (int) f.getValue());
//            } else if (f.isLong()) {
//                add(jsonObject, f.getName(), (long) f.getValue());
//            } else if (f.isFloat()) {
//                add(jsonObject, f.getName(), (float) f.getValue());
//            } else if (f.isDouble()) {
//                add(jsonObject, f.getName(), (double) f.getValue());

            } else {
                add(jsonObject, f.getName(), this.toJson(f.getValue()));
            }
            jsonObject.append(",");
        }
        jsonObject.deleteCharAt(jsonObject.lastIndexOf(","));
        jsonObject.append("}");
        return jsonObject.toString();
    }


    private <T> StringBuilder add(StringBuilder json, String name, T value) {
        return json.append("\"").append(name).append("\"").append(":").append(value);
    }


}
