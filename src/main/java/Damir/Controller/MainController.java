package Damir.Controller;

import Damir.Service.NasaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api")
public class MainController {

    private NasaService nasaSerfice;

    public MainController(NasaService nasaSerfice) {
        this.nasaSerfice = nasaSerfice;
    }

    @GetMapping("/apod")
    public ResponseEntity<Map>getAPOD(){
        return nasaSerfice.getAPOD();
    }
}
