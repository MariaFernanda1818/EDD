/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.taxi.utils.enums;

import lombok.Getter;

/**
 * Enumeración que representa diferentes lugares con su respectiva ubicación y nombre.
 */
@Getter
public enum LugaresEnum {
    I(0,"I"),
    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    D(4, "D"),
    E(5, "E"),
    F(6, "F"),
    T(7, "T");

    private final Integer ubicacion;
    private final String nombre;

   /**
     * Constructor para inicializar los campos de ubicación y nombre.
     * 
     * @param ubicacion El índice de la ubicación.
     * @param nombre El nombre del lugar.
     */
    LugaresEnum(Integer ubicacion, String nombre) {
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }
}
