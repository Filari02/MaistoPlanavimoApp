package app.serivce;

import app.repository.ProduktasRepository;
import app.view.ProduktasView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduktasService {
    private final ProduktasRepository produktasRepository;

    public List<ProduktasView> getProduktai() {
        return produktasRepository.findAll().stream().map(ProduktasView::of).collect(Collectors.toList());
    }
}
