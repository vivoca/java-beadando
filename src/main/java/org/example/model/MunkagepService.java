package org.example.model;

import org.example.repository.Munkagep;

public interface MunkagepService extends GeneralService<Munkagep> {

    void feladatFelvetel(String munkagepId, String feladatId);
}
