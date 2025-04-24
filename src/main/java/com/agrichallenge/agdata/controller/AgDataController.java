package com.agrichallenge.agdata.controller;

import com.agrichallenge.agdata.model.AgData;
import com.agrichallenge.agdata.service.AgDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/agdata")
public class AgDataController {

    @Autowired
    private AgDataService agDataService;

    @GetMapping("")
    public ResponseEntity<List<AgData>> getAllProducts() throws IOException {
        List<AgData> products = agDataService.loadAgData();
        if (products.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // TODO: GET /api/agdata/crop-count?cropName=corn

    @GetMapping("")
    public ResponseEntity<Long> getCropCount()throws IOException{
        Long cropCount=agDataService.getCropCount(null);
        if(cropCount!=null){
    return new ResponseEntity<>(cropCount,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
}

    // TODO: GET /api/agdata/average-yield?cropName=wheat
    @GetMapping("/average-yield")
    public ResponseEntity<Double> getAverageYield(@RequestParam String cropName) {
        double avgYield = agDataService.getAverageYield(cropName);
        return new ResponseEntity<>(avgYield, HttpStatus.OK);
    }


    // TODO: GET /api/agdata/by-region?region=Midwest

    @GetMapping("/by-region")
    public ResponseEntity<List<AgData>> getByRegion(@RequestParam String region) {
        List<AgData> records = agDataService.getRecordsByRegion(region);
        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

}
