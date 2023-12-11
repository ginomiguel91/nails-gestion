package com.sis.nailsgestion.controllers;

import com.sis.nailsgestion.models.SchedulerTaskDto;
import com.sis.nailsgestion.services.SchedulerTaskService;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api")
public class SchedulerTaskController {

    SchedulerTaskService schedulerTaskService;


    public SchedulerTaskController(SchedulerTaskService schedulerTaskService) {
        this.schedulerTaskService = schedulerTaskService;
    }


    @GetMapping("/schedulerTasks")
    public ResponseEntity<List<SchedulerTaskDto>> getSchedulerTasks() {
        if (schedulerTaskService.getSchedulerTasks().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        return new ResponseEntity<>(schedulerTaskService.getSchedulerTasks(), HttpStatus.OK);
    }

    @GetMapping("/schedulerTasks/{id}")
    public ResponseEntity<SchedulerTaskDto> getSchedulerTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(schedulerTaskService.getSchedulerTaskById(id), HttpStatus.OK);
    }

    @PostMapping("/schedulerTasks")
    public ResponseEntity<SchedulerTaskDto> createSchedulerTask(@RequestBody SchedulerTaskDto schedulerTaskDto) {
        return new ResponseEntity<>(schedulerTaskService.createSchedulerTask(schedulerTaskDto), HttpStatus.CREATED);
    }

    @PutMapping("/schedulerTasks/{id}")
    public ResponseEntity<SchedulerTaskDto> updateSchedulerTask(@RequestBody SchedulerTaskDto schedulerTaskDto, @PathVariable Long id) {
        return new ResponseEntity<>(schedulerTaskService.updateSchedulerTask(schedulerTaskDto, id), HttpStatus.OK);
    }


    @DeleteMapping("/schedulerTasks/{id}")
    public ResponseEntity<SchedulerTaskDto> removeSchedulerTaskById(@PathVariable Long id) {
        schedulerTaskService.removeSchedulerTaskById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/schedulerTasks/totalAmount/{id}")
    public Double getTotalAmountBySchedulerTask(@PathVariable Long id) {
        return schedulerTaskService.getTotalAmountBySchedulerTask(id);

    }

    @GetMapping("/schedulerTasks/pdf/{id}")
    public ResponseEntity<Resource> getPdfSchedulerTask(@PathVariable Long id) {


        Resource pdfResource = schedulerTaskService.getPdfSchedulerTask(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Scheduler_Task_Report.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfResource);

    }
}
