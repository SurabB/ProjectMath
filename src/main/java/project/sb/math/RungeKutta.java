package project.sb.math;

import java.util.function.BiFunction;

public class RungeKutta {


    public static  double[] solve1stODErk2(double xnow,double ynow,double h,BiFunction<Double,Double,Double>func,int itr){
        for(int i=0;i<=itr;i++){
            //follows Heun's y n+1 formula
            double ynext=ynow+(h/2)*(func.apply(xnow,ynow)+func.apply(xnow+h,ynow+h*func.apply(xnow,ynow)));
            //update the values
            ynow=ynext;
            xnow=xnow+h;
        }
        return new double[]{xnow,ynow};
    }
    public static double[] solve2ndODErk2(double xnow,double ynow,double znow,double h,TriFunction<Double>func,int itr){

        for(int i=0;i<=itr;i++){
            //follows Heun's z n+1 euler formula
            double znowEuler=znow+h*func.apply(xnow,ynow,znow);

            //follows Heun's y n+1 euler formula
            double ynowEuler=ynow+h*znow;

            //follows Heun's y n+1 formula
            double ynext=ynow+(h/2)*(znow+znowEuler);

            //follows Heun's  z n+1 formula
            double znext=znow+(h/2)*(func.apply(xnow,ynow,znow)+func.apply(xnow,ynowEuler,znowEuler));


            //update the values
            ynow=ynext;
            xnow=xnow+h;
            znow=znext;

        }
            return new double[]{xnow,ynow,znow};
    }
    public  static double[] solve1stODeEuler(double xnow, double ynow, double h, BiFunction<Double,Double,Double>func, int itr){

        for(int i=0;i<=itr;i++){
            //follows euler y n+1 formula
            double ynext=ynow+h*func.apply(xnow,ynow);

            //update the values
            ynow=ynext;
            xnow=xnow+h;
        }
        return new double[]{xnow,ynow};
    }
    public static double[] solve2ndODeEuler(double xnow,double ynow,double znow,double h,TriFunction<Double> func,int itr){

        for(int i=0;i<=itr;i++){
            //follows euler z n+1 formula
           double znext=znow+h*func.apply(xnow,ynow,znow);

           //follows euler y n+1 formula
            double ynext=ynow+h*znow;


            //update the values
            ynow=ynext;
            xnow=xnow+h;
            znow=znext;
        }
        return new double[]{xnow,ynow,znow};

    }
    public static double[] solve1stODErk4(double xnow,double ynow,double h,BiFunction<Double,Double,Double> func,int itr){

        for(int i=0;i<=itr;i++){


            //find k1,k2,k3,k4 for necessary computation
            double kOne=h*func.apply(xnow,ynow);
            double kTwo=h* func.apply(xnow+h/2,ynow+kOne/2);
            double kThree=h* func.apply(xnow+h/2,ynow+kTwo/2);
            double kFour=h*func.apply(xnow+h,ynow+kThree);


            //follows rk4/classical method yn+1  formula
            double ynext=ynow+(1.0/6)*(kOne+(2*kTwo)+(2*kThree)+kFour);

            //update the values
            ynow=ynext;
            xnow=xnow+h;
        }
        return new double[]{xnow,ynow};
    }
    public static  double[] solve2ndODErk4(double xnow,double ynow,double znow,double h,TriFunction<Double> func,int itr){

        for(int i=0;i<=itr;i++){

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
        return new double[]{xnow,ynow,znow};
    }

}