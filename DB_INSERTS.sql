/*==============================================================*/
/* INSERTS                                                      */
/*==============================================================*/
insert into cia
values (
        sec_cia.nextval,
        'Devenco',
        'Dev',
        '1'
    );
insert into cia
values (
        sec_cia.nextval,
        'Maverik',
        'Mav',
        '1'
    );
insert into cia
values (
        sec_cia.nextval,
        'Donatello Space',
        'Don',
        '1'
    );
insert into persona
values (
        1,
        sec_persona.nextval,
        1,
        'Devenco',
        'Dev',
        'DevSer',
        'DevSer',
        '1'
    );
insert into cliente
values (
        1,
        sec_persona.currval,
        '52185059242',
        '1'
    );
insert into persona
values (
        2,
        sec_persona.nextval,
        0,
        'Jorge Vargas Mozz',
        'Jvargas',
        'Jvargas',
        'Jvargas',
        '1'
    );
insert into cliente
values (
        2,
        sec_persona.currval,
        '74928122807',
        '1'
    );
insert into persona
values (
        3,
        sec_persona.nextval,
        1,
        'PVN',
        'PVN',
        'PVN',
        'PVN',
        '1'
    );
insert into cliente
values (
        3,
        sec_persona.currval,
        '31972155442',
        '1'
    );
insert into persona
values (
        1,
        sec_persona.nextval,
        1,
        'JPVD',
        'PVD',
        'PVD',
        'PVD',
        '1'
    );
insert into empleado
values (
        1,
        sec_persona.currval,
        '3003001 Daisy Dr',
        '976351455',
        'Futbol',
        null,
        '3-8-1997',
        '74130919',
        '224096',
        '22-10-2010',
        '1',
        '1',
        'Observacion 1',
        1,
        'lawrence.cruz@gmail.com',
        '1'
    );
insert into persona
values (
        2,
        sec_persona.nextval,
        1,
        'Trafic Clay',
        'Clay	',
        'Clay',
        'Clay',
        '1'
    );
insert into empleado
values (
        2,
        sec_persona.currval,
        '595 Eason Rd',
        '907521493',
        'Voley',
        null,
        '3-4-1998',
        '75500041',
        '224097',
        '22-10-1990',
        '1',
        '2',
        'Observacion 2',
        2,
        'micheal.brown@gmail.com',
        '1'
    );
insert into persona
values (
        3,
        sec_persona.nextval,
        1,
        'Lab Pelaez',
        'LabP',
        'LabP',
        'LabP',
        '1'
    );
insert into empleado
values (
        3,
        sec_persona.currval,
        '8769 W Belt Line Rd',
        '905704997',
        'Basquet',
        null,
        '2-4-1984',
        '78073872',
        '224098',
        '22-10-2020',
        '0',
        '3',
        'Observacion 3',
        3,
        'candice.berry@gmail.com',
        '1'
    );
insert into persona
values (
        1,
        sec_persona.nextval,
        0,
        'Moncada',
        'Mon',
        'Mon',
        'Mon',
        '1'
    );
insert into proveedor
values (
        1,
        sec_persona.currval,
        '46380575736',
        '1'
    );
insert into persona
values (
        2,
        sec_persona.nextval,
        1,
        'Mun. Lima',
        'ML',
        'ML',
        'ML',
        '1'
    );
insert into proveedor
values (
        2,
        sec_persona.currval,
        '40767064641',
        '1'
    );
insert into persona
values (
        3,
        sec_persona.nextval,
        1,
        'Consorcio Sierra Sur',
        'ConSS',
        'ConSS',
        'ConSS',
        '1'
    );
insert into proveedor
values (
        3,
        sec_persona.currval,
        '11124638262',
        '1'
    );
insert into persona
values (
        1,
        sec_persona.nextval,
        1,
        'Consorcio Mar',
        'ConD',
        'Emape',
        'Ema',
        '1'
    );
insert into empresa_vta
values (
        1,
        sec_persona.currval,
        '46380575736',
        '1',
        '20-09-2018',
        '20-05-2023',
        null,
        'Consorcio: Empresa cosapi 40%, empresa ing2 30%, Dev. 30%.',
        '1'
    );
