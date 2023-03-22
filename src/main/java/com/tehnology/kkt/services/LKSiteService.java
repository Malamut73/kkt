package com.tehnology.kkt.services;

import com.tehnology.kkt.models.catalog.LKSite;
import com.tehnology.kkt.repositories.LKSiteDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LKSiteService {

    private final LKSiteDAO lkSiteDAO;

    public List<LKSite> findAll() {
        return lkSiteDAO.findAll();
    }

    public void save(LKSite lkSite) {
        lkSiteDAO.save(lkSite);
    }

    public void delete(LKSite lkSite) {
        lkSiteDAO.delete(lkSite);
    }
}
