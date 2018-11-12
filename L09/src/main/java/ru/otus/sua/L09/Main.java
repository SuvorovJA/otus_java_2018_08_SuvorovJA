package ru.otus.sua.L09;

import com.google.gson.Gson;
import ru.otus.sua.L09.MyJson.MyJson;
import ru.otus.sua.L09.forTest.ComplexObjects;
import ru.otus.sua.L09.forTest.SimpleObjects;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Gson g = new Gson();
        MyJson m = new MyJson();

        ComplexObjects tComplexObject = new ComplexObjects();
        tComplexObject.setName("inside name");
        tComplexObject.setArray(new int[]{2, 5, 7, 0, 9});
        tComplexObject.setBoxedLong(11_111_111_111L);
        tComplexObject.setPrimiLong(22_222_222_222L);
        tComplexObject.setDoubleList(Arrays.asList(2.1, 2d, 3.3, 7.2));
        tComplexObject.setSimpleObject(new SimpleObjects(true, 'S', 1));
        print("ComplexObjects", g.toJson(tComplexObject), m.toJson(tComplexObject));


        SimpleObjects simple1 = new SimpleObjects(false, 'A', 1);
        SimpleObjects simple2 = new SimpleObjects(true, 'r', 2);
        SimpleObjects simple3 = new SimpleObjects(false, 'r', 3);
        SimpleObjects simple4 = new SimpleObjects(true, 'a', 4);
        SimpleObjects simple5 = new SimpleObjects(true, 'y', 5);
        SimpleObjects[] simpleObjects = new SimpleObjects[]{simple1, simple2, simple3, simple4, simple5};
        print("Array of SimpleObjects", g.toJson(simpleObjects), m.toJson(simpleObjects));

        Object tNull = null;
        print("null", g.toJson(tNull), m.toJson(tNull));

        int tInt = 123;
        print("int", g.toJson(tInt), m.toJson(tInt));

        double tDouble = 123.00001;
        print("double", g.toJson(tDouble), m.toJson(tDouble));

        char tChar = 123;
        print("char", g.toJson(tChar), m.toJson(tChar));

        Integer tInteger = 123123;
        print("Integer", g.toJson(tInteger), m.toJson(tInteger));

        Object tObjInteger = 1231234;
        print("Integer inside Object", g.toJson(tObjInteger), m.toJson(tObjInteger));

        String tString = "string";
        print("String", g.toJson(tString), m.toJson(tString));


    }

    private static void print(String type, String toJsonGson, String toJsonMy) {
        System.out.println("  Test content: \"" + type + "\"\n");
        System.out.println("Reference Json: " + toJsonGson + "\n");
        System.out.println("       My Json: " + toJsonMy);

        for (int i = 0; i < 100; i++) {
            System.out.print('-');
        }
        System.out.println("\n");
    }

}
