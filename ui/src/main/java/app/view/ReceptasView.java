package app.view;

import app.model.Produktas;
import app.model.Receptas;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ReceptasView {
    private String pavadinimas;
    private List<String> produktai;
    private String aprasymas;

    public static ReceptasView of(Receptas receptas) {
        return ReceptasView.builder()
                .pavadinimas(receptas.getPavadinimas())
                .produktai(receptas.getProduktai().stream().map(Produktas::getPavadinimas).collect(Collectors.toList()))
                .aprasymas(receptas.getAprasymas())
                .build();
    }
}
