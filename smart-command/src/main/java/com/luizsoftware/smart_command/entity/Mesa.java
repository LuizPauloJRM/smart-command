package com.luizsoftware.smart_command.entity;


import com.luizsoftware.smart_command.enums.StatusMesa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mesas")
@Data//Getters e Setters , toSring
@NoArgsConstructor//Construtor vazio
@AllArgsConstructor//Todos campos construtor
@Builder//padrao de builder
public class Mesa {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private Integer numero;

    @Column(nullable = false)//permissao de valores null
    private Integer capacidade;


    //Tipo Enum - Nome do enum salvo
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMesa status = StatusMesa.DISPONIVEL;

    private LocalDateTime abertoem;//mesa ocupada

    @OneToMany(mappedBy = "mesa" , cascade = CascadeType.ALL)
    private List<Reserva>reservas;

}