insert into persona
values (
        2,
        sec_persona.nextval,
        1,
        'Consorcio Sol',
        'ConD',
        'Emape',
        'Ema',
        '1'
    );
insert into empresa_vta
values (
        2,
        sec_persona.currval,
        '40767064641',
        '1',
        '20-09-2020',
        '20-05-2021',
        null,
        'Consorcio',
        '1'
    );
insert into persona
values (
        3,
        sec_persona.nextval,
        1,
        'Mun. Pasco',
        'ConD',
        'Emape',
        'Ema',
        '1'
    );
insert into empresa_vta
values (
        3,
        sec_persona.currval,
        '11124638262',
        '0',
        '20-09-2018',
        '20-05-2023',
        null,
        'Empresa Devenco',
        '1'
    );
insert into proyecto
values (
        1,
        sec_proyecto.nextval,
        'Consorcio Desarrollo',
        4,
        - 999,
        10,
        - 999,
        1,
        '-',
        '-',
        '20-09-2019',
        0,
        0,
        '-',
        0,
        0,
        0,
        1,
        '20-09-2020',
        9874.25,
        - 999,
        - 999,
        - 999,
        - 999,
        18604.12,
        17,
        19604.12,
        - 999,
        '-',
        '-',
        '-',
        '01-01-2021',
        'RUTA_DOC',
        2019,
        2026,
        0,
        null,
        - 1,
        1,
        'Observacion de precio alta.',
        '1'
    );
insert into proyecto
values (
        2,
        sec_proyecto.nextval,
        'Consorcio Sierra Sur',
        5,
        - 999,
        11,
        - 999,
        2,
        '-',
        '-',
        '21-09-2017',
        0,
        0,
        '-',
        0,
        0,
        0,
        2,
        '21-09-2018',
        7874.22,
        - 999,
        - 999,
        - 999,
        - 999,
        19604.12,
        18,
        20604.12,
        - 999,
        '-',
        '-',
        '-',
        '01-01-2021',
        'RUTA_DOC',
        2017,
        2024,
        0,
        null,
        - 1,
        1,
        'Observacion de precio alta.',
        '1'
    );
insert into proyecto
values (
        3,
        sec_proyecto.nextval,
        'Consorcio Lima',
        6,
        - 999,
        12,
        - 999,
        3,
        '-',
        '-',
        '17-02-2021',
        0,
        0,
        '-',
        0,
        0,
        0,
        3,
        '17-02-2022',
        17674.23,
        - 999,
        - 999,
        - 999,
        - 999,
        22604.12,
        19,
        23604.12,
        - 999,
        '-',
        '-',
        '-',
        '01-01-2021',
        'RUTA_DOC',
        2021,
        2028,
        0,
        null,
        - 1,
        1,
        'Observacion de precio alta.',
        '0'
    );
insert into tabs
values ('1', 'Unidad de medida', 'Umed', '1');
insert into tabs
values ('2', 'Moneda', 'Mon', '1');
insert into tabs
values ('3', 'Desembolso', 'Desem', '1');
insert into tabs
values (
        '4',
        'Comprobante de Pago',
        'CompPago',
        '1'
    );
insert into elementos
values ('1', '1', 'UNI', 'UNI', '1');
insert into elementos
values ('1', '2', 'Kilogramo', 'Kg', '1');
insert into elementos
values ('1', '3', 'Metro', 'M', '1');
insert into elementos
values ('2', '1', 'Soles', 'S/', '1');
insert into elementos
values ('2', '2', 'Dólares', '$', '1');
insert into elementos
values ('3', '1', 'Adelanto', 'Adel', '1');
insert into elementos
values ('3', '2', 'Pago', 'Pago', '1');
insert into elementos
values ('4', '1', 'Factura', 'Fact', '1');
insert into elementos
values (
        '4',
        '2',
        'Recibo por Honorarios',
        'RxH',
        '1'
    );
insert into elementos
values ('4', '3', 'Voucher', 'Vou', '1');
insert into partida
values (
        1,
        'I',
        sec_partida_i.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Ingreso',
        '1',
        1,
        '2',
        '2',
        0,
        '1'
    );
