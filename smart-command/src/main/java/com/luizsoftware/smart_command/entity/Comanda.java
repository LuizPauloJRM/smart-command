package com.luizsoftware.smart_command.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comandas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Muitas demandas para uma mesa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    //Comanda vinculada ao cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDateTime criadoEm = LocalDateTime.now();

    private Boolean infechada = false;

    //Calculo dinamico
    @Column(precision = 10,scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    //Uma comanda varios itens
    @ManyToMany(mappedBy = "comanda" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<ItemComanda> itens;
}
