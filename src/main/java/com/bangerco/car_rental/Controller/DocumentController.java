package com.bangerco.car_rental.Controller;

import com.bangerco.car_rental.DTO.DocumentReg;
import com.bangerco.car_rental.DTO.RenterReg;
import com.bangerco.car_rental.Entity.Document;
import com.bangerco.car_rental.Entity.Renter;
import com.bangerco.car_rental.Service.DocumentService;
import com.bangerco.car_rental.Service.RenterService;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    RenterService renterService;

    @GetMapping("/viewDocuments")
    public String viewDocuments(Model model) {

        List<Document> documentList = documentService.getFiles();
        model.addAttribute("documents", documentList);
        return "Documents";
    }

    @PostMapping("/uploadDocuments")
    public String uploadDocuments(@RequestParam("files")MultipartFile[] files, @ModelAttribute("renter")Renter renter) {

        for(MultipartFile file: files) {

            documentService.saveDocument(file, renter);
        }

        return "redirect:/document/viewDocuments";
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
        Document document = documentService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getDocumentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+document.getDocumentName()+"\"")
                .body(new ByteArrayResource(document.getData()));
    }

    @GetMapping("/addDocument/{id}")
    public String addDocument(@PathVariable(value = "id") int renterID, Model model) {

        Renter renter = renterService.getRenterByID(renterID);
        DocumentReg documentReg = new DocumentReg();
        documentReg.setRenter(renter);

        model.addAttribute("document", documentReg);
        model.addAttribute("renter", renter);
        return "AddDocument";

    }
}
