/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.taxi.service.impl;

import com.dev.taxi.dto.DijkstraDto;
import com.dev.taxi.dto.RespuestaGrafoDto;
import com.dev.taxi.service.ITaxiService;
import com.dev.taxi.utils.enums.LugaresEnum;
import lombok.extern.log4j.Log4j2;


/**
 * Clase de servicio que proporciona funcionalidad para encontrar la ruta más corta
 * entre dos ubicaciones utilizando el algoritmo de Dijkstra.
 */
@Log4j2
public class TaxiService implements ITaxiService {

    private static final int INF = Integer.MAX_VALUE / 2; // Prevenir desbordamientos en cálculos
    private int[][] aristas;
    private int[][] copiris;

    /**
     * Constructor para inicializar la matriz de adyacencia y otros datos necesarios.
     */
    public TaxiService() {
        this.aristas = new int[8][8];
        this.copiris = new int[8][8];
        iniciarData();
    }

    /**
     * Encuentra la ruta más corta entre las ubicaciones de inicio y final utilizando el algoritmo de Dijkstra.
     *
     * @param lugarInicio La ubicación de inicio.
     * @param lugarFinal La ubicación final.
     * @return Un objeto {@link RespuestaGrafo} que contiene la matriz del grafo y el resultado del algoritmo de Dijkstra.
     */
    @Override
    public RespuestaGrafoDto destinoMasCercano(String lugarInicio, String lugarFinal) {
        Integer ubicacionInicio = LugaresEnum.valueOf(lugarInicio).getUbicacion();
        Integer ubicacionFinal = LugaresEnum.valueOf(lugarFinal).getUbicacion();

        DijkstraDto dij = new DijkstraDto(INF, "", ubicacionInicio, ubicacionFinal);
        dij = dijkstra(ubicacionInicio, ubicacionFinal, 0, "", dij);

        String recorrido = dij.getRecorrido();
        StringBuilder recorridoFinal = new StringBuilder();
        recorridoFinal.append(setEnum(dij.getInicio())).append("-");

        for (String numero : recorrido.split("-")) {
            if(!numero.isEmpty()){
               recorridoFinal.append(setEnum(Integer.valueOf(numero))).append("-");
            }
        }

        recorridoFinal.append(setEnum(dij.getFin()));
        dij.setRecorrido(recorridoFinal.toString());

        RespuestaGrafoDto respuestaGrafo = new RespuestaGrafoDto();
        respuestaGrafo.setMatriz(generarMatris());
        respuestaGrafo.setDijkstra(dij);

        return respuestaGrafo;
    }

    /**
     * Inicializa la matriz de adyacencia con los valores predeterminados.
     */
    public void iniciarData() {
        aristas = new int[][] {
                { 0,  9,  0,  2,  0,  0,  0,  0 }, // Zona I
                { 9,  0,  1,  0,  1,  0,  0,  0 }, // Zona A
                { 0,  1,  0,  1,  7,  0,  6,  0 }, // Zona B
                { 2,  0,  1,  0,  0,  0, 10,  0 }, // Zona C
                { 0,  1,  7,  0,  0,  2,  0, 18 }, // Zona D
                { 0,  0,  0,  0,  2,  0,  0,  9 }, // Zona E
                { 0,  0,  6, 10,  0,  0,  0,  2 }, // Zona F
                { 0,  0,  0,  0, 18,  9,  2,  0 }  // Zona T
        };
        copiris = new int[aristas.length][aristas[0].length];
        volverLlenar();
    }

    /**
     * Rellena la matriz auxiliar copiris con los valores de la matriz de adyacencia aristas.
     */
    public void volverLlenar() {
        for (int i = 0; i < aristas.length; i++) {
            System.arraycopy(aristas[i], 0, copiris[i], 0, aristas[0].length);
        }
    }

    /**
     * Implementación del algoritmo de Dijkstra para encontrar la ruta más corta.
     *
     * @param lugarInicial El índice de la ubicación inicial.
     * @param fin El índice de la ubicación final.
     * @param trae La distancia acumulada hasta el momento.
     * @param suple El camino recorrido hasta el momento.
     * @param objetoBase El objeto Dijkstra que almacena los resultados del algoritmo.
     * @return El objeto Dijkstra con los resultados del cálculo de la ruta más corta.
     */
    public DijkstraDto dijkstra(int lugarInicial, int fin, int trae, String suple, DijkstraDto objetoBase) {
        if (copiris[lugarInicial][fin] != 0 && objetoBase.getCantidad() > copiris[lugarInicial][fin] + trae) {
            objetoBase.setCantidad(copiris[lugarInicial][fin] + trae);
            copiris[lugarInicial][fin] = copiris[fin][lugarInicial] = 0;
            objetoBase.setRecorrido(suple);
        }

        for (int i = 0; i < copiris.length; i++) {
            if (copiris[lugarInicial][i] != 0 && objetoBase.getCantidad() > (copiris[lugarInicial][i] + trae)) {
                int nuevoTrae = copiris[lugarInicial][i] + trae;
                copiris[lugarInicial][i] = copiris[i][lugarInicial] = 0; // Eliminar arista
                String nuevoSuple = suple + i + "-";
                objetoBase = dijkstra(i, fin, nuevoTrae, nuevoSuple, objetoBase);
            }
        }

        volverLlenar();
        return objetoBase;
    }

    /**
     * Genera una representación en cadena de la matriz de adyacencia.
     *
     * @return La matriz de adyacencia en formato de cadena.
     */
    public String generarMatris() {
        StringBuilder sb = new StringBuilder();
        for (int[] fila : aristas) {
            for (int valor : fila) {
                sb.append(valor).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Convierte un índice de ubicación a su correspondiente valor en el enum LugaresEnum.
     *
     * @param ubicacion El índice de la ubicación.
     * @return El valor correspondiente en LugaresEnum.
     */
    private LugaresEnum setEnum(Integer ubicacion) {
        return LugaresEnum.values()[ubicacion];
    }
}