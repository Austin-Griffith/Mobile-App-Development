package com.example.austingriffith.finalexam;

public class PizzaActivity {

    private String sauce;
    private String size ;

    public void setSauceInfo(Integer pizzaSauce){
        switch (pizzaSauce){
            case 0: //red sauce
                sauce="red sauce";
                break;
            case 1: //white sauce
                sauce="white sauce";
                break;
            case 2: //BBQ sauce
                sauce="BBQ sauce";
                break;
            case 3: //buffalo
                sauce="buffalo sauce";
                break;
            default:
                sauce="none";

        }
    }


    public void setSizeInfo(Integer pizzaSize){
        switch (pizzaSize){
            case 0: //small
                size="small";
                break;
            case 1: //white sauce
                size="medium";
                break;
            case 2: //BBQ sauce
                size="large";
                break;
            case 3: //buffalo
                size="extra large";
                break;
            default:
                size="none";

        }
    }



    public void setPizzaSauce(Integer sauce){

        setPizzaSauce(sauce);
    }

    public void setPizzaSize(Integer size){

        setPizzaSize(size);
    }

    public String getPizzaSauce(){

        return sauce;
    }

    public String getPizzaSize(){

        return size;
    }


}
