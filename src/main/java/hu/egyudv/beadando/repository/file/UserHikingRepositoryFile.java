package hu.egyudv.beadando.repository.file;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import hu.egyudv.beadando.exception.RepositoryException;
import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.UserHikingRepository;
import hu.egyudv.beadando.repository.UserRepository;
import hu.egyudv.beadando.repository.file.entity.Hiking;
import hu.egyudv.beadando.repository.file.entity.User;
import hu.egyudv.beadando.repository.file.entity.UserHiking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserHikingRepositoryFile implements UserHikingRepository {

    private List<UserHiking> objectList = new ArrayList<>();
    private static final String FILE_PATH = "dataFiles/userHiking.csv";
    private FileHandler fileHandler;


    @Override
    public List<Hiking> getHikingListByUser(String userId) {
        objectList = all();
        List<Hiking> resultList = new ArrayList<>();
        HikingRepository hikingRepository = new HikingRepositoryFile();
        for (UserHiking item : objectList) {
            if (item.getUserId().equals(userId)) {
                Hiking hiking = hikingRepository.get(item.getHikingId());
                if (hiking != null) {
                    resultList.add(hiking);
                }
            }
        }
        return resultList;
    }

    @Override
    public List<User> getUserListByHiking(String hikingId) {
        objectList = all();
        List<User> resultList = new ArrayList<>();
        UserRepository userRepository = new UserRepositoryFile();
        for (UserHiking item : objectList) {
            if (item.getHikingId().equals(hikingId)) {
                User user = userRepository.get(item.getUserId());
                if (user != null) {
                    resultList.add(user);
                }
            }
        }
        return resultList;
    }

    @Override
    public void save(String userId, String hikingId) {
        objectList = all();
        if (userId != null && hikingId != null) {
            for (UserHiking item : objectList) {
                if (item.getUserId().equals(userId)
                        && item.getHikingId().equals(hikingId)) {
                    throw new RepositoryException("User and Hiking already related");
                }
            }
            UserHiking userHiking = new UserHiking();
            userHiking.setId(UUID.randomUUID().toString());
            userHiking.setUserId(userId);
            userHiking.setHikingId(hikingId);
            objectList.add(userHiking);
        }

        try {
            updateCsv();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void delete(String userId, String hikingId) {
        objectList = all();
        UserHiking object = null;
        if (userId != null && hikingId != null) {
            for (UserHiking item : objectList) {
                if (item.getUserId().equals(userId)
                        && item.getHikingId().equals(hikingId)) {
                    object = item;
                }
            }
            if (object == null) {
                throw new RepositoryException("Error during delete: item not found");
            } else {
                objectList.remove(object);
            }
        }
        try {
            updateCsv();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAllByUser(String userId) {
        System.out.println("before delete: " + objectList);
        if (userId != null) {
            List<UserHiking> byUserList = getByUser(userId);
            System.out.println("before delete: " + objectList);
            System.out.println("before delete by: " + byUserList);
            for (UserHiking item : getByUser(userId)) {
                objectList.remove(item);
            }
        }
        System.out.println("after delete: " + objectList);
        try {
            updateCsv();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<UserHiking> getByUser(String userId) {
        objectList = all();
        List<UserHiking> resultList = new ArrayList<>();
        for (UserHiking item : resultList) {
            if (item.getUserId().equals(userId)) {
                resultList.add(item);
            }
        }
        return resultList;
    }


    private List<UserHiking> all() {
        try {
            List<UserHiking> resultList = convertCsvToList();
            objectList.addAll(resultList);
        } catch (Exception ex) {
            throw new RuntimeException("error in read file data");
        }
        return objectList;
    }

    private List<UserHiking> convertCsvToList() throws IOException {
        String fileString = getCsvString();
        List<UserHiking> resultList = new CsvToBeanBuilder(new StringReader(fileString))
                .withType(UserHiking.class)
                .build()
                .parse();
        return resultList;
    }

    private void updateCsv() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        System.out.println("update: " + objectList);
        Writer writer = new FileWriter(FILE_PATH);
        StatefulBeanToCsv<UserHiking> beanToCsv = new StatefulBeanToCsvBuilder<UserHiking>(writer).build();
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
