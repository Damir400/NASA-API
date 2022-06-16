package Damir.Service;

// APOD - астрономическая карта дня

import Damir.Client.DateClient;
import Damir.Client.NasaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NasaService {

    private NasaClient nasaClient;
    private DateClient dateClient;
    @Value("${api.key}")
    private String nasaKey;

    @Autowired
    public NasaService(NasaClient nasaClient, DateClient dateClient) {
        this.nasaClient = nasaClient;
        this.dateClient = dateClient;
    }

    public ResponseEntity<Map> getAPOD(){                           // APOD = Astronomy Picture of the Day
        ResponseEntity<Map> result = nasaClient.getAPOD(nasaKey);
        return result;
    }

    public List<String> getDate(){                                  // получение дат, по которым есть данные
        List<String> result = new ArrayList<>(dateClient.getDate());
        return result;
    }

//    public ResponseEntity<Map> getEPIC(String date){                           // EPIC = Earth Polychromatic Imaging Camera
//        ResponseEntity<Map> result = dateClient.getEPIC(date);
//        return result;
//    }

    public ResponseEntity<List<Map>> getEPIC(String date){                           // EPIC = Earth Polychromatic Imaging Camera
        ResponseEntity<List<Map>> result = dateClient.getEPIC(date);
        List<Map> responseBody=result.getBody();
        for( int i=0; i<responseBody.size(); i++){
            // https://api.nasa.gov/EPIC/archive/enhanced/2022/06/12/png/epic_RGB_20220612010436.png?api_key=DEMO_KEY
            // https://api.nasa.gov/EPIC/archive/enhanced/2022/06/06/png/epic_RGB_20220612010436.png?api_key=DEMO_KEY

         StringBuilder stringBuilder = new StringBuilder("https://api.nasa.gov/EPIC/archive/enhanced/");
         stringBuilder.append(date.replace('-','/'));
         stringBuilder.append("/png/"+ responseBody.get(i).get("image")+ ".png");
         stringBuilder.append("?api_key=XvASfaPmdD3X98CazMKJpgeKBSsdZVpRF8PxIhE9");
        responseBody.get(i).put("url",stringBuilder);
        }
        return result;
    }

//
//    public ResponseEntity<Map> getMarsPhotos(){                           // Mars Rover Photos
//        ResponseEntity<Map> result = nasaClient.getMarsPhotos(nasaKey);
//
//        return result;
//    }


}
