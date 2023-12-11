package com.sis.nailsgestion.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sis.nailsgestion.util.CustomPdfProperties;
import com.sis.nailsgestion.util.NotFoundException;
import com.sis.nailsgestion.util.SchedulerTaskMapper;
import com.sis.nailsgestion.daos.SchedulerTaskRepository;
import com.sis.nailsgestion.models.ArrangementTypeDto;
import com.sis.nailsgestion.models.SchedulerTaskDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SchedulerTaskServiceImpl implements SchedulerTaskService {

    static final Font BODY_CELL_FONT = new Font(Font.FontFamily.HELVETICA, 11,
            Font.BOLD);
    SchedulerTaskRepository schedulerTaskRepository;
    SchedulerTaskMapper schedulerTaskMapper;

    CustomPdfProperties customPdfProperties;
    static final String MSG_EXCEPTION_SCHEDULER_TASK = "¡ Not found SchedulerTask !";

    static final Logger logger = LoggerFactory.getLogger(SchedulerTaskServiceImpl.class);

    static final String LABEL_DESCRIPTION = "DESCRIPTION";
    static final String LABEL_PRICE = "PRICE";
    static final String LABEL_TOTAL = "RECAUDED TOTAL";

    static final String IMAGE_PATH = "static/nails.png";


    public SchedulerTaskServiceImpl(SchedulerTaskRepository schedulerTaskRepository, SchedulerTaskMapper schedulerTaskMapper, CustomPdfProperties customPdfProperties) {
        this.schedulerTaskRepository = schedulerTaskRepository;
        this.schedulerTaskMapper = schedulerTaskMapper;
        this.customPdfProperties = customPdfProperties;
    }

    @Override
    public List<SchedulerTaskDto> getSchedulerTasks() {
        List<SchedulerTaskDto> schedulerTaskDtoListEmpty = new ArrayList<>();
        if (schedulerTaskRepository.findAll().isEmpty()) {
            return schedulerTaskDtoListEmpty;
        }

        return schedulerTaskMapper.mapToListDto(schedulerTaskRepository.findAll().stream().toList());
    }

    @Override
    public SchedulerTaskDto getSchedulerTaskById(Long id) {
        if (!schedulerTaskRepository.existsById(id)) {
            return null;
        }
        return schedulerTaskMapper.convertToDto(schedulerTaskRepository.findById(id).orElseThrow(() -> new NotFoundException(MSG_EXCEPTION_SCHEDULER_TASK)));
    }

    @Override
    public SchedulerTaskDto createSchedulerTask(SchedulerTaskDto schedulerTaskDto) {
        if (schedulerTaskDto == null) {
            return null;
        }
        return schedulerTaskMapper.convertToDto(schedulerTaskRepository.save(schedulerTaskMapper.convertToEntity(schedulerTaskDto)));
    }

    @Override
    public SchedulerTaskDto updateSchedulerTask(SchedulerTaskDto schedulerTaskDto, Long id) {
        if (schedulerTaskDto == null) {
            return null;
        }

        SchedulerTaskDto updatedschedulerTaskDto = schedulerTaskMapper.convertToDto(schedulerTaskRepository.save(schedulerTaskMapper.convertToEntity(schedulerTaskDto)));
        updatedschedulerTaskDto.setId(id);
        return updatedschedulerTaskDto;
    }

    @Override
    public void removeSchedulerTaskById(Long id) {

        if (!schedulerTaskRepository.existsById(id)) {
            throw new NotFoundException(MSG_EXCEPTION_SCHEDULER_TASK);
        }
        schedulerTaskRepository.deleteById(id);
    }

    @Override
    public Double getTotalAmountBySchedulerTask(Long id) {
        return schedulerTaskRepository.getTotalAmountBySchedulerTask(id);
    }

    @Override
    public Resource getPdfSchedulerTask(Long id) {
        Document document = new Document(PageSize.A4);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            /*add Metadata*/

            customPdfProperties.addMetaData(document);
            /*agregar logo */

            Image logo = Image.getInstance(getAbsolutePath(IMAGE_PATH));

            /* add coordinates  x/y */
            logo.setAbsolutePosition(customPdfProperties.getLogoXCoordinate(), customPdfProperties.getLogoYCoordinate());

            document.add(logo);

            /*add title */

            customPdfProperties.addTitleReport(document);

            /* add appointmentDate subtitle*/
            Paragraph subTitle = new Paragraph();
            subTitle.setAlignment(Element.ALIGN_CENTER);

            SchedulerTaskDto schedulerTaskDto = getSchedulerTaskById(id);
            subTitle.add(schedulerTaskDto.getAppointmentDate().toString());
            document.add(subTitle);

            /*add customers title*/

            customPdfProperties.addCustomersTitle(document);

            /*add table customers*/
            customPdfProperties.addTableCustomers(document, schedulerTaskDto);
            /*add arrangements title*/

            customPdfProperties.addArrangementsTitle(document);

            /*add table arrangements with recauded total */
            PdfPTable tableArrangements = new PdfPTable(2);

            tableArrangements.setWidthPercentage(100);

            PdfPCell cellDESCRIPTION = new PdfPCell(new Phrase(LABEL_DESCRIPTION, BODY_CELL_FONT));
            cellDESCRIPTION.setBorder(Rectangle.BOX);
            tableArrangements.addCell(cellDESCRIPTION);

            PdfPCell cellPRICE = new PdfPCell(new Phrase(LABEL_PRICE, BODY_CELL_FONT));
            cellPRICE.setBorder(Rectangle.BOX);
            tableArrangements.addCell(cellPRICE);

            for (ArrangementTypeDto arrangementTypeDto : schedulerTaskDto.getArrangementTypes()) {
                tableArrangements.addCell(arrangementTypeDto.getDescription());
                tableArrangements.addCell(arrangementTypeDto.getPrice().toString());
            }
            PdfPCell cellTotal = new PdfPCell(new Phrase(LABEL_TOTAL, BODY_CELL_FONT));
            tableArrangements.addCell(cellTotal);

            double total = getTotalAmountBySchedulerTask(id) * schedulerTaskDto.getCustomers().size();

            PdfPCell valueTotal = new PdfPCell(new Phrase(Double.toString(total), BODY_CELL_FONT));
            tableArrangements.addCell(valueTotal);


            document.add(tableArrangements);


            document.close();
            writer.close();

            return new ByteArrayResource(outputStream.toByteArray());
        } catch (DocumentException | IOException documentException) {
            logger.info(documentException.getMessage());
            throw new NotFoundException(MSG_EXCEPTION_SCHEDULER_TASK);
        }

    }

    private static String getAbsolutePath(String relativePath) throws IOException {
        Resource resource = new ClassPathResource(relativePath);
        return resource.getFile().getAbsolutePath();
    }


}
