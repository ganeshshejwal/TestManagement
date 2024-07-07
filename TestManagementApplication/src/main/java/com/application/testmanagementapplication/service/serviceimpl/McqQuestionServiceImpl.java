package com.application.testmanagementapplication.service.serviceimpl;

import org.slf4j.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.testmanagementapplication.exception.DataAlreadyExistsException;
import com.application.testmanagementapplication.exception.DataNotFoundException;
import com.application.testmanagementapplication.exception.FileProcessingException;
import com.application.testmanagementapplication.model.McqQuestion;
import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.respository.McqQuestionRepository;
import com.application.testmanagementapplication.respository.SubCategoryRepository;
import com.application.testmanagementapplication.service.McqQuestionService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class McqQuestionServiceImpl implements McqQuestionService {

    private static final Logger logger = LoggerFactory.getLogger(McqQuestionServiceImpl.class);

    @Autowired
    private McqQuestionRepository mcqQuestionRepository;

    @Autowired
    private SubCategoryRepository  subCategoryRepository;
    
    public List<McqQuestion> createMcqQuestion(MultipartFile file) {
        logger.info("Request for creating McqQuestion");
        List<McqQuestion> questions = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook=new XSSFWorkbook(inputStream)
            ){
            Sheet sheet = workbook.getSheetAt(0);
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            for (int i = firstRowNum+1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    McqQuestion mcqQuestion = new McqQuestion();
                    for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        switch (j) {
                            case 2:
                                String subCategoryName = cell.toString();
                                Optional<SubCategory> subCategory = subCategoryRepository.findBysubCategoryName(subCategoryName);
                                if (!subCategory.isPresent()) {
                                    logger.error("SubCategory is not found");
                                    throw new DataNotFoundException("SubCategory is not found.");
                                }
                                mcqQuestion.setSubCategory(subCategory.get());
                                break;
                            case 3:
                                mcqQuestion.setQuestion(cell.toString());
                                break;
                            case 4:
                                mcqQuestion.setOptionOne(cell.toString());
                                break;
                            case 5:
                                mcqQuestion.setOptionTwo(cell.toString());
                                break;
                            case 6:
                                mcqQuestion.setOptionThree(cell.toString());
                                break;
                            case 7:
                                mcqQuestion.setOptionFour(cell.toString());
                                break;
                            case 8:
                                mcqQuestion.setCorrectOption(cell.toString());
                                break;
                            case 9:
                                mcqQuestion.setPositiveMark(cell.toString());
                                break;
                            case 10:
                                mcqQuestion.setNegativeMark(cell.toString());
                                break;
                            default:
                                break;
                        }
                    }

                    Optional<McqQuestion> createdMcqQuestion = mcqQuestionRepository.findByquestion(mcqQuestion.getQuestion());
                    if (createdMcqQuestion.isPresent()) {
                        logger.error("McqQuestion is already present");
                        throw new DataAlreadyExistsException("McqQuestion is already present.");
                    }
                    McqQuestion savedQuestion = mcqQuestionRepository.save(mcqQuestion);
                    questions.add(savedQuestion);
                }
            }
        }
        
        catch(Exception e){
            logger.error("Error occurred while processing file for creating McqQuestions");
            throw new FileProcessingException("Error occurred while processing file for creating McqQuestions");
        }
        logger.info("McqQuestion created successfully");
        return questions;
    }

    public List<McqQuestion> getAllMcqQuestions() {
        logger.info("Request for fetching all McqQuestions");
        List<McqQuestion> mcqQuestions = mcqQuestionRepository.findAll();
        if (mcqQuestions.isEmpty()) {
            logger.error("No McqQuestions found");
            throw new DataNotFoundException("No McqQuestions found.");
        }
        logger.info("McqQuestions fetched successfully");
        return mcqQuestions;
    }

    public Optional<McqQuestion> getMcqQuestionById(int id) {
        logger.info("Request for fetching McqQuestion using McqQuestion Id");
        Optional<McqQuestion> mcqQuestion = mcqQuestionRepository.findById(id);
        if (!mcqQuestion.isPresent()) {
            logger.error("McqQuestion is not found");
            throw new DataNotFoundException("McqQuestion is not found.");
        }
        logger.info("McqQuestion fetched successfully");
        return mcqQuestion;
    }

    public McqQuestion updateMcqQuestion(int id,McqQuestion mcqQuestion) {
        logger.info("Request for updating McqQuestion using McqQuestion Id");
        Optional<McqQuestion> createdmcqQuestion = mcqQuestionRepository.findById(id);
        if (!createdmcqQuestion.isPresent()) {
            logger.error("McqQuestion is not found");
            throw new DataNotFoundException("McqQuestion is not found.");
        }
        mcqQuestion.setQuestionId(id); 
        mcqQuestionRepository.save(mcqQuestion);
        logger.info("McqQuestion updated successfully");
        return mcqQuestion;
    }

    public void deleteMcqQuestion(int id) {
        logger.info("Request for deleting McqQuestion using McqQuestion Id");
        Optional<McqQuestion> mcqQuestion = mcqQuestionRepository.findById(id);
        if (!mcqQuestion.isPresent()) {
            logger.error("McqQuestion is not found");
            throw new DataNotFoundException("McqQuestion is not found.");
        }
        mcqQuestionRepository.deleteById(id);
        logger.info("McqQuestion deleted successfully");
    }
}
