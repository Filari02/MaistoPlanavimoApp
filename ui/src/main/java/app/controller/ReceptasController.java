package app.controller;

import app.serivce.ReceptasService;
import app.view.ReceptasCreateView;
import app.view.ReceptasView;
import app.view.RibojimasView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/receptas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReceptasController {

    private final ReceptasService receptasService;

    @GetMapping("/asmuo-receptai/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ReceptasView> getAsmensReceptai(@PathVariable Long id) {
        return receptasService.getAsmensReceptai(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createReceptas(@RequestBody ReceptasCreateView receptasCreateView) {
        receptasService.createReceptas(receptasCreateView);
    }

    @PostMapping("/plan/{asmuoId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ReceptasView> plan(@RequestBody List<RibojimasView> ribojimasView, @PathVariable Long asmuoId) throws Exception {
        return receptasService.plan(ribojimasView, asmuoId);
    }
}
