package com.example.laboratory.service;

import com.example.laboratory.entity.Analysis;
import com.example.laboratory.entity.Users;
import com.example.laboratory.repository.AnalisysRepository;
import com.example.laboratory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class AnalysisServiceImpl implements AnalysisService{

    @Autowired
    private AnalisysRepository analisysRepository;
    @Autowired
    private UserRepository userRepository;

    @Transient
    @Transactional
    public Analysis saveAnalysis(Analysis analysis,String userName) {
        Optional<Users> user = userRepository.findByUsername(userName);
        analysis.setUser(user.get());
        return analisysRepository.saveAndFlush(analysis) ;
    }

    @Override
    public List<Analysis> findAnalysisByAnalysisType(Analysis.AnalysisType analysisType) {
        return analisysRepository.findAnalysisByAnalysisType(analysisType);
    }

    @Override
    public List<Analysis> findAll() {
        return analisysRepository.findAll();
    }

    @Override
    public Analysis getId(long id) {
        return analisysRepository.getById(id);
    }

    @Override
    public void deleteAnalysis(Analysis analysis) {
     analisysRepository.delete(analysis);
    }

    @Override
    public List<Analysis> findAllByUser(Users userAuthenticated) {
        return analisysRepository.findAllByUser(userAuthenticated);
    }

   @Override
    public List<Analysis> findAnalysisByDescription(String description) {
        return analisysRepository.findAnalysisByDescription(description);
    }


}
