package hu.egyudv.java;

import hu.egyudv.java.model.MunkagepServiceImpl;
import hu.egyudv.java.repository.*;
import hu.egyudv.java.exception.IncompatibilityTaskException;
import hu.egyudv.java.model.FeladatService;
import hu.egyudv.java.model.FeladatServiceImpl;
import hu.egyudv.java.model.MunkagepService;
import org.example.repository.*;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        FeladatRepository feladatRepository = new FeladatRepositoryMemory();
        MunkagepRepository munkagepRepository = new MunkagepRepositoryMemory();

        FeladatService feladatService = new FeladatServiceImpl(feladatRepository);
        MunkagepService munkagepService = new MunkagepServiceImpl(munkagepRepository, feladatRepository);

        Feladat forgiFeladat1 = feladatService.save(
                Feladat.builder()
                        .feladatTipus(FeladatTipus.FORGACSOLAS)
                        .nev("demo forgacsolas 1")
                        .build());

        Feladat forgiFeladat2 = feladatService.save(
                Feladat.builder()
                        .feladatTipus(FeladatTipus.FORGACSOLAS)
                        .nev("demo forgacsolas 2")
                        .build());

        Feladat koviFeladat = feladatService.save(
                Feladat.builder()
                        .feladatTipus(FeladatTipus.KOVACSOLAS)
                        .nev("demo kovacsolas 1")
                        .build());

        Munkagep kovacsologep = munkagepService.save(
                Munkagep.builder()
                        .feladatTipus(FeladatTipus.KOVACSOLAS)
                        .nev("kovacsologep")
                        .feladatList(new ArrayList<>())
                        .build()
        );

        Munkagep forgacsologep = munkagepService.save(
                Munkagep.builder()
                        .feladatTipus(FeladatTipus.FORGACSOLAS)
                        .nev("forgacsologep")
                        .feladatList(new ArrayList<>())
                        .build()
        );

        try {
            munkagepService.feladatFelvetel(forgacsologep.getId(), koviFeladat.getId());
        } catch (IncompatibilityTaskException ex) {
            System.out.println("Exception catched: " + ex.getMessage());
        }

        munkagepService.feladatFelvetel(forgacsologep.getId(), forgiFeladat1.getId());


    }
}
