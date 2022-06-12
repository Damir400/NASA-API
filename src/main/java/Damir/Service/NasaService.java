package Damir.Service;

// APOD - астрономическая карта дня

import Damir.Client.NasaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NasaService {

    private NasaClient nasaClient;
    @Value("${api.key}")
    private String nasaKey;

    @Autowired
    public NasaService(NasaClient nasaClient) {
        this.nasaClient = nasaClient;
    }

    public ResponseEntity<Map> getAPOD(){
        ResponseEntity<Map> result = nasaClient.getAPOD(nasaKey);

        return result;
    }


}
