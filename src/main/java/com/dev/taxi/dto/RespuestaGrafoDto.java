/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.taxi.dto;

import lombok.Data;

@Data
public class RespuestaGrafoDto {
    private String matriz;

    private DijkstraDto dijkstra;
}
