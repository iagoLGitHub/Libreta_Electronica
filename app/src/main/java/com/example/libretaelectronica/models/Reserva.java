package com.example.libretaelectronica.models;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

public class Reserva { private Date fechaHora;
 private String nombre;

 public Reserva(Date fechaHora, String nombre) {
  this.fechaHora = fechaHora;
  this.nombre = nombre;

 }

 public Date getFechaHora() {
  return fechaHora;
 }

 public void setFechaHora(Date fechaHora) {
  this.fechaHora = fechaHora;
 }

 public String getNombre() {
  return nombre;
 }

 public void setNombre(String nombre) {
  this.nombre = nombre;
 }

 @Override
 public String toString() {
  return "Reserva{" +
          "fechaHora=" + fechaHora +
          ", nombre='" + nombre + '\'' +
          '}';
 }
}
