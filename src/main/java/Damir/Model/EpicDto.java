package Damir.Model;

import java.util.Date;
import java.util.Map;

public class EpicDto {
    private String identifier;
    private String caption;
    private String image;
    private String version;
    private Map<String,Double> centroid_coordinates;
    private Map<String,Double> dscovr_j2000_position;
    private Map<String,Double> lunar_j2000_position;
    private Map<String,Double> sun_j2000_position;
    private Map<String,Double> attitude_quaternions;
    private Date date;
    private Map<String,Map<String,Double>> coords;
   // private Map<String,Double> dscovr_j2000_position; //задвоенные данные
//    private Map<String,Double>
//    private String
//    private String
}
