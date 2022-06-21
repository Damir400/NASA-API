package Damir.Service;

import Damir.Client.DateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NasaService {

    private DateClient dateClient;
    @Value("${api.key}")
    private String nasaKey;
    @Value("${epic.url}")
    private String epicUrl;

    @Autowired
    public NasaService(DateClient dateClient) {
        this.dateClient = dateClient;
    }

    public List<String> getDate(){                                  // получение дат, по которым есть данные
        List<String> result = new ArrayList<>(dateClient.getDate());
        return result;
    }

    public ResponseEntity<List<Map>> getEPIC(String date){                          // EPIC = Earth Polychromatic Imaging Camera
        ResponseEntity<List<Map>> result = dateClient.getEPIC(date);                //Из полученного ответа вытаскиваем все доступные названия картинок
        List<Map> responseBody=result.getBody();                                    //после чего собираем составные ссылки для получения картинок
        for( int i=0; i<responseBody.size(); i++){
         StringBuilder stringBuilder = new StringBuilder(epicUrl);
         stringBuilder.append(date.replace('-','/'));
         stringBuilder.append("/png/"+ responseBody.get(i).get("image")+ ".png");
         stringBuilder.append("?api_key=" + nasaKey);
        responseBody.get(i).put("url",stringBuilder);
        }
        return result;

    }
}
