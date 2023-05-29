package app.view;

import app.model.Produktas;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduktasView {
    private Long id;
    private String pavadinimas;

    public  static ProduktasView of(Produktas produktas) {
        return ProduktasView.builder()
                .id(produktas.getId())
                .pavadinimas(produktas.getPavadinimas())
                .build();
    }
}
