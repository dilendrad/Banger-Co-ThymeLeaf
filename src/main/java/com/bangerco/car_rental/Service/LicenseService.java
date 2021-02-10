package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.Entity.License;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LicenseService implements LicenseInterface {

    String line = "";

    @Override
    public List<License> validateLicenseData() {

        try {

            List<License> list = new ArrayList<>();

            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Dilendra De Silva\\Desktop\\APIIT\\3rd Year\\EIRLSS\\Assignment (ThymeLeaf)\\src\\main\\resources\\data.csv"));
            while ((line = bufferedReader.readLine()) != null) {

                String [] data = line.split(",");
                License license = new License();
                license.setName(data[0]);
                license.setLicenseNo(data[1]);
                license.setStatus(data[2]);
                list.add(license);

            }

            return list;
        }

        catch (IOException e) {

            e.printStackTrace();
        }
        return null;

    }



}
