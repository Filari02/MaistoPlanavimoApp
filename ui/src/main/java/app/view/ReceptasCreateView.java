package app.view;

import app.model.Receptas;
import lombok.Data;

import java.util.List;

@Data
public class ReceptasCreateView {
    private String pavadinimas;
    private String aprasymas;
    private List<Long> produktaiIds;
    private Long asmuoId;
}
