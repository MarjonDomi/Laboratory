package com.example.laboratory.repository;

import com.example.laboratory.entity.Analysis;
import com.example.laboratory.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnalisysRepository extends JpaRepository<Analysis,Long> {
    List<Analysis> findAllByUser(Users userAuthenticated);
    List<Analysis> findAnalysisByAnalysisType(Analysis.AnalysisType analysisType);
    List<Analysis> findAnalysisByDescription(String description);
}
