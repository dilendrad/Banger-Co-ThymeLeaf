package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.Entity.WebScraper;
import com.bangerco.car_rental.Service.VehicleService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/webscraper")
public class WebScraperController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/webScrape")
    public String webScraping(Model model){

        List<WebScraper> webScrapedVehicleList = new ArrayList<>();

        final String url = "https://www.malkey.lk/rates/self-drive-rates.html";

        try {
            final Document document = Jsoup.connect(url).get();

            for(Element row: document.select("table.table.selfdriverates tr")) {

                WebScraper vehicle = new WebScraper();

                final String vehicleName= row.select("td.text-left.percent-40").text();
                if(!vehicleName.contentEquals("")) {
                    vehicle.setVehicleName(vehicleName);
                }
                final String rates = row.select("td.text-center.percent-22").text();
                if(!rates.contentEquals("")) {

                    String[] priceList = rates.split(" ");

                    vehicle.setPricePerMonth(priceList[0]);
                    vehicle.setPricePerWeek(priceList[1]);
                    vehicle.setMileagePerDay(priceList[2]);

                }

                if(vehicle.getVehicleName()!=null) {
                    webScrapedVehicleList.add(vehicle);
                }

            }

        }catch(Exception e) {
            e.getMessage();
        }


        model.addAttribute("scrape", webScrapedVehicleList);
        return "WebScraper";
    }
}
