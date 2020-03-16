package com.practicas.exercise1;

import com.practicas.exercise1.service.CarService;

public class Exercise1 {

    public static void main(String[] args){

        System.out.println("Exercise 1");
        
        System.out.println("Exercise 1.1");
        
        System.out.println("RESULT 1.1 " +  CarService.getMarcaModelo(25, 33));

        System.out.println("Exercise 1.2");
        
        
        System.out.println("RESULT  1.2 - 1" +  CarService.getMarcaModeloGTHorsePower(10, 150));
        System.out.println("RESULT  1.2 - 2" +  CarService.getMarcaModeloGTHorsePower2(10, 150));
        System.out.println("RESULT  1.2 - 3" +  CarService.getMarcaModeloGTHorsePower2(10, 150));
        
        System.out.println("Exercise 1 END");
        
        
        
        
    }
}
