package Damir.Service;

import Damir.Client.DateClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
    public class NasaServiceTest {
        @Autowired
        private NasaService nasaService;
        @MockBean
        private DateClient dateClient;


    @Test
    public void getDate() {
        List<String> testEntity = new ArrayList<>();
        testEntity.add("2022-01-15");
        testEntity.add("2022-02-15");
        testEntity.add("2022-03-15");
        testEntity.add("2022-04-15");
        testEntity.add("2022-05-15");
        testEntity.add("2022-06-15");
        when(dateClient.getDate()).thenReturn(testEntity);
        List<String> result = nasaService.getDate();
        assertEquals(testEntity,result);

    }

    @Test
    public void getEPIC() {
        List<Map> item1 = new ArrayList<>();
        ResponseEntity<List<Map>> test = mock(ResponseEntity.class);
        when(test.getBody()).thenReturn(item1);
        when(dateClient.getEPIC(anyString())).thenReturn(test);
        ResponseEntity<List<Map>> result = nasaService.getEPIC(anyString());
        assertEquals(test,result);
    }
}