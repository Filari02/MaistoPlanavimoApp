package app.serivce;

import app.model.Produktas;
import app.repository.AsmuoRepository;
import app.repository.ProduktasRepository;
import app.utils.ReceptaiPlanningUtil;
import app.view.ReceptasCreateView;
import app.view.ReceptasView;
import app.view.RibojimasView;
import lombok.RequiredArgsConstructor;
import app.model.Receptas;
import org.springframework.stereotype.Service;
import app.repository.ReceptasRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceptasService {

    private final ReceptasRepository receptasRepository;
    private final AsmuoService asmuoService;
    private final AsmuoRepository asmuoRepository;
    private final ProduktasRepository produktasRepository;

    public List<ReceptasView> getAsmensReceptai(Long asmuoId) {
        return asmuoService.getAsmensReceptai(asmuoId);
    }

    public void createReceptas(ReceptasCreateView receptasView) {
        Receptas receptas = new Receptas();
        receptas.setPavadinimas(receptasView.getPavadinimas());
        receptas.setAprasymas(receptas.getAprasymas());
        receptas.setAsmuo(asmuoRepository.getReferenceById(receptasView.getAsmuoId()));
        receptas.setProduktai(receptasView.getProduktaiIds().stream().map(produktasRepository::getReferenceById).collect(Collectors.toSet()));
        receptasRepository.save(receptas);
    }

    public List<ReceptasView> plan(List<RibojimasView> ribojimasView, Long asmuoId) throws Exception {
        if (ribojimasView.stream().allMatch(r -> r.getTimesPerWeek() < 0)) {
            throw new Exception("bloga įvestis");
        }
        List<Receptas> receptai = asmuoRepository.findById(asmuoId).get().getReceptai();
        List<Receptas> plannedReceptai = ReceptaiPlanningUtil.planReceptaiForWeek(ribojimasView, receptai);
        return plannedReceptai.stream().map(ReceptasView::of).collect(Collectors.toList());
    }



}
