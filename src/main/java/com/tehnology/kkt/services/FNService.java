package com.tehnology.kkt.services;

import com.tehnology.kkt.models.FN;
import com.tehnology.kkt.repositories.FNDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<FN> findAllByOrderByDayEndDesc() {
        return fndao.findAllByOrderByDayEndDesc();
    }
}
