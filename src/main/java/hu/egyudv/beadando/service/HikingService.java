package hu.egyudv.beadando.model;


import hu.egyudv.beadando.repository.entity.Hiking;

public interface HikingService extends GeneralService<Hiking> {

    void feladatFelvetel(String munkagepId, String feladatId);
}
