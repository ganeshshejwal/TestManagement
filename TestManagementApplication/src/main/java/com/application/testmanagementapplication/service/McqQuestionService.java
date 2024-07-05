package com.application.testmanagementapplication.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.testmanagementapplication.model.McqQuestion;
import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.respository.McqQuestionRepository;
import com.application.testmanagementapplication.respository.SubCategoryRepository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class McqQuestionService {

    @Autowired
    private McqQuestionRepository mcqQuestionRepository;

    @Autowired
    private SubCategoryRepository  subCategoryRepository;
    
    public List<McqQuestion> saveMcqQuestion(MultipartFile file) {
        List<McqQuestion> questionBank = new ArrayList<>();
        
        try (InputStream inputStream = file.getInputStream();
            Workbook workbook=new XSSFWorkbook(inputStream)){
            Sheet sheet=workbook.getSheetAt(0);

            for (int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    McqQuestion multipleChoiceQuestion = new McqQuestion();

                    for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        switch (j) {
                            case 2:
                                String categoryName = cell.toString();
                                Optional<SubCategory> subCategory = subCategoryRepository.findBysubCategoryName(categoryName);
                                subCategory.ifPresent(multipleChoiceQuestion::setSubCategory);
                                break;
                            case 3:
                                multipleChoiceQuestion.setQuestion(cell.toString());
                                break;
                            case 4:
                                multipleChoiceQuestion.setOptionOne(cell.toString());
                                break;
                            case 5:
                                multipleChoiceQuestion.setOptionTwo(cell.toString());
                                break;
                            case 6:
                                multipleChoiceQuestion.setOptionThree(cell.toString());
                                break;
                            case 7:
                                multipleChoiceQuestion.setOptionFour(cell.toString());
                                break;
                            case 8:
                                multipleChoiceQuestion.setCorrectOption(cell.toString());
                                break;
                            case 9:
                                multipleChoiceQuestion.setPositiveMark(cell.toString());
                                break;
                            case 10:
                                multipleChoiceQuestion.setNegativeMark(cell.toString());
                                break;
                            default:
                                break;
                        }
                    }
                    McqQuestion savedQuestion = mcqQuestionRepository.save(multipleChoiceQuestion);
                    questionBank.add(savedQuestion);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return questionBank;
    }

    public List<McqQuestion> getAllMcqQuestions() {
        return mcqQuestionRepository.findAll();
    }

    public Optional<McqQuestion> getMcqQuestionById(int id) {
        return mcqQuestionRepository.findById(id);
    }

    public McqQuestion updateMcqQuestion(int id,McqQuestion mcqQuestion) {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);
        if(subCategory.isPresent()) mcqQuestion.setSubCategory(subCategory.get());
        return mcqQuestionRepository.save(mcqQuestion);
    }

    public void deleteMcqQuestion(int id) {
        mcqQuestionRepository.deleteById(id);
    }
}
