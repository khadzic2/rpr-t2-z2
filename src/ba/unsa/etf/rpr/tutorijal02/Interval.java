package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    double pocetnaTacka;
    double krajnjaTacka;
    boolean pripadaPocetna;
    boolean pripadaKrajnja;
    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pripadaPocetna, boolean pripadaKrajnja){
        if(pocetnaTacka > krajnjaTacka)
            throw new IllegalArgumentException("Pocetna tacka je veca od krajnje tacke");
        this.pocetnaTacka = pocetnaTacka;
        this.krajnjaTacka = krajnjaTacka;
        this.pripadaPocetna = pripadaPocetna;
        this.pripadaKrajnja = pripadaKrajnja;
    }
    public Interval(){
        pocetnaTacka = 0;
        krajnjaTacka = 0;
        pripadaPocetna = false;
        pripadaKrajnja = false;
    }
    public boolean isNull(){
        return pocetnaTacka == 0 && krajnjaTacka == 0 && !pripadaPocetna && !pripadaKrajnja;
    }

    public boolean isIn(double tacka){
        if(tacka < krajnjaTacka && tacka > pocetnaTacka) return true;
        if(pripadaKrajnja && tacka == krajnjaTacka) return  true;
        return pripadaPocetna && tacka == pocetnaTacka;
    }

    public Interval intersect(Interval i1){
         Interval i = new Interval();
         if(krajnjaTacka < i1.pocetnaTacka) return  i;
         if(krajnjaTacka == i1.pocetnaTacka && pripadaKrajnja && i1.pripadaPocetna){
             i.pocetnaTacka = i.krajnjaTacka = pocetnaTacka;
             i.pripadaPocetna = i.pripadaKrajnja = true;
         }
         if(pocetnaTacka == i1.pocetnaTacka){
             i.pocetnaTacka = pocetnaTacka;
             if(pripadaPocetna && i1.pripadaPocetna) i.pripadaPocetna = true;
         }else if(pocetnaTacka < i1.pocetnaTacka){
             i.pocetnaTacka = i1.pocetnaTacka;
             if(i1.pripadaPocetna) i.pripadaPocetna = true;
         }else{
             i.pocetnaTacka = pocetnaTacka;
             if(pripadaPocetna) i.pripadaPocetna = true;
         }
        if(krajnjaTacka == i1.krajnjaTacka){
            i.krajnjaTacka = krajnjaTacka;
            if(pripadaKrajnja && i1.pripadaKrajnja) i.pripadaKrajnja = true;
        }else if(krajnjaTacka < i1.krajnjaTacka){
            i.krajnjaTacka = krajnjaTacka;
            if(pripadaKrajnja) i.pripadaKrajnja = true;
        }else{
            i.krajnjaTacka = i1.krajnjaTacka;
            if(i1.pripadaKrajnja) i.pripadaKrajnja = true;
        }
        return  i;
    }
    static Interval intersect(Interval i1, Interval i2){
        return i1.intersect(i2);
    }
    @Override
    public String toString(){
        if(pocetnaTacka == 0 && krajnjaTacka == 0)
            return "()";
        if(pripadaPocetna){
            if(pripadaKrajnja)
                return  "[" + pocetnaTacka + "," + krajnjaTacka + "]";
            else
                return  "[" + pocetnaTacka + "," + krajnjaTacka + ")";
        }
        else{
            if(pripadaKrajnja)
                return  "(" + pocetnaTacka + "," + krajnjaTacka + "]";
            else
                return  "(" + pocetnaTacka + "," + krajnjaTacka + ")";
        }
    }
    @Override
    public boolean equals(Object o){
        Interval i = (Interval) o;
        return  pocetnaTacka == i.pocetnaTacka && krajnjaTacka == i.krajnjaTacka && pripadaPocetna == i.pripadaPocetna && pripadaKrajnja == i.pripadaKrajnja;
    }

}
