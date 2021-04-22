package org.example.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
public class Munkagep extends CompanyItem{

    private List<Feladat> feladatList;

}
