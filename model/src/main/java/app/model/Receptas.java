package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RECEPTAS")
public class Receptas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PAVADINIMAS")
    private String pavadinimas;

    @Column(name = "APRASYMAS")
    private String aprasymas;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "RECEPTAS_PRODUKTAI",
            joinColumns = @JoinColumn(name = "RECEPTAS_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUKTAS_ID"))
    private Set<Produktas> produktai = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ASMUO_ID")
    private Asmuo asmuo;
}
