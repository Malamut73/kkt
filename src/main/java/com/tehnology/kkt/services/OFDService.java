package com.tehnology.kkt.services;

import com.tehnology.kkt.models.OFD;
import com.tehnology.kkt.repositories.OFDDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OFDService {

    private final OFDDAO ofddao;

    public List<OFD> findAll() {
        return ofddao.findAll();
    }


    public void deleteOFDById(Long ofdid) {
        ofddao.deleteById(ofdid);
    }

    public OFD findById(Long ofdid) {
        return ofddao.getReferenceById(ofdid);
    }

    public void saveOFD(OFD ofd) {
        ofddao.save(ofd);
    }

    public void deletOFD(OFD ofd) {
        ofddao.delete(ofd);
    }

    public List<OFD> findAllByOrderByDayEndDesc() {
        return ofddao.findAllByOrderByDayEndDesc();
    }
}
