package app.controller;

import app.serivce.ProduktasService;
import app.view.ProduktasView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produktas")
public class ProduktasController {
    private final ProduktasService produktasService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProduktasView> getProduktai() {
        return produktasService.getProduktai();
    }
}
