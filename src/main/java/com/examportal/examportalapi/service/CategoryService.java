package com.examportal.examportalapi.service;


import com.examportal.examportalapi.data.transfer.object.CategoryDto;
import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.domain.CategoryDomain;
import com.examportal.examportalapi.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public MessageDto addCategory(CategoryDto categoryDto)
    {
        CategoryDomain categoryDomain = mapDtoToDomain(categoryDto);
        categoryRepository.save(categoryDomain);
        log.info("Category have been saved with id "+categoryDomain.getCategoryId());
        String message = "Category have been saved with id "+categoryDomain.getCategoryId();
        MessageDto messageDto = new MessageDto(message);
        return messageDto;
    }

    public CategoryDto getCategoryById(int id)
    {
        CategoryDomain categoryDomain = categoryRepository.findById(id).get();
         return mapDomainToDto(categoryDomain);
    }

    public MessageDto updateCategoryById(CategoryDto categoryDto , int id)
    {
        if(categoryRepository.findById(id).isPresent())
        {
            categoryDto.setCategoryId(id);
            categoryRepository.save(mapDtoToDomain(categoryDto));
            String message = "Category have been Updated with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }
        else {
            String message = "Category not found with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }
    }

    public MessageDto deleteCategoryById(int id)
    {
        if(categoryRepository.findById(id).isPresent())
        {
            categoryRepository.deleteById(id);
            String message = "Category have been deleted with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }
        else {
            String message = "Category not found with id "+id;
            MessageDto messageDto = new MessageDto(message);
            return messageDto;
        }

    }

    public CategoryDomain mapDtoToDomain(CategoryDto categoryDto) {
        return objectMapper.convertValue(categoryDto, CategoryDomain.class);
    }

    public CategoryDto mapDomainToDto(CategoryDomain categoryDomain) {
        return objectMapper.convertValue(categoryDomain, CategoryDto.class);
    }
}
