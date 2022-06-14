package Damir.Controller;

import Damir.Service.NasaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class MainController {

    private NasaService nasaService;

    public MainController(NasaService nasaSerfice) {
        this.nasaService = nasaSerfice;
    }

    @GetMapping("/apod")
    public ResponseEntity<Map>getAPOD(){
        return nasaService.getAPOD();
    }

    @GetMapping("/getdate")
    public List<String> getDate(){
        return nasaService.getDate();
    }

    @GetMapping("/epic/{date}")
    public ResponseEntity<Map>getEPIC(@PathVariable(value = "date") String date){
        return nasaService.getEPIC(date);
    }

}
