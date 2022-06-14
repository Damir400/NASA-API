package Damir.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "jsondate", url = "${nasa.date.url}")
public interface DateClient {

    @GetMapping( "/api/images.php?available_dates")
    List<String> getDate();


    @GetMapping( "/api/enhanced/date/{date}")  //
    ResponseEntity<Map> getEPIC(
            @PathVariable(value = "date") String date
    );
}
