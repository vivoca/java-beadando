package org.example.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CompanyItem {

    private String id;
    private String nev;
    private FeladatTipus feladatTipus;
}
