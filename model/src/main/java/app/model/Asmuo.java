package app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ASMUO")
public class Asmuo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EL_PASTAS")
    private String elPastas;

    @Column(name = "VARDAS")
    private String vardas;

    @Column(name = "SLAPTAZODIS")
    private String slaptazodis;

    @OneToMany(mappedBy = "asmuo", cascade = CascadeType.ALL)
    private List<Receptas> receptai;
}
