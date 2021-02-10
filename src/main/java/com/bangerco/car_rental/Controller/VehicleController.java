package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.Entity.Vehicle;
import com.bangerco.car_rental.Service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    @Value("${uploadDirectory}")
    private String uploadFolder;

    @Autowired
    private VehicleService vehicleService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = {"/", "home"})
    public String addVehiclePage() {

        return "AddVehicle";
    }

    @PostMapping("/saveVehicleDetails")
    public @ResponseBody
    ResponseEntity<?> createVehicleProduct(@RequestParam("brand") String brand,
                                           @RequestParam("model") String model,
                                           @RequestParam("categoryType") String categoryType,
                                           @RequestParam("transmissionType") String transmissionType,
                                           @RequestParam("fuelType") String fuelType,
                                           @RequestParam("airConditioning") String airConditioning,
                                           @RequestParam("doors") int doors,
                                           @RequestParam("seats") int seats,
                                           @RequestParam("categoryPrice") double categoryPrice,
                                           Model modelName,
                                           HttpServletRequest httpServletRequest,
                                           final @RequestParam("image") MultipartFile multipartFile) {
        try {
            String uploadToDirectory = httpServletRequest.getServletContext().getRealPath(uploadFolder);
            logger.info("uploadToDirectory : " + uploadToDirectory);
            String fileName = multipartFile.getOriginalFilename();
            String filePath = Paths.get(uploadToDirectory, fileName).toString();
            logger.info("File Name : " + multipartFile.getOriginalFilename());

            if (fileName == null || fileName.contains("..")) {
                modelName.addAttribute("Invalid!", "Sorry, this filename includes an invalid path sequence! \" +fileName");
                return new ResponseEntity<>("Sorry, this filename includes an invalid path sequence!" + fileName, HttpStatus.BAD_REQUEST);
            }

            String[] brands = brand.split(",");
            String[] models = model.split(",");
            String[] categoryTypes = categoryType.split(",");
            String[] transmissionTypes = transmissionType.split(",");
            String[] fuelTypes = fuelType.split(",");
            String[] airConditions = airConditioning.split(",");
            logger.info("Brand : " + brands[0] + " " + filePath);
            logger.info(("Model : " + models[0]));
            logger.info("Category Type : " + categoryTypes[0]);
            logger.info("Transmission Type : " + transmissionTypes);
            logger.info("Fuel Type : " + fuelTypes[0]);
            logger.info("Air Conditioning : " + airConditions[0]);
            logger.info("Category Price : " + categoryPrice);
            logger.info("Doors : " + doors);
            logger.info("Seats : " + seats);

            try {
                File directory = new File(uploadToDirectory);

                if (!directory.exists()) {
                    logger.info("Folder Created");
                    directory.mkdirs();
                }

                BufferedOutputStream bufferedOutputStream =
                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                bufferedOutputStream.write(multipartFile.getBytes());
                bufferedOutputStream.close();
            } catch (Exception e) {
                logger.info("Error in Catch");
                e.printStackTrace();
            }

            byte[] vehicleImageData = multipartFile.getBytes();
            Vehicle optionalVehicle  = new Vehicle();
            optionalVehicle .setVehicleBrand(models[0]);
            optionalVehicle .setVehicleModel(brands[0]);
            optionalVehicle .setCategoryType(categoryTypes[0]);
            optionalVehicle .setTransmissionType(transmissionTypes[0]);
            optionalVehicle .setFuelType(fuelTypes[0]);
            optionalVehicle .setAirConditioning(airConditions[0]);
            optionalVehicle .setDoors(doors);
            optionalVehicle .setSeats(seats);
            optionalVehicle .setCategoryPrice(categoryPrice);
            optionalVehicle .setImage(vehicleImageData);
            vehicleService.saveVehicleImage(optionalVehicle );

            logger.info("HttpStatus === " + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Car has been saved with the File!" + fileName, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception : " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showCarImage(@PathVariable("id") Long id, HttpServletResponse httpServletResponse,
                      Optional<Vehicle> optionalVehicle) throws ServletException, IOException {
        logger.info("Car ID : " + id);
        optionalVehicle = vehicleService.getVehicleImageByID(id);
        httpServletResponse.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        httpServletResponse.getOutputStream().write(optionalVehicle.get().getImage());
        httpServletResponse.getOutputStream().close();
    }

    @GetMapping("/vehicleImageDetails")
    String showCarDetails(@RequestParam("id") Long id,
                          Optional<Vehicle> optionalVehicle,
                          Model modelName) {
        try {
            logger.info("Car ID : " + id);

            if(id != 0) {
                optionalVehicle = vehicleService.getVehicleImageByID(id);

                logger.info("car :: " + optionalVehicle);

                if(optionalVehicle.isPresent()) {
                    modelName.addAttribute("id", optionalVehicle.get().getId());
                    modelName.addAttribute("brand", optionalVehicle.get().getVehicleBrand());
                    modelName.addAttribute("model", optionalVehicle.get().getVehicleModel());
                    modelName.addAttribute("categoryType", optionalVehicle.get().getCategoryType());
                    modelName.addAttribute("transmissionType", optionalVehicle.get().getTransmissionType());
                    modelName.addAttribute("fuelType", optionalVehicle.get().getFuelType());
                    modelName.addAttribute("airConditioning", optionalVehicle.get().getAirConditioning());
                    modelName.addAttribute("doors", optionalVehicle.get().getDoors());
                    modelName.addAttribute("seats", optionalVehicle.get().getSeats());
                    modelName.addAttribute("categoryPrice", optionalVehicle.get().getCategoryPrice());
                    return "VehicleDetails";
                }

                return "redirect:/admin/loadAdminHome";
            }

            return "redirect:/admin/loadAdminHome";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/loadAdminHome";
        }
    }

    @GetMapping("/show")
    String show(Model modelName) {
        List<Vehicle> images = vehicleService.getAllActiveVehicleImages();
        modelName.addAttribute("images", images);
        return "ViewVehicles";
    }

    @GetMapping("/viewSmallTownCars")
    String viewSmallTownCars(Model model) {
        List<Vehicle> smallTownCars = vehicleService.getAllSmallTownCars();
        model.addAttribute("smallTownCars", smallTownCars);
        return "ViewSmallTownCars";
    }

    @GetMapping("/viewVehiclesAdmin")
    String viewVehiclesAdmin(Model modelName) {
        List<Vehicle> adminImages = vehicleService.getAllActiveVehicleImages();
        modelName.addAttribute("adminImages", adminImages);
        return "ViewVehiclesAdmin";
    }


}
