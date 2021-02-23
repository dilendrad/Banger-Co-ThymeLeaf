package com.bangerco.car_rental.Service;

import com.bangerco.car_rental.Entity.Document;
import com.bangerco.car_rental.Entity.Renter;
import com.bangerco.car_rental.Repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService implements DocumentServiceInterface{

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    RenterService renterService;

    public Document saveDocument(MultipartFile file, Renter renter) {

        String documentName = file.getOriginalFilename();

        try {

            Document document = new Document(documentName, file.getContentType(), file.getBytes());
            document.setRenter(renterService.getRenterByID(renter.getRenterID()));
            return  documentRepository.save(document);
        }

        catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public Optional<Document> getFile(Integer fileId) {
        return documentRepository.findById(fileId);
    }

    public List<Document> getFiles() {

        return documentRepository.findAll();
    }

}
