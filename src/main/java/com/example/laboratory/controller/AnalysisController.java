package com.example.laboratory.controller;

import com.example.laboratory.entity.Analysis;
import com.example.laboratory.service.AnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/a")
//@PreAuthorize("hasRole('ADMIN')")
public class AnalysisController {

    @Autowired
    private AnalysisServiceImpl analysisService;

    @GetMapping("/allanalysis")
    public List<Analysis> getAllAnalysis(){
        return analysisService.findAll();
    }

    @GetMapping("/analysis/{id}")
    public Analysis getAnalysisId(@PathVariable(value = "id") long id) {
        return analysisService.getId(id);
    }

    @PostMapping("/addnewanalysis")
    public Analysis createAnalysis(@RequestBody Analysis analysis){
//        Analysis.setUser(User.getUser());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return analysisService.saveAnalysis(analysis,authentication.getName());
    }

    @PutMapping("/updateanalysis/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public Analysis updateAnalysis(@PathVariable (value="id")long id,@RequestBody Analysis analysis){
        Analysis updanalysis = analysisService.getId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        updanalysis.setDescription(analysis.getDescription());
//        updanalysis.setAnalysisType(analysis.getAnalysisType());
//        updanalysis.setDateOfAnalysis(analysis.getDateOfAnalysis());
        return analysisService.saveAnalysis(updanalysis,authentication.getName());
    }

    @DeleteMapping("/deleteanalysis/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable (value="id")long id){
       Analysis analysis = analysisService.getId(id);
        analysisService.deleteAnalysis(analysis);
    }
    @GetMapping("/findanalysisbydescription")
    public List<Analysis> findAnalysisByDescription(@RequestParam String description) {
        return analysisService.findAnalysisByDescription(description);
    }

}
