package com.idm3.CRUDExample.service;

import com.idm3.CRUDExample.model.Catalogue;
import com.idm3.CRUDExample.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogueService {

    @Autowired
    private CatalogueRepository catalogueRepository;

    public List<Catalogue> findAllCatalogues() {
        return catalogueRepository.findAll();
    }

    public Optional<Catalogue> findCatalogueById(Long id) {
        return catalogueRepository.findById(id);
    }
}
