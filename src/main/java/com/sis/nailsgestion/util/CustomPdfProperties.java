package com.sis.nailsgestion.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.sis.nailsgestion.models.SchedulerTaskDto;


public interface CustomPdfProperties {


    void addMetaData(Document document);

    void addEmptyLine(Paragraph paragraph, int number);

    void addTitleReport(Document document) throws DocumentException;

    void addCustomersTitle(Document document) throws DocumentException;

    void addArrangementsTitle(Document document) throws DocumentException;


    void addTableCustomers(Document document, SchedulerTaskDto schedulerTaskDto) throws DocumentException;

    float getLogoXCoordinate();

    float getLogoYCoordinate();
}
