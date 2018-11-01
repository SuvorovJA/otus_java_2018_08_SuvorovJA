package ru.otus.sua.L07.atm.staff;

public enum Nominal {
    N5000(Multivalute.RUR, 5000),
    N2000(Multivalute.RUR, 2000),
    N1000(Multivalute.RUR, 1000),
    N500(Multivalute.RUR, 500),
    N200(Multivalute.RUR, 200),
    N100(Multivalute.RUR, 100),
    N50(Multivalute.RUR, 50),
    N10(Multivalute.RUR, 10);

    private long nominal;
    private Multivalute valute;

    Nominal(Multivalute valute, long nominal) {
        this.nominal = nominal;
        this.valute = valute;
    }

    public long getNominal() {
        return nominal;
    }

    public Multivalute getValute() {
        return valute;
    }
}
