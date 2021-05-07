package hu.egyudv.beadando.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import hu.egyudv.beadando.exception.EntityNotFoundException;
import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.entity.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserRepositoryFile implements UserRepository {

    private List<User> objectList = new ArrayList<>();
    private static final String FILE_PATH = "dataFiles/users.csv";
    private FileHandler fileHandler;

    private final UserHikingRepository userHikingRepository = new UserHikingRepositoryFile();


    @Override
    public List<User> all() {
        try {
            List<User> resultList = convertCsvToList();
            objectList.addAll(resultList);
        } catch (Exception ex) {
            throw new RuntimeException("error in read file data");
        }
        return objectList;
    }

    @Override
    public User save(User object) {
        User found = null;
        objectList = all();
        if (object.getId() != null && object.getId().length() > 0) {
            for (User item : objectList) {
                if (item.getId().equals(object.getId())) {
                    item.setFirstName(object.getFirstName());
                    item.setLastName(object.getLastName());
                    item.setBirthDate(object.getBirthDate());
                    item.setMobile(object.getMobile());
                    item.setCompletedHikingList(object.getCompletedHikingList());
                    found = item;
                    break;
                }
            }
            if (found == null) {
                throw new EntityNotFoundException("No such User data " + object.getId());
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
    public User get(String id) {
        objectList = all();
        User result = objectList.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .get();
        if (result == null) {
            throw new EntityNotFoundException("No such User " + id);
        }
        return result;
    }

    private List<User> convertCsvToList() throws IOException {
        String fileString = getCsvString();
        List<User> resultList = new CsvToBeanBuilder(new StringReader(fileString))
                .withType(User.class)
                .build()
                .parse();
        return resultList;
    }

    private void updateCsv() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Writer writer = new FileWriter(FILE_PATH);
        StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(writer).build();
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
