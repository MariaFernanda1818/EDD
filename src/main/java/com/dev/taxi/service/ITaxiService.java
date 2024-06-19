/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.taxi.service;

import com.dev.taxi.dto.RespuestaGrafoDto;

/**
 * La interfaz {@code ITaxiService} define los métodos necesarios para los servicios de taxi.
 * Proporciona la funcionalidad para calcular el destino más cercano entre dos lugares.
 */
public interface ITaxiService {
    
    /**
     * Calcula el destino más cercano desde un lugar de inicio a un lugar final.
     *
     * @param lugarInicio el lugar de inicio
     * @param lugarFinal  el lugar final
     * @return un objeto {@code RespuestaGrafoDto} que contiene la información del destino más cercano
     */
    RespuestaGrafoDto destinoMasCercano(String lugarInicio, String lugarFinal);
    
}

