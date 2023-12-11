package com.sis.nailsgestion.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sis.nailsgestion.models.CustomerDto;
import com.sis.nailsgestion.models.SchedulerTaskDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class CustomPdfPropertiesImpl implements CustomPdfProperties {

    static final Font BODY_CELL_FONT = new Font(Font.FontFamily.HELVETICA, 11,
            Font.BOLD);
    static final String TITLE_REPORT = "Scheduler Task Report";

    static final String CUSTOMER_LIST = "Customers list";
    static final String ARRANGEMENT_LIST = "Arrangements list by customer";
    static final String LABEL_NAME = "NAME";
    static final String LABEL_LASTNAME = "LASTNAME";
    static final String LABEL_IDENTIFICATION = "IDENTIFICATION";
    static final String LABEL_PHONE_NUMBER = "PHONE_NUMBER";

    static Font customFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    @Value("${logo.x-coordinate}")
    private float logoXCoordinate;

    @Value("${logo.y-coordinate}")
    private float logoYCoordinate;

    @Override
    public void addMetaData(Document document) {
        document.addTitle(" Scheduler Task PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Gino Miguel Ricardo Glez");
        document.addCreator("Gino Miguel Ricardo Glez");
    }

    @Override
    public void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    @Override
    public void addTitleReport(Document document) throws DocumentException {
        Paragraph title = new Paragraph();

        title.add(new Phrase(TITLE_REPORT, customFont));
        title.setAlignment(Element.ALIGN_CENTER);

        document.add(title);
    }

    @Override
    public void addCustomersTitle(Document document) throws DocumentException {
        Paragraph customerTitle = new Paragraph();
        customerTitle.add(new Phrase(CUSTOMER_LIST, BODY_CELL_FONT));
        customerTitle.setAlignment(Element.ALIGN_CENTER);

        addEmptyLine(customerTitle, 2);
        document.add(customerTitle);

    }


    @Override
    public void addArrangementsTitle(Document document) throws DocumentException {
        Paragraph arrangementTitle = new Paragraph();
        arrangementTitle.add(new Phrase(ARRANGEMENT_LIST, BODY_CELL_FONT));
        arrangementTitle.setAlignment(Element.ALIGN_CENTER);


        addEmptyLine(arrangementTitle, 2);
        document.add(arrangementTitle);

    }

    @Override
    public void addTableCustomers(Document document, SchedulerTaskDto schedulerTaskDto) throws DocumentException {
        PdfPTable tableCustomers = new PdfPTable(4);
        tableCustomers.setWidthPercentage(100);
        PdfPCell name = new PdfPCell(new Phrase(LABEL_NAME, BODY_CELL_FONT));
        name.setBorder(Rectangle.BOX);
        tableCustomers.addCell(name);

        PdfPCell lastname = new PdfPCell(new Phrase(LABEL_LASTNAME, BODY_CELL_FONT));
        lastname.setBorder(Rectangle.BOX);
        tableCustomers.addCell(lastname);

        PdfPCell identification = new PdfPCell(new Phrase(LABEL_IDENTIFICATION, BODY_CELL_FONT));
        identification.setBorder(Rectangle.BOX);
        tableCustomers.addCell(identification);


        PdfPCell phoneNumber = new PdfPCell(new Phrase(LABEL_PHONE_NUMBER, BODY_CELL_FONT));
        phoneNumber.setBorder(Rectangle.BOX);
        tableCustomers.addCell(phoneNumber);


        for (CustomerDto customerDto : schedulerTaskDto.getCustomers()) {
            tableCustomers.addCell(customerDto.getName());
            tableCustomers.addCell(customerDto.getLastName());
            tableCustomers.addCell(customerDto.getIdentification());
            tableCustomers.addCell(customerDto.getPhoneNumber());
        }

        document.add(tableCustomers);
    }

    @Override
    public float getLogoXCoordinate() {
        return logoXCoordinate;
    }

    @Override
    public float getLogoYCoordinate() {
        return logoYCoordinate;
    }


}
