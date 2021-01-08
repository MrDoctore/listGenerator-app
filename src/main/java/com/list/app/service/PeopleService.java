package com.list.app.service;

import com.list.app.exceptions.BadRequestException;
import com.list.app.model.Event;
import com.list.app.model.People;
import com.list.app.repository.EventRepository;
import com.list.app.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final EventRepository eventRepository;
    private final PeopleRepository peopleRepository;

    public List<People> listAllByEvent(Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        return (List<People>) peopleRepository.findByEvent(event);
    }

    public People findById(Integer id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Person not found"));
    }

    public People save(Integer id, People people) {
        Event event = eventRepository.findById(id).get();
        people.setEvent(event);
        return peopleRepository.save(people);
    }

    public People update(People people) {
        return peopleRepository.save(people);
    }

    public void delete(Integer id) {
        peopleRepository.deleteById(id);
    }

    public void importFile(Integer id, String filePath) {
        Event event = eventRepository.findById(id).get();
        List<People> peopleList = new ArrayList<People>();
        try {
            FileInputStream arquivo = new FileInputStream(filePath);

            HSSFWorkbook workbook = new HSSFWorkbook(arquivo);
            HSSFSheet peopleSheet = workbook.getSheetAt(0);
            HSSFRow header_row = peopleSheet.getRow(0);
            Iterator<Row> rowIterator = peopleSheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getCell(0) != null) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    People person = new People();
                    person.setEvent(event);
                    while (cellIterator.hasNext()) {
                        for (int i = 0; i < 3; i++) {
                            DataFormatter formatter = new DataFormatter();
                            HSSFCell header_cell = header_row.getCell(i);
                            String header = header_cell.getStringCellValue();
                            Cell cell = cellIterator.next();
                            if (header.equalsIgnoreCase("NOME")) {
                                person.setName(formatter.formatCellValue(cell));
                            } else if (header.equalsIgnoreCase("EMAIL")) {
                                person.setEmail(formatter.formatCellValue(cell));
                            } else if (header.equalsIgnoreCase("SALA")) {
                                person.setClassroom(formatter.formatCellValue(cell));
                            } else {
                                System.out.println("Coluna inválida");
                            }
                        }
                    }
                    peopleList.add(person);
                }
            }
            arquivo.close();
            peopleRepository.saveAll(peopleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
