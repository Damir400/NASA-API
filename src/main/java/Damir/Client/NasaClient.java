package Damir.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

//Клиент для получения данных NASA

@FeignClient(name = "jsonimg", url = "${nasa.url}")
public interface NasaClient {

    @GetMapping( "/planetary/apod")
    ResponseEntity<Map> getAPOD(
            @RequestParam("api_key") String nasaKey
    );

//    @GetMapping("/historical/{date}.json")
//    ResponseEntity<Map> getHistoricalRates(
//            @PathVariable(value = "date") String date,
//            @RequestParam("nasa.key") String nasaKey
//    );
}

