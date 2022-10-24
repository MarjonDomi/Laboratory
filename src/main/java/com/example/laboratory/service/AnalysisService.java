package com.example.laboratory.service;

import com.example.laboratory.entity.Analysis;
import com.example.laboratory.entity.SubCategory;
import com.example.laboratory.entity.Users;

import java.util.List;
import java.util.Optional;

public interface AnalysisService {
    Analysis saveAnalysis(Analysis analysis, String user);
    List<Analysis> findAnalysisByAnalysisType(Analysis.AnalysisType analysisType);
    List<Analysis> findAll();
    Analysis getId(long id);
    void deleteAnalysis(Analysis analysis);
    List<Analysis> findAllByUser(Users userAuthenticated);
    List<Analysis> findAnalysisByDescription(String description);
}
