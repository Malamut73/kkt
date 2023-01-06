package com.tehnology.kkt.services;

import com.tehnology.kkt.models.extraclasses.FN;
import com.tehnology.kkt.repositories.FNDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FNService {

    private final FNDAO fndao;

    public FN findById(Long fnid) {
        return fndao.getReferenceById(fnid);
    }

    public void save(FN fn) {
        fndao.save(fn);
    }

    public void deleteById(Long fnid) {
        fndao.deleteById(fnid);
    }
}
