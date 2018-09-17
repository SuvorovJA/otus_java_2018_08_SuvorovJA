package ru.otus.sua.L02;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class AgentTest {
    public static void main(String[] args) {
//        System.out.println("All sizes in Bytes");
//        System.out.println();
//
//
//        System.out.println("Primitives(autoboxing):");
//        printObjectSize("  long", 1L);
//        printObjectSize("   int", 1);
//        printObjectSize(" short", (short) 1);
//        printObjectSize("  byte", (byte) 1);
//        printObjectSize("  bool", true);
//        System.out.println();
//
//        System.out.println("Emptyes:");
//        printObjectSize(" new Object()", new Object());
        printObjectSize(" pool-string", "");
//        printObjectSize(" obj-string", new String(""));
//        printObjectSize(" new BigDecimal(0)", new BigDecimal(0));
//        ArrayList<String> als = new ArrayList<>();
//        printObjectSize(" new ArrayList<String>()", als);
//        printObjectSize(" new Byte[0]", new Byte[0]);
//        printObjectSize(" new Integer[0]", new Integer[0]);
//        printObjectSize(" new Long[0]", new Long[0]);
//        System.out.println();
//
//        System.out.println("Filled strings and collections:");
//        printObjectSize(" pool-string \"string\"", "string");
//        printObjectSize(" obj-string \"string\"", new String("string"));
//        printObjectSize(" pool-string \"string string string string\"", "string string string string");
//        printObjectSize(" obj-string \"string string string string\"", new String("string string string string"));
//        printObjectSize(" new BigDecimal(\"999999999999999.999\")", new BigDecimal("999999999999999.999"));
//        als.add("1");
//        printObjectSize(" new ArrayList<String>(\"1\")", als);
//        als.add("2");
//        printObjectSize(" new ArrayList<String>(\"1\",\"2\")", als);
//        als.add("3");
//        printObjectSize(" new ArrayList<String>(\"1\",\"2\",\"3\")", als);
//        als.add("44444444444444444");
//        printObjectSize(" new ArrayList<String>(\"1\",\"2\",\"3\",\"44444444444444444\")", als);
//
//        Byte[] b110 = new Byte[110];
//        byte b = 1;
//        Arrays.fill(b110, b);
//        printObjectSize(" new Byte[110]", b110);
//
//        Character[] c110 = new Character[110];
//        Arrays.fill(c110,'c');
//        printObjectSize(" new Character[110]", c110);
//
//        Integer[] i110 = new Integer[110];
//        // Integer.hashCode() == Value
//        for ( int i=0; i<110; i++) {
//            i110[i] = i*i;
//        }
//        printObjectSize(" new Integer[110]", i110);
//
//        Long[] l110 = new Long[110];
//        Arrays.fill(l110,1L);
//        printObjectSize(" new Long[110]", l110);
//
//        System.out.println();
//
//        System.out.println("Custom complex object:");
//        printObjectSize(" new CustomClass()", new CustomClass());
//        printObjectSize(" new CustomClass(1000,2000,\"Obj Name Obj Name\")", new CustomClass(1000, 2000, "Obj Name Obj Name"));
//        printObjectSize(" new CustomClass(1000,2000,\"Obj Name Obj Name Obj Name Obj Name Obj Name Obj Name\")", new CustomClass(1000, 2000, "Obj Name Obj Name Obj Name Obj Name Obj Name Obj Name"));
//        System.out.println();

    }

    public static void printObjectSize(String comment, Object obj) {
        System.out.println(String.format(
                "%s: type=%s, plainSize=%s, recursiveSize=%s",
                comment, obj.getClass().getSimpleName(), AgentForMem.sizeOf(obj), AgentForMem.getObjSize(obj)
                )
        );
    }


}

class CustomClass {
    private Integer id;
    private int iid;
    private String name;

    public CustomClass() {
    }

    public CustomClass(Integer id, int iid, String name) {
        this.id = id;
        this.iid = iid;
        this.name = name;
    }
}