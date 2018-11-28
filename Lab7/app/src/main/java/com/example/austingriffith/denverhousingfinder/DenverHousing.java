package com.example.austingriffith.denverhousingfinder;

public class DenverHousing {

    private String housingArea;
    private String housingAreaURL;

    private void setHousingDetails(Integer housingInterest) {

        switch (housingInterest) {
            case 0: //Lodo in Denver
                housingArea = "Lodo, also know as Lower Downtown Denver";
                housingAreaURL = "https://www.lodo.org/";
                break;
            case 1:  //Highland in Denver
                housingArea = "Denver Highlands, still in the city but a bit more upscale";
                housingAreaURL = "https://www.visitdenverhighlands.com/";
                break;
            case 2:  //Rhino District in Denver know for arts and craft beer
                housingArea = "Rhino District, known for its arts and craft beer scene";
                housingAreaURL = "https://rinoartdistrict.org/";
                break;
            case 3:  //Capital Hill  more family oriented
                housingArea = "Capital Hill, great for families and bigger houses";
                housingAreaURL = "https://www.denver.org/about-denver/neighborhood-guides/capitol-hill-golden-triangle/";
                break;
            case 4:  // 16th st mall in the heart of the city
                housingArea = "16th Street Mall, located in the heart of the city";
                housingAreaURL = "https://www.denver.org/things-to-do/denver-attractions/16th-street-mall/";
                break;
            default:
                housingArea = "none selected";
                housingAreaURL = "https://www.google.com/search?q=Denver+housing&rlz=1C5CHFA_enUS768US770&oq=Denver+housing+&aqs=chrome..69i57j0l5.4535j1j4&sourceid=chrome&ie=UTF-8";
        }

    }

        public void setHousing (Integer housingInterest){
            setHousingDetails(housingInterest);
        }

        //seter method not used in this project but good practice to include
        public void setHousingURL (Integer housingInterest){
            setHousingDetails(housingInterest);
        }

        public String getHousing () {
            return housingArea;
        }

        public String getHousingURL () {
            return housingAreaURL;
        }


}       // end of class declaration