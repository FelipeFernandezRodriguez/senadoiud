package co.edu.iudigital.senadoiud.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum DepartmentType {

    AMAZONAS("Amazonas"),
    ANTIOQUIA("Antioquia"),
    ARAUCA("Arauca"),
    ATLANTICO("Atlántico"),
    BOGOTA("Bogotá"),
    BOLIVAR("Bolívar"),
    BOYACA("Boyacá"),
    CALDAS("Caldas"),
    CAQUETA("Caquetá"),
    CASANARE("Casanare"),
    CAUCA("Cauca"),
    CESAR("Cesar"),
    CHOCO("Chocó"),
    CORDOBA("Córdoba"),
    CUNDINAMARCA("Cundinamarca"),
    GUAINIA("Guainía"),
    GUAVIARE("Guaviare"),
    HUILA("Huila"),
    LA_GUAJIRA("La Guajira"),
    MAGDALENA("Magdalena"),
    META("Meta"),
    NARINO("Nariño"),
    NORTE_DE_SANTANDER("Norte de Santander"),
    PUTUMAYO("Putumayo"),
    QUINDIO("Quindío"),
    RISARALDA("Risaralda"),
    SAN_ANDRES_Y_PROVIDENCIA("San Andrés y Providencia"),
    SANTANDER("Santander"),
    SUCRE("Sucre"),
    TOLIMA("Tolima"),
    VALLE_DEL_CAUCA("Valle del Cauca"),
    VAUPES("Vaupés"),
    VICHADA("Vichada");

    final String name;

}