insert into partida
values (
        1,
        'I',
        sec_partida_i.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Venta',
        '1',
        1,
        '2',
        '2',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Trafico',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Topografía',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Hidrología',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Suelos',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Puentes',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Estructura 01',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Especialista en Suelos',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Asistente en Suelos',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Obrero',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'Laboratorio de Suelos',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida
values (
        1,
        'E',
        sec_partida_e.nextval,
        to_char(sec_codpartidas.nextval, '99,999,999'),
        'FWD',
        '1',
        1,
        '1',
        '1',
        0,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'I',
        1,
        sec_partida_mezcla_i.nextval,
        0,
        '2',
        '2',
        1000000,
        1,
        1,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'I',
        2,
        sec_partida_mezcla_i.nextval,
        1,
        '2',
        '2',
        2000000,
        2,
        2,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        6,
        sec_partida_mezcla_e.nextval,
        0,
        '1',
        '1',
        1000,
        1,
        1,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        1,
        sec_partida_mezcla_e.nextval,
        6,
        '1',
        '1',
        2000,
        2,
        1,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        2,
        sec_partida_mezcla_e.nextval,
        6,
        '1',
        '1',
        3000,
        2,
        2,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        3,
        sec_partida_mezcla_e.nextval,
        6,
        '1',
        '1',
        4000,
        2,
        3,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        4,
        sec_partida_mezcla_e.nextval,
        6,
        '1',
        '1',
        5000,
        2,
        4,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        5,
        sec_partida_mezcla_e.nextval,
        6,
        '1',
        '1',
        6000,
        2,
        5,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        7,
        sec_partida_mezcla_e.nextval,
        4,
        '1',
        '1',
        7000,
        3,
        1,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        8,
        sec_partida_mezcla_e.nextval,
        4,
        '1',
        '1',
        8000,
        3,
        2,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        9,
        sec_partida_mezcla_e.nextval,
        4,
        '1',
        '1',
        9000,
        3,
        3,
        '1'
    );
insert into partida_mezcla
values (
        1,
        'E',
        10,
        sec_partida_mezcla_e.nextval,
        4,
        '1',
        '1',
        10000,
        3,
        4,
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'I',
        1,
        '10,000,00',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'I',
        2,
        '10,000,01',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        1,
        '10,000,02',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        2,
        '10,000,03',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        3,
        '10,000,04',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        4,
        '10,000,05',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        5,
        '10,000,06',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        6,
        '10,000,07',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        7,
        '10,000,08',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        8,
        '10,000,09',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        9,
        '10,000,10',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
insert into proy_partida
values (
        1,
        1,
        1,
        'E',
        10,
        '10,000,11',
        '1',
        1,
        '1',
        '-1',
        '1',
        '1'
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'I',
        1,
        1,
        SEC_PROY_PARTIDA_MEZCLA_I.nextval,
        0,
        '2',
        '2',
        1,
        1,
        1000000,
        1,
        1000000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'I',
        1,
        2,
        SEC_PROY_PARTIDA_MEZCLA_I.nextval,
        1,
        '2',
        '2',
        2,
        1,
        2000000,
        1,
        2000000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        6,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        0,
        '1',
        '1',
        1,
        1,
        1000,
        1,
        1000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        1,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        6,
        '1',
        '1',
        2,
        1,
        2000,
        1,
        2000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        2,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        6,
        '1',
        '1',
        2,
        2,
        3000,
        1,
        3000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        3,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        6,
        '1',
        '1',
        2,
        3,
        4000,
        1,
        4000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        4,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        6,
        '1',
        '1',
        2,
        4,
        5000,
        1,
        5000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        5,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        6,
        '1',
        '1',
        2,
        5,
        6000,
        1,
        6000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        7,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        4,
        '1',
        '1',
        3,
        1,
        7000,
        1,
        7000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        8,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        4,
        '1',
        '1',
        3,
        2,
        8000,
        1,
        8000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        9,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        4,
        '1',
        '1',
        3,
        3,
        9000,
        1,
        9000
    );
INSERT INTO PROY_PARTIDA_MEZCLA
VALUES(
        1,
        1,
        'E',
        1,
        10,
        SEC_PROY_PARTIDA_MEZCLA_E.nextval,
        4,
        '1',
        '1',
        3,
        4,
        10000,
        1,
        10000
    );