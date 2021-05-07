package hu.egyudv.beadando;

import hu.egyudv.beadando.service.HikingServiceImpl;
import hu.egyudv.beadando.service.UserService;
import hu.egyudv.beadando.service.UserServiceImpl;
import hu.egyudv.beadando.service.HikingService;
import hu.egyudv.beadando.repository.*;

import java.text.ParseException;


public class App {

    public static void main(String[] args) throws ParseException {
        System.out.println("Hello World!");

        UserRepository userRepository = new UserRepositoryFile();
        HikingRepository hikingRepository = new HikingRepositoryFile();

        UserService userService = new UserServiceImpl(userRepository);
        HikingService hikingService = new HikingServiceImpl();

//        User user01 = new User();
//        user01.setName("Test User 01");
//        user01.setBirthDate(new SimpleDateFormat("yyyy.MM.dd").parse("1990.05.27"));
//        user01.setMobile("+123456");
//        userService.save(user01);
//
//        User user02 = new User();
//        user02.setName("Test User 02");
//        user02.setBirthDate(new SimpleDateFormat("yyyy.MM.dd").parse("1996.10.03"));
//        user02.setMobile("+777888");
//        userService.save(user02);
//
//        User user03 = new User();
//        user03.setName("Test User 03");
//        user03.setBirthDate(new SimpleDateFormat("yyyy.MM.dd").parse("1994.08.11"));
//        user03.setMobile("+5555555");
//        userService.save(user03);
        System.out.println(userService.all());

//        Feladat forgiFeladat2 = userService.save(
//                Feladat.builder()
//                        .feladatTipus(FeladatTipus.FORGACSOLAS)
//                        .nev("demo forgacsolas 2")
//                        .build());
//
//        Munkagep forgacsologep = hikingService.save(
//                Munkagep.builder()
//                        .feladatTipus(FeladatTipus.FORGACSOLAS)
//                        .nev("forgacsologep")
//                        .feladatList(new ArrayList<>())
//                        .build()
//        );
//
//        try {
//            hikingService.feladatFelvetel(forgacsologep.getId(), koviFeladat.getId());
//        } catch (IncompatibilityTaskException ex) {
//            System.out.println("Exception catched: " + ex.getMessage());
//        }
//
//        hikingService.feladatFelvetel(forgacsologep.getId(), forgiFeladat1.getId());


    }
}
