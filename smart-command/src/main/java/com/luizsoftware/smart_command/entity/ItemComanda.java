package com.luizsoftware.smart_command.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="itens_comanda")
@Data
@Builder
public class ItemComanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Muitos itens para uma comanda
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comanda_id")
    private Comanda comanda;


    //Muitos itens um produto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id" , nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    //Guardar o preco primeiro momento
    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal precoUnitario;

    //Subtotal quantia vezes o preco unico
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @PrePersist
    @PreUpdate
    public void calcularSubtotal(){
        this.subtotal = this.getPrecoUnitario().multiply(
          BigDecimal.valueOf(this.quantidade)

        );
    }


}
