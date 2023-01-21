package com.tehnology.kkt.services;

import com.tehnology.kkt.models.LK;
import com.tehnology.kkt.repositories.LKDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LKService {

    private final LKDAO lkdao;

    public void deleteLk(LK lk) {
        lkdao.delete(lk);
    }

    public LK findById(Long lkid) {
        return lkdao.getReferenceById(lkid);
    }

    public void deleteLKById(Long lkid) {
        lkdao.deleteById(lkid);
    }
}
