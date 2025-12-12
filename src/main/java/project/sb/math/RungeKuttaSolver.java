package project.sb.math;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiFunction;

interface TriFunction<T>{
    T apply(T a, T b, T c);
}

public class RungeKuttaSolver {


    public static  HashMap<Integer, List<Double>> firstOrderDiffUsingRk2(double xnow,double ynow,double h,BiFunction<Double,Double,Double>func,int itr){
        //stores values  x and y for each iteration including initial ones
     HashMap<Integer,List<Double>> values=new LinkedHashMap<>(itr+1);
        for(int i=0;i<=itr;i++){
            //follows Heun's y n+1 formula
            double ynext=ynow+(h/2)*(func.apply(xnow,ynow)+func.apply(xnow+h,ynow+h*func.apply(xnow,ynow)));

            //stores current values x,y
            values.put(i,List.of(xnow,ynow));

            //update the values
            ynow=ynext;
            xnow=xnow+h;
        }
        //returns values x and y of each iteration including the initial values.
        return values;
    }
    public static  HashMap<Integer,List<Double>> secondOrderDiffUsingRk2(double xnow,double ynow,double znow,double h,TriFunction<Double>func,int itr){
        //stores values  x,y,z for each iteration including initial ones
        HashMap<Integer,List<Double>> values=new LinkedHashMap<>(itr+1);
        for(int i=0;i<=itr;i++){
            //follows Heun's z n+1 euler formula
            double znowEuler=znow+h*func.apply(xnow,ynow,znow);

            //follows Heun's y n+1 euler formula
            double ynowEuler=ynow+h*znow;

            //follows Heun's y n+1 formula
            double ynext=ynow+(h/2)*(znow+znowEuler);

            //follows Heun's  z n+1 formula
            double znext=znow+(h/2)*(func.apply(xnow,ynow,znow)+func.apply(xnow,ynowEuler,znowEuler));

            //stores current x,y,z
            values.put(i,List.of(xnow,ynow,znow));

            //update the values
            ynow=ynext;
            xnow=xnow+h;
            znow=znext;

        }
        return values;
    }
    public  static HashMap<Integer, List<Double>> firstOrderDiffUsingEuler(double xnow, double ynow, double h, BiFunction<Double,Double,Double>func, int itr){
        //stores values  x and y for each iteration including initial ones
        HashMap<Integer,List<Double>> values=new LinkedHashMap<>(itr+1);
        for(int i=0;i<=itr;i++){
            //follows euler y n+1 formula
            double ynext=ynow+h*func.apply(xnow,ynow);

            //stores current value of x and y
            values.put(i,List.of(xnow,ynow));

            //update the values
            ynow=ynext;
            xnow=xnow+h;
        }
        return values;
    }
    public static  HashMap<Integer, List<Double>> secondOrderDiffUsingEuler(double xnow,double ynow,double znow,double h,TriFunction<Double> func,int itr){
        //stores values  x,y,z  for each iteration including initial ones
        HashMap<Integer,List<Double>> values=new LinkedHashMap<>(itr+1);
        for(int i=0;i<=itr;i++){
            //follows euler z n+1 formula
           double znext=znow+h*func.apply(xnow,ynow,znow);

           //follows euler y n+1 formula
            double ynext=ynow+h*znow;

            //stores current value of x,y,z
            values.put(i,List.of(xnow,ynow,znow));

            //update the values
            ynow=ynext;
            xnow=xnow+h;
            znow=znext;
        }
        return values;
    }
    public static  HashMap<Integer, List<Double>> secondOrderDiffUsingRk4(double xnow,double ynow,double znow,double h,TriFunction<Double> func,int itr){
        //stores values  x,y,z  for each iteration including initial ones
        HashMap<Integer,List<Double>> values=new LinkedHashMap<>(itr+1);

        for(int i=0;i<=itr;i++){

            //stores current value of x,y,z
            values.put(i,List.of(xnow,ynow,znow));

            //find k1,l1,k2,l2,k3,l3,k4,l4 for necessary computation
           double kOne=h*znow;
           double lOne=h* func.apply(xnow,ynow,znow);
           double kTwo=h*(znow+lOne/2);
           double lTwo=h* func.apply(xnow+h/2,ynow+kOne/2,znow+lOne/2);
           double kThree=h* (znow+lTwo/2);
           double lThree=h* func.apply(xnow+h/2,ynow+kTwo/2,znow+lTwo/2);
           double kFour=h*(znow+lThree);
           double lFour=h*func.apply(xnow+h,ynow+kThree,znow+lThree);


         //follows rk4/classical method yn+1 and zn+1 formula
           double ynext=ynow+(1.0/6)*(kOne+(2*kTwo)+(2*kThree)+kFour);
           double znext=znow+(1.0/6)*(lOne+(2*lTwo)+(2*lThree)+lFour);

           //update the values
           ynow=ynext;
           znow=znext;
           xnow=xnow+h;
        }
        return values;
    }


}