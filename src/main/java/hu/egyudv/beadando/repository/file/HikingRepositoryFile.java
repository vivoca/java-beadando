package hu.egyudv.beadando.repository.file;


import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.UserHikingRepository;
import hu.egyudv.beadando.repository.file.entity.Hiking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import hu.egyudv.beadando.exception.EntityNotFoundException;
import hu.egyudv.beadando.repository.file.entity.User;


public class HikingRepositoryFile implements HikingRepository {

    private List<Hiking> objectList = new ArrayList<>();
    private static final String FILE_PATH = "dataFiles/hikings.csv";
    private FileHandler fileHandler;

    private final UserHikingRepository userHikingRepository = new UserHikingRepositoryFile();

    @Override
    public List<Hiking> all() {
        try {
            List<Hiking> resultList = convertCsvToList();
            objectList.addAll(resultList);
        } catch (Exception ex) {
            throw new RuntimeException("error in read file data");
        }
        return objectList;
    }

    @Override
    public Hiking save(Hiking object) {
        objectList = all();
        Hiking found = null;
        if (object.getId() != null && object.getId().length() > 0) {
            for (Hiking item : objectList) {
                if (item.getId().equals(object.getId())) {
                    item.setName(object.getName());
                    item.setLocation(object.getLocation());
                    item.setLength(object.getLength());
                    item.setDescription(object.getDescription());
                    item.setDifficulty(object.getDifficulty());
                    found = item;
                    break;
                }
            }
            if (found == null) {
                throw new EntityNotFoundException("No such hiking data " + object.getId());
            }
        } else { // insert
            object.setId(UUID.randomUUID().toString());
            objectList.add(object);
            found = object;
        }

        try {
            updateCsv();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return found;
    }

    @Override
    public void delete(String id) {
        objectList = all();
        objectList = objectList.stream().filter(object -> !object.getId().equals(id)).collect(Collectors.toList());
        try {
            updateCsv();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Hiking get(String id) {
        objectList = all();
        Hiking result = objectList.stream()
                .filter( f -> f.getId().equals(id))
                .findFirst()
                .get();
        if (result == null) {
            throw new EntityNotFoundException("No such hiking " + id);
        }
        return result;
    }

    @Override
    public List<User> getUserCompletedList(String id) {
        // todo
        return new ArrayList<>();
    }

    private List<Hiking> convertCsvToList() throws IOException {
        String fileString = getCsvString();
        List<Hiking> resultList = new CsvToBeanBuilder(new StringReader(fileString))
                .withType(Hiking.class)
                .build()
                .parse();
        return resultList;
    }

    private void updateCsv() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        Writer writer = new FileWriter(FILE_PATH);
        StatefulBeanToCsv<Hiking> beanToCsv = new StatefulBeanToCsvBuilder<Hiking>(writer).build();
        beanToCsv.write(objectList);
        writer.close();
    }

    private String getCsvString() throws IOException {
        if (fileHandler == null) {
            fileHandler = new FileHandler(FILE_PATH);
        }
        return fileHandler.getFileContent();
    }

}
