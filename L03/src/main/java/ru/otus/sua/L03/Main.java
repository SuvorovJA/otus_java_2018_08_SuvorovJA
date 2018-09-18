package ru.otus.sua.L03;

import java.util.*;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        List<Integer> jli = new ArrayList<>();
        jli.add(1333);
        jli.add(1444);
        jli.add(1555);
        jli.add(2333);
        jli.add(2444);
        jli.add(2555);

        List<Integer> mli = new MyArrayList<>();
        mli.add(3333);
        mli.add(3444);
        mli.add(3555);

        List<String> mls = new MyArrayList<>();
        mls.add("s933");
        mls.add("s144");
        mls.add("s655");

        out.println("  ArrayList<Integer> jli: " + jli.toString());
        out.println("MyArrayList<Integer> mli: " + mli.toString());
//        out.println("MyArrayList<String>  mls: " + mls.toString());

        mli.clear();
        out.println("mli.clear():      " + mli.toString());
        mli.addAll(jli);
        out.println("mli.addAll(jli):      " + mli.toString());
        out.println("mli.indexOf(1555):    " + mli.indexOf(1555));
        out.println("mli.subList(2,4):     " + mli.subList(2, 4).toString());
        mli.remove((Integer) 1555);
        out.println("mli.remove(1555):     " + mli.toString());
        out.println("mli.containsAll(jli): " + mli.containsAll(jli));
        mli.removeAll(jli);
        out.println("mli.removeAll(jli):   " + mli.toString());
        mli.addAll(Arrays.asList(3, 5, 7, 9, 0, 1555, 0, 1444, 1444, 9, 4));
        out.println("mli.addAll(Arrays.asList()): " + mli.toString());
        mli.retainAll(jli);
        out.println("mli.retainAll(jli);   " + mli); // without toString()

        out.print("\n\nCollections test\n\n");
        // The destination list's size must be greater than or equal to the source list's size.
        List<String> mls2 = Stream.generate(String::new).limit(10).collect(toList());
        out.println("MyArrayList<String>  mls:     " + mls);
        out.println("MyArrayList<String> mls2:     " + mls2 + " mls2.size()=" + mls2.size());
        Collections.addAll(mls, "x-one", "b-two");
        out.println("Collections.addAll(mls, \"x-one\",\"b-two\"): " + mls);
        Collections.copy(mls2, mls);
        out.println("Collections.copy(mls2, mls):  " + mls2);
        Collections.sort(mls2);
        out.println("Collections.sort(mls2):       " + mls2);


        out.print("\n\nIterator remove() test\n\n");
        out.println("MyArrayList<String>  mls:     " + mls);
		Iterator<String> mlsi = mls.iterator();
		while (mlsi.hasNext()) {
		    String s = mlsi.next();
			out.print("item="+s);
			if (s.equals("s655")){
			    out.print(" removed");
			    mlsi.remove();
            }
            out.println();
		}
        out.println("MyArrayList<String>  mls:     " + mls);

        out.print("\n\n.forEach() test: ");
        mls.forEach((s) -> {
            out.print(s + ",");
        });
    }

}
