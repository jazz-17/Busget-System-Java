alter session set nls_date_format = 'DD-MM-YY';

/*==============================================================*/
/* DROPS                                                        */
/*==============================================================*/
drop table proyecto cascade constraints;

drop table empleado cascade constraints;

drop table cliente cascade constraints;

drop table proveedor cascade constraints;

drop table empresa_vta cascade constraints;

drop table persona cascade constraints;

drop table cia cascade constraints;

drop table partida cascade constraints;

drop table partida_mezcla cascade constraints;

drop table proy_partida cascade constraints;

drop table proy_partida_mezcla cascade constraints;

drop table dproy_partida_mezcla cascade constraints;

drop table elementos cascade constraints;

drop table tabs cascade constraints;

drop table comp_pagocab cascade constraints;

drop table comp_pagodet cascade constraints;

drop table vtacomp_pagocab cascade constraints;

drop table vtacomp_pagodet cascade constraints;

drop table flujocaja cascade constraints;

drop table flujocaja_det cascade constraints;

/*==============================================================*/
/* DROPS - SECUENCIAS                                           */
/*==============================================================*/
drop sequence sec_cia;

drop sequence sec_persona;

drop sequence sec_proyecto;

drop sequence sec_tabs;

--drop sequence SEC_ELEMENTOS;
drop sequence sec_partida_e;

drop sequence sec_partida_i;

drop sequence sec_codpartidas;

drop sequence sec_partida_mezcla_e;

drop sequence sec_partida_mezcla_i;

drop sequence sec_proy_partida_mezcla_e;

drop sequence sec_proy_partida_mezcla_i;

--drop sequence SEC_DPROY_PARTIDA_MEZCLA;
--drop sequence SEC_NRO_CP;
drop sequence sec_nro_pago_vta;

drop sequence sec_dproy_partida_mezcla_adelanto;

drop sequence sec_dproy_partida_mezcla_e;

drop sequence sec_dproy_partida_mezcla_i;

drop sequence sec_dproy_partida_mezcla_pago;

drop sequence sec_dproy_partida_mezcla_semilla_e;

drop sequence sec_dproy_partida_mezcla_semilla_i;

/*==============================================================*/
/* DROPS - PROCEDIMIENTOS                                       */
/*==============================================================*/
drop procedure insertar_cia;

drop procedure insertar_cliente;

drop procedure insertar_empleado;

drop procedure insertar_empresa_vta;

drop procedure insertar_proveedor;

drop procedure insertar_proyecto;

drop procedure insertar_elementos;

drop procedure insertar_tabs;

drop procedure insertar_partida;

drop procedure insertar_partida_mezcla;

drop procedure insertar_proy_partida;

drop procedure insertar_proy_partida_mezcla;

drop procedure insertar_comp_pagocab;

drop procedure insertar_comp_pagodet;

drop procedure insertar_vtacomp_pagocab;

drop procedure insertar_vtacomp_pagodet;

drop procedure insertar_dproy_partida_mezcla;

drop procedure update_flujocaja_det_unalinea_y_global;

/*==============================================================*/
/* DROPS - TRIGGER                                              */
/*==============================================================*/
/*drop TRIGGER INSERTAR_FLUJOCAJA_CABECERA;*/
/*NO ES NECESARIO PORQUE EL TRIGGER SE DROPEA CON LA TABLA*/
/*==============================================================*/
/* DROPS - FUNCIONES                                            */
/*==============================================================*/
drop function getpadre;

drop function duracionproyecto;

/*==============================================================*/
/* TABLAS                                                       */
/*==============================================================*/
create table cia (
	codcia   number(6) not null,
	descia   varchar2(100) not null,
	descorta varchar2(20) not null,
	vigente  varchar2(1) not null,
	constraint cia_pk primary key ( codcia )
);

create table persona (
	codcia      number(6) not null,
	codpersona  number(6) not null,
	tippersona  varchar2(1) not null,
	despersona  varchar2(100) not null,
	descorta    varchar2(30) not null,
	descalterna varchar2(100) not null,
	descortaalt varchar2(10) not null,
	vigente     varchar2(1) not null,
	constraint cia_persona_pk primary key ( codcia,
	                                        codpersona )
);

create table proveedor (
	codcia       number(6) not null,
	codproveedor number(6) not null,
	nroruc       varchar2(20) not null,
	vigente      varchar2(1) not null,
	constraint proveedor_pk primary key ( codcia,
	                                      codproveedor )
);

create table cliente (
	codcia     number(6) not null,
	codcliente number(6) not null,
	nroruc     varchar2(20) not null,
	vigente    varchar2(1) not null,
	constraint cliente_pk primary key ( codcia,
	                                    codcliente )
);

create table empresa_vta (
	codcia          number(6) not null,
	ciacontrata     number(6) not null,
	nroruc          varchar2(20) not null,
	flgempconsorcio varchar2(1) not null,
	fecini          date not null,
	fecfin          date not null,
	codempresa      number(6),
	observac        varchar2(2000) not null,
	vigente         varchar2(1) not null,
	constraint empresa_vta_pk primary key ( codcia,
	                                        ciacontrata )
);

create table empleado (
	codcia      number(6) not null,
	codempleado number(6) not null,
	direcc      varchar2(100) not null,
	celular     varchar2(33) not null,
	hobby       varchar2(2000) not null,
	foto        blob,
	fecnac      date not null,
	dni         varchar2(20) not null,
	nrocip      varchar2(10) not null,
	feccipvig   date not null,
	liccond     varchar2(1) not null,
	flgempliea  varchar2(1) not null,
	observac    varchar2(300) not null,
	codcargo    number(4) not null,
	email       varchar2(100) not null,
	vigente     varchar2(1) not null,
	constraint empleado_pk primary key ( codcia,
	                                     codempleado )
);

create table proyecto (
	codcia          number(6) not null,
	codpyto         number(6) not null,
	nombpyto        varchar2(1000) not null,
	empljefeproy    number(6) not null,
	codcia1         number(6) not null,
	ciacontrata     number(6) not null,
	codcc           number(6) not null,
	codcliente      number(6) not null,
	flgempconsorcio varchar2(1) not null,
	codsnip         varchar2(10) not null,
	fecreg          date not null,
	codfase         number(1) not null,
	codnivel        number(2) not null,
	codfuncion      varchar2(4) not null,
	codsituacion    number(2) not null,
	numinfor        number(1) not null,
	numinforentrg   number(1) not null,
	estpyto         number(2) not null,
	fecestado       date not null,
	valrefer        number(12,2) not null,
	costodirecto    number(12,2) not null,
	costoggen       number(12,2) not null,
	costofinan      number(12,2) not null,
	imputilidad     number(12,2) not null,
	costototsinigv  number(12,2) not null,
	impigv          number(12,2) not null,
	costototal      number(12,2) not null,
	costopenalid    number(12,2) not null,
	coddpto         varchar2(2) not null,
	codprov         varchar2(2) not null,
	coddist         varchar2(2) not null,
	fecviab         date not null,
	rutadoc         varchar2(300) not null,
	annoini         number(4) not null,
	annofin         number(4) not null,
	codobjc         number(2) not null,
	logoproy        blob,
	tabestado       varchar2(3) not null,
	codestado       varchar2(3) not null,
	observac        varchar2(500) not null,
	vigente         varchar2(1) not null,
	constraint proyecto_pk primary key ( codcia,
	                                     codpyto )
);

create table partida (
	codcia      number(6) not null,
	ingegr      varchar2(1) not null,
	codpartida  number(6) not null,
	codpartidas varchar2(12) not null,
	despartida  varchar2(30) not null,
	flgcc       varchar2(1) not null,
	nivel       number(2) not null,
	tunimed     varchar2(3) not null,
	eunimed     varchar2(3) not null,
	semilla     number(5) not null,
	vigente     varchar2(1) not null,
	constraint partida_pk primary key ( codcia,
	                                    ingegr,
	                                    codpartida )
);

create table partida_mezcla (
	codcia        number(6) not null,
	ingegr        varchar2(1) not null,
	codpartida    number(6) not null,
	corr          number(6) not null,
	padcodpartida number(6) not null,
	tunimed       varchar2(3) not null,
	eunimed       varchar2(3) not null,
	costounit     number(9,2) not null,
	nivel         number(5) not null,
	orden         number(5) not null,
	vigente       varchar2(1) not null,
	constraint partida_mezcla_pk primary key ( codcia,
	                                           ingegr,
	                                           codpartida,
	                                           corr )
);

create table proy_partida_mezcla (
	codcia        number(6) not null,
	codpyto       number(6) not null,
	ingegr        varchar2(1) not null,
	nroversion    number(1) not null,
	codpartida    number(6) not null,
	corr          number(6) not null,
	padcodpartida number(6) not null,
    --Cambio de VARCHAR A NUMBER(6)
	tunimed       varchar2(3) not null,
	eunimed       varchar2(3) not null,
	nivel         number(5) not null,
	orden         number(5) not null,
	costounit     number(9,2) not null,
	cant          number(7,3) not null,
	costotot      number(10,2) not null,
	constraint proy_partida_mezcla_pk primary key ( codcia,
	                                                codpyto,
	                                                nroversion,
	                                                ingegr,
	                                                codpartida,
	                                                corr )
);

create table proy_partida (
	codcia      number(6) not null,
	codpyto     number(6) not null,
	nroversion  number(1) not null,
	ingegr      varchar2(1) not null,
	codpartida  number(6) not null,
	codpartidas varchar2(12) not null,
	flgcc       varchar2(1) not null,
	nivel       number(2) not null,
	unimed      varchar2(5) not null,
	tabestado   varchar2(3) not null,
	codestado   varchar2(3) not null,
	vigente     varchar2(1) not null,
	constraint proy_partida_pk primary key ( codcia,
	                                         codpyto,
	                                         nroversion,
	                                         ingegr,
	                                         codpartida )
);

create table elementos (
	codtab   varchar2(3) not null,
	codelem  varchar2(3) not null,
	denele   varchar2(50) not null,
	dencorta varchar2(10) not null,
	vigente  varchar2(1) not null,
	constraint elementos_pk primary key ( codtab,
	                                      codelem )
);

create table tabs (
	codtab   varchar2(3) not null,
	dentab   varchar2(50) not null,
	dencorta varchar2(10) not null,
	vigente  varchar2(1) not null,
	constraint tabs_pk primary key ( codtab )
);

create table dproy_partida_mezcla (
	codcia        number(6) not null,
	codpyto       number(6) not null,
	ingegr        varchar2(1) not null,
	nroversion    number(1) not null,
	codpartida    number(6) not null,
	corr          number(6) not null,
	sec           number(4) not null,
	tdesembolso   varchar2(3) not null,
	edesembolso   varchar2(3) not null,
	nropago       number(2) not null,
	tcomppago     varchar2(3) not null,
	ecomppago     varchar2(3) not null,
	fecdesembolso date not null,
	impdesembneto number(9,2) not null,
	impdesembigv  number(8,2) not null,
	impdesembtot  number(9,2) not null,
	semilla       number(5) not null,
	constraint dproy_partida_mezcla_pk primary key ( codcia,
	                                                 codpyto,
	                                                 ingegr,
	                                                 nroversion,
	                                                 codpartida,
	                                                 corr,
	                                                 sec )
);

create table comp_pagocab (
	codcia       number(6) not null,
	codproveedor number(6) not null,
	nrocp        varchar2(20) not null,
	codpyto      number(6) not null,
	nropago      number(3) not null,
	tcomppago    varchar2(3) not null,
	ecomppago    varchar2(3) not null,
	feccp        date not null,
	tmoneda      varchar2(3) not null,
	emoneda      varchar2(3) not null,
	tipcambio    number(7,4) not null,
	impmo        number(9,2) not null,
	impnetomn    number(9,2) not null,
	impigvmn     number(9,2) not null,
	imptotalmn   number(10,2) not null,
	fotocp       varchar2(60) not null,
	fotoabono    varchar2(60) not null,
	fecabono     date not null,
	desabono     varchar2(1000) not null,
	semilla      number(5) not null,
	tabestado    varchar2(3) not null,
	codestado    varchar2(3) not null,
	constraint comp_pagocab_pk primary key ( codcia,
	                                         codproveedor,
	                                         nrocp )
);

create table comp_pagodet (
	codcia       number(6) not null,
	codproveedor number(6) not null,
	nrocp        varchar2(20) not null,
	sec          number(4) not null,
	ingegr       varchar2(1) not null,
	codpartida   number(6) not null,
	impnetomn    number(9,2) not null,
	impigvmn     number(9,2) not null,
	imptotalmn   number(9,2) not null,
	semilla      number(5) not null,
	constraint comp_pagodet_pk primary key ( codcia,
	                                         codproveedor,
	                                         nrocp,
	                                         sec )
);

create table vtacomp_pagocab (
	codcia     number(6) not null,
	nrocp      varchar2(20) not null,
	codpyto    number(6) not null,
	codcliente number(6) not null,
	nropago    number(3) not null,
	tcomppago  varchar2(3) not null,
	ecomppago  varchar2(3) not null,
	feccp      date not null,
	tmoneda    varchar2(3) not null,
	emoneda    varchar2(3) not null,
	tipcambio  number(7,4) not null,
	impmo      number(9,2) not null,
	impnetomn  number(9,2) not null,
	impigvmn   number(9,2) not null,
	imptotalmn number(10,2) not null,
	fotocp     varchar2(60) not null,
	fotoabono  varchar2(60) not null,
	fecabono   date not null,
	desabono   varchar2(1000) not null,
	semilla    number(5) not null,
	tabestado  varchar2(3) not null,
	codestado  varchar2(3) not null,
	constraint vtacomp_pagocab_pk primary key ( codcia,
	                                            nrocp )
);

create table vtacomp_pagodet (
	codcia     number(6) not null,
	nrocp      varchar2(20) not null,
	sec        number(4) not null,
	ingegr     varchar2(1) not null,
	codpartida number(6) not null,
	impnetomn  number(9,2) not null,
	impigvmn   number(9,2) not null,
	imptotalmn number(9,2) not null,
	semilla    number(5) not null,
	constraint vtacomp_pagodet_pk primary key ( codcia,
	                                            nrocp,
	                                            sec )
);

create table flujocaja (
	codcia           number(6) not null,
	codpyto          number(6) not null,
	ingegr           varchar2(1) not null,
	tipo             varchar2(1) not null,
	codpartida       number(6) not null,
	nivel            number(1) not null,
	orden            number(5) not null,
	desconcepto      varchar2(30) not null,
	desconceptocorto varchar2(10) not null,
	semilla          number(5) not null,
	raiz             number(5) not null,
	tabestado        varchar2(3) not null,
	codestado        varchar2(3) not null,
	vigente          varchar2(1) not null,
	constraint flujocaja_pk primary key ( codcia,
	                                      codpyto,
	                                      ingegr,
	                                      tipo,
	                                      codpartida )
);

create table flujocaja_det (
	anno        number(4) not null,
	codcia      number(6) not null,
	codpyto     number(6) not null,
	ingegr      varchar2(1) not null,
	tipo        varchar2(1) not null,
	codpartida  number(6) not null,
	orden       number(5) not null,
	impini      number(12,2) not null,
	imprealini  number(12,2) not null,
	impene      number(12,2) not null,
	imprealene  number(12,2) not null,
	impfeb      number(12,2) not null,
	imprealfeb  number(12,2) not null,
	impmar      number(12,2) not null,
	imprealmar  number(12,2) not null,
	impabr      number(12,2) not null,
	imprealabr  number(12,2) not null,
	impmay      number(12,2) not null,
	imprealmay  number(12,2) not null,
	impjun      number(12,2) not null,
	imprealjun  number(12,2) not null,
	impjul      number(12,2) not null,
	imprealjul  number(12,2) not null,
	impago      number(12,2) not null,
	imprealago  number(12,2) not null,
	impsep      number(12,2) not null,
	imprealsep  number(12,2) not null,
	impoct      number(12,2) not null,
	imprealoct  number(12,2) not null,
	impnov      number(12,2) not null,
	imprealnov  number(12,2) not null,
	impdic      number(12,2) not null,
	imprealdic  number(12,2) not null,
	impacum     number(12,2) not null,
	imprealacum number(12,2) not null,
	constraint flujocaja_det_pk primary key ( anno,
	                                          codcia,
	                                          codpyto,
	                                          ingegr,
	                                          tipo,
	                                          codpartida )
);

/*==============================================================*/
/* LLAVES FORANEAS                                              */
/*==============================================================*/
/*==============================================================*/
/* PERSONA                                                      */
/*==============================================================*/
alter table persona
	add constraint persona_empresa_vta_fk foreign key ( codcia )
		references cia ( codcia );

/*==============================================================*/
/* PROVEEDOR                                                    */
/*==============================================================*/
alter table proveedor
	add constraint persona_proveedor_fk foreign key ( codcia,
	                                                  codproveedor )
		references persona ( codcia,
		                     codpersona );

/*==============================================================*/
/* CLIENTE                                                      */
/*==============================================================*/
alter table cliente
	add constraint persona_cliente_fk foreign key ( codcia,
	                                                codcliente )
		references persona ( codcia,
		                     codpersona );

/*==============================================================*/
/* EMPRESA_VTA                                                  */
/*==============================================================*/
alter table empresa_vta
	add constraint persona_empresa_vta_cia_fk foreign key ( codcia,
	                                                        ciacontrata )
		references persona ( codcia,
		                     codpersona );

/*==============================================================*/
/* EMPLEADO                                                     */
/*==============================================================*/
alter table empleado
	add constraint persona_empleado_fk foreign key ( codcia,
	                                                 codempleado )
		references persona ( codcia,
		                     codpersona );

/*==============================================================*/
/* PROYECTO                                                     */
/*==============================================================*/
alter table proyecto
	add constraint cia_proyecto_fk foreign key ( codcia )
		references cia ( codcia );

alter table proyecto
	add constraint empleado_proyecto_fk foreign key ( codcia,
	                                                  empljefeproy )
		references empleado ( codcia,
		                      codempleado );

alter table proyecto
	add constraint cliente_proyecto_fk foreign key ( codcia,
	                                                 codcliente )
		references cliente ( codcia,
		                     codcliente );

alter table proyecto
	add constraint empresa_vta_proyecto_fk foreign key ( codcia,
	                                                     ciacontrata )
		references empresa_vta ( codcia,
		                         ciacontrata );

/*==============================================================*/
/* PARTIDA                                                      */
/*==============================================================*/
alter table partida
	add constraint cia_partidafk foreign key ( codcia )
		references cia ( codcia );

/*==============================================================*/
/* PROY_PARTIDA                                                 */
/*==============================================================*/
alter table proy_partida
	add constraint proyecto_proy_partida_fk foreign key ( codcia,
	                                                      codpyto )
		references proyecto ( codcia,
		                      codpyto );

alter table proy_partida
	add constraint partida_proy_partida_fk foreign key ( codcia,
	                                                     ingegr,
	                                                     codpartida )
		references partida ( codcia,
		                     ingegr,
		                     codpartida );

/*ALTER TABLE PROY_PARTIDA ADD CONSTRAINT ESTADO_PROY_PARTIDA_FK
FOREIGN KEY (TabEstado,CodEstado)
REFERENCES ESTADO (TabEstado,CodEstado);*/
/*==============================================================*/
/* PARTIDA_MEZCLA                                               */
/*==============================================================*/
alter table partida_mezcla
	add constraint partida_partida_mezcla_fk foreign key ( codcia,
	                                                       ingegr,
	                                                       codpartida )
		references partida ( codcia,
		                     ingegr,
		                     codpartida );

alter table partida_mezcla
	add constraint elementos_partida_mezcla_fk foreign key ( tunimed,
	                                                         eunimed )
		references elementos ( codtab,
		                       codelem );

/*==============================================================*/
/* PROY_PARTIDA_MEZCLA                                          */
/*==============================================================*/
alter table proy_partida_mezcla
	add constraint proy_partida_proy_partida_mezcla_fk foreign key ( codcia,
	                                                                 codpyto,
	                                                                 nroversion,
	                                                                 ingegr,
	                                                                 codpartida )
		references proy_partida ( codcia,
		                          codpyto,
		                          nroversion,
		                          ingegr,
		                          codpartida );

alter table proy_partida_mezcla
	add constraint elementos_proy_partida_mezcla_fk foreign key ( tunimed,
	                                                              eunimed )
		references elementos ( codtab,
		                       codelem );

/*==============================================================*/
/* DPROY_PARTIDA_MEZCLA                                          */
/*==============================================================*/
alter table dproy_partida_mezcla
	add constraint proy_partida_mezcla_dproy_partida_mezcla_fk foreign key ( codcia,
	                                                                         codpyto,
	                                                                         ingegr,
	                                                                         nroversion,
	                                                                         codpartida,
	                                                                         corr )
		references proy_partida_mezcla ( codcia,
		                                 codpyto,
		                                 ingegr,
		                                 nroversion,
		                                 codpartida,
		                                 corr );

alter table dproy_partida_mezcla
	add constraint elementos_dproy_partida_mezcla_desembolso_fk foreign key ( tdesembolso,
	                                                                          edesembolso )
		references elementos ( codtab,
		                       codelem );

alter table dproy_partida_mezcla
	add constraint elementos_dproy_partida_mezcla_comprobante_fk foreign key ( tcomppago,
	                                                                           ecomppago )
		references elementos ( codtab,
		                       codelem );

/*==============================================================*/
/* ELEMENTOS                                                    */
/*==============================================================*/
alter table elementos
	add constraint elementos_tabs_fk foreign key ( codtab )
		references tabs ( codtab );

/*==============================================================*/
/* TABS                                                         */
/*==============================================================*/
/*NO TIENE*/
/*==============================================================*/
/* COMP_PAGOCAB                                                 */
/*==============================================================*/
alter table comp_pagocab
	add constraint comp_pagocab_proveedor_fk foreign key ( codcia,
	                                                       codproveedor )
		references proveedor ( codcia,
		                       codproveedor );

alter table comp_pagocab
	add constraint comp_pagocab_elementos_fk foreign key ( tmoneda,
	                                                       emoneda )
		references elementos ( codtab,
		                       codelem );

alter table comp_pagocab
	add constraint comp_pagocab_elementos_2_fk foreign key ( tcomppago,
	                                                         ecomppago )
		references elementos ( codtab,
		                       codelem );

alter table comp_pagocab
	add constraint comp_pagocab_proyecto_fk foreign key ( codcia,
	                                                      codpyto )
		references proyecto ( codcia,
		                      codpyto );

/* FALTA LLAVE FORANEA DE ESTADOS (TABLA INEXISTENTE)*/
/*==============================================================*/
/* COMP_PAGODET                                                 */
/*==============================================================*/
alter table comp_pagodet
	add constraint comp_pagodet_comp_pagocab_fk foreign key ( codcia,
	                                                          codproveedor,
	                                                          nrocp )
		references comp_pagocab ( codcia,
		                          codproveedor,
		                          nrocp );

alter table comp_pagodet
	add constraint comp_pagodet_partida_fk foreign key ( codcia,
	                                                     ingegr,
	                                                     codpartida )
		references partida ( codcia,
		                     ingegr,
		                     codpartida );

/*==============================================================*/
/* VTACOMP_PAGOCAB                                              */
/*==============================================================*/
alter table vtacomp_pagocab
	add constraint vtacomp_pagocab_cliente_fk foreign key ( codcia,
	                                                        codcliente )
		references cliente ( codcia,
		                     codcliente );

alter table vtacomp_pagocab
	add constraint vtacomp_pagocab_elementos_fk foreign key ( tmoneda,
	                                                          emoneda )
		references elementos ( codtab,
		                       codelem );

alter table vtacomp_pagocab
	add constraint vtacomp_pagocab_elementos_2_fk foreign key ( tcomppago,
	                                                            ecomppago )
		references elementos ( codtab,
		                       codelem );

alter table vtacomp_pagocab
	add constraint vtacomp_pagocab_proyecto_fk foreign key ( codcia,
	                                                         codpyto )
		references proyecto ( codcia,
		                      codpyto );

/* FALTA LLAVE FORANEA DE ESTADOS (TABLA INEXISTENTE)*/
/*==============================================================*/
/* VTACOMP_PAGODET                                              */
/*==============================================================*/
alter table vtacomp_pagodet
	add constraint vtacomp_pagodet_vtacomp_pagocab_fk foreign key ( codcia,
	                                                                nrocp )
		references vtacomp_pagocab ( codcia,
		                             nrocp );

alter table vtacomp_pagodet
	add constraint vtacomp_pagodet_partida_fk foreign key ( codcia,
	                                                        ingegr,
	                                                        codpartida )
		references partida ( codcia,
		                     ingegr,
		                     codpartida );

/*==============================================================*/
/* FLUJOCAJA                                           */
/*==============================================================*/
alter table flujocaja
	add constraint partida_flujocaja_fk foreign key ( codcia,
	                                                  ingegr,
	                                                  codpartida )
		references partida ( codcia,
		                     ingegr,
		                     codpartida );

alter table flujocaja
	add constraint proyecto_flujocaja_fk foreign key ( codcia,
	                                                   codpyto )
		references proyecto ( codcia,
		                      codpyto );

/*ALTER TABLE FLUJOCAJA ADD CONSTRAINT ESTADO_FLUJOCAJA_FK
FOREIGN KEY (TabEstado,CodEstado)
REFERENCES ESTADO (TabEstado,CodEstado);*/
/*==============================================================*/
/* FLUJOCAJA_DET                                          */
/*==============================================================*/
alter table flujocaja_det
	add constraint flujocaja_flujocaja_det_fk foreign key ( codcia,
	                                                        codpyto,
	                                                        ingegr,
	                                                        tipo,
	                                                        codpartida )
		references flujocaja ( codcia,
		                       codpyto,
		                       ingegr,
		                       tipo,
		                       codpartida );

/*==============================================================*/
/* SECUENCIAS                                                   */
/*==============================================================*/
create sequence sec_cia start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_persona start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_proyecto start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_tabs start with 5 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_partida_e start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_partida_i start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_codpartidas start with 10000000 increment by 1 maxvalue 99999999 minvalue 10000000;

create sequence sec_partida_mezcla_e start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_partida_mezcla_i start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_proy_partida_mezcla_e start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_proy_partida_mezcla_i start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_dproy_partida_mezcla_e start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_dproy_partida_mezcla_i start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_dproy_partida_mezcla_pago start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_dproy_partida_mezcla_adelanto start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_dproy_partida_mezcla_semilla_i start with 1 increment by 1 maxvalue 99999 minvalue 1;

create sequence sec_dproy_partida_mezcla_semilla_e start with 1 increment by 1 maxvalue 99999 minvalue 1;

/*create sequence SEC_NRO_CP
start with 1
increment by 1
maxvalue 99999
minvalue 1;*/
create sequence sec_nro_pago_vta start with 1 increment by 1 maxvalue 99999 minvalue 1;

/*==============================================================*/
/* TRIGGERS                                                     */
/*==============================================================*/
create or replace trigger insertar_flujocaja_cabecera before
	insert on proy_partida_mezcla
	for each row
declare
	annoauxiliar     number(10);
	numerodecolumnas number(10);
	cursor cursor_annos is
	select n
	  from (
		select distinct rownum n
		  from dual
		connect by
			level <= (
				select annofin
				  from proyecto
				 where codpyto = :new.codpyto
			)
	)
	 where n >= (
		select annoini
		  from proyecto
		 where codpyto = :new.codpyto
	)
	 order by n;

begin
	select count(n)
	  into numerodecolumnas
	  from (
		select distinct rownum n
		  from dual
		connect by
			level <= (
				select annofin
				  from proyecto
				 where codpyto = :new.codpyto
			)
	)
	 where n >= (
		select annoini
		  from proyecto
		 where codpyto = :new.codpyto
	)
	 order by n;



	insert into flujocaja values (
		:new.codcia,
		:new.codpyto,
		:new.ingegr,
		'-',
		:new.codpartida,
		:new.nivel,
		:new.orden,
		'DESCONCEPTO',
		'D_CORTO',
		0,
		0,
		'-1',
		'1',
		'1'
	);

	open cursor_annos;
	loop
		fetch cursor_annos into annoauxiliar;
		insert into flujocaja_det values (
			annoauxiliar,
			:new.codcia,
			:new.codpyto,
			:new.ingegr,
			'-',
			:new.codpartida,
			:new.orden,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0,
			0
		);

		numerodecolumnas := numerodecolumnas - 1;
		exit when numerodecolumnas = 0;
	end loop;

	close cursor_annos;
end;
/ 

/*==============================================================*/
/* FUNCIONES                                                    */
/*==============================================================*/
create or replace function getpadre (
	nrohijo  in flujocaja.codcia%type,
	ing_egr  in flujocaja.ingegr%type,
	cod_pyto in proyecto.codpyto%type
) return number is
	nropadre number;
begin
	select padcodpartida
	  into nropadre
	  from proy_partida_mezcla
	 where codpartida = nrohijo
	   and ingegr = ing_egr
	   and codpyto = cod_pyto;

	return nropadre;
end getpadre;
/

create or replace function duracionproyecto (
	cod_pyto in proyecto.codpyto%type
) return number is
	duracionenanios number;
begin
	select count(n)
	  into duracionenanios
	  from (
		select distinct rownum n
		  from dual
		connect by
			level <= (
				select annofin
				  from proyecto
				 where codpyto = cod_pyto
			)
	)
	 where n >= (
		select annoini
		  from proyecto
		 where codpyto = cod_pyto
	)
	 order by n;

	return duracionenanios;
end duracionproyecto;
/
/*==============================================================*/
/* PROCEDIMIENTOS                                               */
/*==============================================================*/
/*INSERTAR UPDATE_FLUJOCAJA_DET_UNALINEA_Y_GLOBAL*/
create or replace procedure update_flujocaja_det_unalinea_y_global (
	cod_cia         in flujocaja.codcia%type,
	cod_pyto        in flujocaja.codpyto%type,
	ing_egr         in flujocaja.ingegr%type,
	cod_partida     in flujocaja.codpartida%type,
	costo_total     in comp_pagocab.imptotalmn%type,
	fecha           in vtacomp_pagocab.feccp%type,
	proyectado_real in cliente.vigente%type
) is

	contador            number(20);
	nrodemes            number(2);
	nrodeanio           number(4);
	valorauxiliaractual number(10);
	valorauxiliarpadre  number(10);
	codhijo             number(10);
	column_name         varchar2(10);
begin
	nrodemes  := to_char(
	                   fecha,
	                   'MM'
	            );
	nrodeanio := to_char(
	                    fecha,
	                    'YYYY'
	             );
	if proyectado_real = 'P' then
		for i in 1..12 loop
			column_name :=
				case i
					when 1 then
						'impene'
					when 2 then
						'impfeb'
					when 3 then
						'impmar'
					when 4 then
						'impabr'
					when 5 then
						'impmay'
					when 6 then
						'impjun'
					when 7 then
						'impjul'
					when 8 then
						'impago'
					when 9 then
						'impsep'
					when 10 then
						'impoct'
					when 11 then
						'impnov'
					when 12 then
						'impdic'
				end;

			if nrodemes = i then
				execute immediate 'select '
				                  || column_name
				                  || ' from flujocaja_det where codcia = :1 and codpyto = :2 and ingegr = :3 and codpartida = :4 and anno = :5'
				  into valorauxiliaractual
					using cod_cia,cod_pyto,ing_egr,cod_partida,nrodeanio;

				execute immediate 'update flujocaja_det set '
				                  || column_name
				                  || ' = :1 where codcia = :2 and codpyto = :3 and ingegr = :4 and codpartida = :5 and anno = :6'
					using valorauxiliaractual + costo_total,cod_cia,cod_pyto,ing_egr,cod_partida,nrodeanio;

				codhijo := cod_partida;
				while getpadre(
				              codhijo,
				              ing_egr,
				              cod_pyto
				      ) <> 0 loop
					execute immediate 'select '
					                  || column_name
					                  || ' from flujocaja_det where codcia = :1 and codpyto = :2 and ingegr = :3 and codpartida = :4 and anno = :5'
					  into valorauxiliarpadre
						using cod_cia,cod_pyto,ing_egr,getpadre(
						                                       codhijo,
						                                       ing_egr,
						                                       cod_pyto
						                               ),nrodeanio;

					execute immediate 'update flujocaja_det set '
					                  || column_name
					                  || ' = :1 where codcia = :2 and codpyto = :3 and ingegr = :4 and codpartida = :5 and anno = :6'
						using valorauxiliarpadre + costo_total,cod_cia,cod_pyto,ing_egr,getpadre(
						                                                                        codhijo,
						                                                                        ing_egr,
						                                                                        cod_pyto
						                                                                ),nrodeanio;

					codhijo := getpadre(
					                   codhijo,
					                   ing_egr,
					                   cod_pyto
					           );
				end loop;

				if i = 1 then
					update flujocaja_det
					   set
						impacum = impini + impene + impfeb + impmar + impabr + impmay + impjun + impjul + impago + impsep + impoct + impnov + impdic
					 where codcia = cod_cia
					   and codpyto = cod_pyto
					   and ingegr = ing_egr
					   and codpartida = cod_partida
					   and anno = nrodeanio;

					update flujocaja_det f1
					   set
						f1.impini = nvl(
							(
								select f2.impacum
								  from flujocaja_det f2
								 where f2.anno = f1.anno - 1
								    and f2.codpartida = f1.codpartida
								   and f2.ingegr = f1.ingegr
								   and f2.codpyto = f1.codpyto
								   and f2.codcia = f1.codcia
							),
							0
						);
				end if;
			end if;
		end loop;
	end if;
	if proyectado_real = 'R' then
		for i in 1..12 loop
			column_name :=
				case i
					when 1 then
						'imprealene'
					when 2 then
						'imprealfeb'
					when 3 then
						'imprealmar'
					when 4 then
						'imprealabr'
					when 5 then
						'imprealmay'
					when 6 then
						'imprealjun'
					when 7 then
						'imprealjul'
					when 8 then
						'imprealago'
					when 9 then
						'imprealsep'
					when 10 then
						'imprealoct'
					when 11 then
						'imprealnov'
					when 12 then
						'imprealdic'
				end;

			if nrodemes = i then
				execute immediate 'select '
				                  || column_name
				                  || ' from flujocaja_det where codcia = :1 and codpyto = :2 and ingegr = :3 and codpartida = :4 and anno = :5'
				  into valorauxiliaractual
					using cod_cia,cod_pyto,ing_egr,cod_partida,nrodeanio;

				execute immediate 'update flujocaja_det set '
				                  || column_name
				                  || ' = :1 where codcia = :2 and codpyto = :3 and ingegr = :4 and codpartida = :5 and anno = :6'
					using valorauxiliaractual + costo_total,cod_cia,cod_pyto,ing_egr,cod_partida,nrodeanio;

				codhijo := cod_partida;
				while getpadre(
				              codhijo,
				              ing_egr,
				              cod_pyto
				      ) <> 0 loop
					execute immediate 'select '
					                  || column_name
					                  || ' from flujocaja_det where codcia = :1 and codpyto = :2 and ingegr = :3 and codpartida = :4 and anno = :5'
					  into valorauxiliarpadre
						using cod_cia,cod_pyto,ing_egr,getpadre(
						                                       codhijo,
						                                       ing_egr,
						                                       cod_pyto
						                               ),nrodeanio;

					execute immediate 'update flujocaja_det set '
					                  || column_name
					                  || ' = :1 where codcia = :2 and codpyto = :3 and ingegr = :4 and codpartida = :5 and anno = :6'
						using valorauxiliarpadre + costo_total,cod_cia,cod_pyto,ing_egr,getpadre(
						                                                                        codhijo,
						                                                                        ing_egr,
						                                                                        cod_pyto
						                                                                ),nrodeanio;

					codhijo := getpadre(
					                   codhijo,
					                   ing_egr,
					                   cod_pyto
					           );
				end loop;

				if i = 1 then
					update flujocaja_det
					   set
						impacum = impini + impene + impfeb + impmar + impabr + impmay + impjun + impjul + impago + impsep + impoct + impnov + impdic
					 where codcia = cod_cia
					   and codpyto = cod_pyto
					   and ingegr = ing_egr
					   and codpartida = cod_partida
					   and anno = nrodeanio;

					update flujocaja_det f1
					   set
						f1.impini = nvl(
							(
								select f2.impacum
								  from flujocaja_det f2
								 where f2.anno = f1.anno - 1
								    and f2.codpartida = f1.codpartida
								   and f2.ingegr = f1.ingegr
								   and f2.codpyto = f1.codpyto
								   and f2.codcia = f1.codcia
							),
							0
						);
				end if;
			end if;
		end loop;
	end if;

	contador  := duracionproyecto(cod_pyto);
	while ( contador > 0 ) loop
		contador := contador - 1;
		update flujocaja_det f1
		   set
			f1.impini = nvl(
				(
					select f2.impacum
					  from flujocaja_det f2
					 where f2.anno =(f1.anno - 1)
					   and f2.codpartida = f1.codpartida
					   and f2.ingegr = f1.ingegr
					   and f2.codpyto = f1.codpyto
					   and f2.codcia = f1.codcia
				),
				0
			);

		update flujocaja_det f1
		   set
			f1.imprealini = nvl(
				(
					select f2.imprealacum
					  from flujocaja_det f2
					 where f2.anno =(f1.anno - 1)
					   and f2.codpartida = f1.codpartida
					   and f2.ingegr = f1.ingegr
					   and f2.codpyto = f1.codpyto
					   and f2.codcia = f1.codcia
				),
				0
			);

		update flujocaja_det
		   set
			impacum = impini + impene + impfeb + impmar + impabr + impmay + impjun + impjul + impago + impsep + impoct + impnov + impdic
			;

		update flujocaja_det
		   set
			imprealacum = imprealini + imprealene + imprealfeb + imprealmar + imprealabr + imprealmay + imprealjun + imprealjul + imprealago
			+ imprealsep + imprealoct + imprealnov + imprealdic;

	end loop;

end update_flujocaja_det_unalinea_y_global;
/
/*INSERTAR EMPRESA*/
create or replace procedure insertar_empresa_vta (
	codcia    in persona.codcia%type,
	desp      in persona.despersona%type,
	descor    in persona.descorta%type,
	descalt   in persona.descalterna%type,
	descoralt in persona.descortaalt%type,
	vig       in persona.vigente%type,
	ruc       in empresa_vta.nroruc%type,
	consorcio in empresa_vta.flgempconsorcio%type,
	ini       in empresa_vta.fecini%type,
	fin       in empresa_vta.fecfin%type,
	obs       in empresa_vta.observac%type
) is
begin
	insert into persona values (
		codcia,
		sec_persona.nextval,
		'2',
		desp,
		descor,
		descalt,
		descoralt,
		vig
	);

	insert into empresa_vta values (
		codcia,
		sec_persona.currval,
		ruc,
		consorcio,
		ini,
		fin,
		null,
		obs,
		vig
	);

end;
/
/*INSERTAR EMPLEADO*/
create or replace procedure insertar_empleado (
	cia       in persona.codcia%type,
	tip       in persona.tippersona%type,
	desp      in persona.despersona%type,
	descor    in persona.descorta%type,
	descalt   in persona.descalterna%type,
	descoralt in persona.descortaalt%type,
	vig       in persona.vigente%type,
	dir       in empleado.direcc%type,
	cel       in empleado.celular%type,
	hob       in empleado.hobby%type,
	fot       in empleado.foto%type,
	nac       in empleado.fecnac%type,
	iden      in empleado.dni%type,
	cip       in empleado.nrocip%type,
	cipvig    in empleado.feccipvig%type,
	cond      in empleado.liccond%type,
	flg       in empleado.flgempliea%type,
	obs       in empleado.observac%type,
	codcar    in empleado.codcargo%type,
	correo    in empleado.email%type
) is
begin
	insert into persona values (
		cia,
		sec_persona.nextval,
		tip,
		desp,
		descor,
		descalt,
		descoralt,
		vig
	);

	insert into empleado values (
		cia,
		sec_persona.currval,
		dir,
		cel,
		hob,
		fot,
		nac,
		iden,
		cip,
		cipvig,
		cond,
		flg,
		obs,
		codcar,
		correo,
		vig
	);

end;
/
/*INSERTAR PROVEEDOR*/
create or replace procedure insertar_proveedor (
	codc      in persona.codcia%type,
	tip       in persona.tippersona%type,
	desp      in persona.despersona%type,
	descor    in persona.descorta%type,
	descalt   in persona.descalterna%type,
	descoralt in persona.descortaalt%type,
	vig       in persona.vigente%type,
	ruc       in proveedor.nroruc%type
) is
begin
	insert into persona values (
		codc,
		sec_persona.nextval,
		tip,
		desp,
		descor,
		descalt,
		descoralt,
		vig
	);

	insert into proveedor values (
		codc,
		sec_persona.currval,
		ruc,
		vig
	);

end;
/
/*INSERTAR CIA*/
create or replace procedure insertar_cia (
	codc     in cia.codcia%type,
	des      in cia.descia%type,
	descorta in cia.descorta%type,
	vig      in cia.vigente%type
) is
begin
	insert into cia values (
		sec_cia.nextval,
		des,
		descorta,
		vig
	);

end;
/
/*INSERTAR PROYECTO*/
create or replace procedure insertar_proyecto (
	cod_cia     in proyecto.codcia%type,
	nompy       in proyecto.nombpyto%type,
	jefe        in proyecto.empljefeproy%type,
	ciacont     in proyecto.ciacontrata%type,
	codcli      in proyecto.codcliente%type,
	fecre       in proyecto.fecreg%type,
	estpyt      in proyecto.estpyto%type,
	fecest      in proyecto.fecestado%type,
	valref      in proyecto.valrefer%type,
	costototsin in proyecto.costototsinigv%type,
	igv         in proyecto.impigv%type,
	costot      in proyecto.costototal%type,
	obs         in proyecto.observac%type,
	annoin      in proyecto.annoini%type,
	annofi      in proyecto.annofin%type,
	logo        in proyecto.logoproy%type,
	vigent      in proyecto.vigente%type
) is
begin
	insert into proyecto (
		codcia,
		codpyto,
		nombpyto,
		empljefeproy,
		codcia1,
		ciacontrata,
		codcc,
		codcliente,
		flgempconsorcio,
		codsnip,
		fecreg,
		codfase,
		codnivel,
		codfuncion,
		codsituacion,
		numinfor,
		numinforentrg,
		estpyto,
		fecestado,
		valrefer,
		costodirecto,
		costoggen,
		costofinan,
		imputilidad,
		costototsinigv,
		impigv,
		costototal,
		costopenalid,
		coddpto,
		codprov,
		coddist,
		fecviab,
		rutadoc,
		annoini,
		annofin,
		codobjc,
		logoproy,
		tabestado,
		codestado,
		observac,
		vigente
	) values (
		cod_cia,
		sec_proyecto.nextval,
		nompy,
		jefe,
		- 999,
		ciacont,
		- 999,
		codcli,
		'-',
		'-',
		fecre,
		0,
		0,
		'-',
		0,
		0,
		0,
		estpyt,
		fecest,
		valref,
		- 999,
		- 999,
		- 999,
		- 999,
		costototsin,
		igv,
		costot,
		- 999,
		'-',
		'-',
		'-',
		'01-01-2022',
		'RUTA_DOC',
		annoin,
		annofi,
		0,
		logo,
		'-1',
		'1',
		obs,
		vigent
	);

end;
/
/*INSERTAR CLIENTE*/
create or replace procedure insertar_cliente (
	codcia    in persona.codcia%type,
	desp      in persona.despersona%type,
	descor    in persona.descorta%type,
	descalt   in persona.descalterna%type,
	descoralt in persona.descortaalt%type,
	vig       in persona.vigente%type,
	ruc       in cliente.nroruc%type
) is
begin
	insert into persona values (
		codcia,
		sec_persona.nextval,
		'2',
		desp,
		descor,
		descalt,
		descoralt,
		vig
	);

	insert into cliente values (
		codcia,
		sec_persona.currval,
		ruc,
		vig
	);

end;
/
/*INSERTAR ELEMENTOS*/
create or replace procedure insertar_elementos (
	ctab    in elementos.codtab%type,
	cele    in elementos.codelem%type,
	deele   in elementos.denele%type,
	decorta in elementos.dencorta%type,
	vig     in elementos.vigente%type
) is
begin
	insert into elementos values (
		ctab,
		cele,
		deele,
		decorta,
		vig
	);

end;
/
/*INSERTAR TABS*/
create or replace procedure insertar_tabs (
  /*CTAB IN TABS.CODTAB%TYPE,*/
	detab in tabs.dentab%type,
	decor in tabs.dencorta%type,
	vig   in tabs.vigente%type
) is
begin
	insert into tabs values (
		sec_tabs.nextval,
		detab,
		decor,
		vig
	);

end;
/
/*INSERTAR PARTIDA*/
create or replace noneditionable procedure insertar_partida (
	codcia     in partida.codcia%type,
	ingegre    in partida.ingegr%type,
	despartida in partida.despartida%type,
	tunimed    in partida.tunimed%type,
	eunimed    in partida.eunimed%type,
	vig        in partida.vigente%type
) is
begin
	if ( ingegre = 'E' ) then
		insert into partida values (
			codcia,
			ingegre,
			sec_partida_e.nextval,
			to_char(
				sec_codpartidas.nextval,
				'99,999,999'
			),
			despartida,
			'1',
			1,
			tunimed,
			eunimed,
			1,
			vig
		);

	end if;

	if ( ingegre = 'I' ) then
		insert into partida values (
			codcia,
			ingegre,
			sec_partida_i.nextval,
			to_char(
				sec_codpartidas.nextval,
				'99,999,999'
			),
			despartida,
			'1',
			1,
			tunimed,
			eunimed,
			1,
			vig
		);

	end if;

end;
/
/*INSERTAR PARTIDA_MEZCLA*/
create or replace noneditionable procedure insertar_partida_mezcla (
	codcia  in partida_mezcla.codcia%type,
	ingegre in partida_mezcla.ingegr%type,
	codpar  in partida_mezcla.codpartida%type,
	padcod  in partida_mezcla.padcodpartida%type,
	tunimed in partida_mezcla.tunimed%type,
	eunimed in partida_mezcla.eunimed%type,
	costo   in partida_mezcla.costounit%type,
	nivel   in partida_mezcla.nivel%type,
	orden   in partida_mezcla.orden%type,
	vig     in partida_mezcla.vigente%type
) is
begin
	if ( ingegre = 'E' ) then
		insert into partida_mezcla values (
			codcia,
			ingegre,
			codpar,
			sec_partida_mezcla_e.nextval,
			padcod,
			tunimed,
			eunimed,
			costo,
			nivel,
			orden,
			vig
		);

	end if;

	if ( ingegre = 'I' ) then
		insert into partida_mezcla values (
			codcia,
			ingegre,
			codpar,
			sec_partida_mezcla_i.nextval,
			padcod,
			tunimed,
			eunimed,
			costo,
			nivel,
			orden,
			vig
		);

	end if;

end;
/
/*INSERTAR PROY_PARTIDA*/
create or replace noneditionable procedure insertar_proy_partida (
	codpyto    in proy_partida.codpyto%type,
	nroversion in proy_partida.nroversion%type,
	codcia     in proy_partida.codcia%type,
	ingegre    in proy_partida.ingegr%type,
	codp       in proy_partida.codpartida%type,
	codpar     in proy_partida.codpartidas%type,
	tabe       in proy_partida.tabestado%type,
	code       in proy_partida.codestado%type,
	vig        in proy_partida.vigente%type
) is
begin
	insert into proy_partida values (
		codcia,
		codpyto,
		nroversion,
		ingegre,
		codp,
		codpar,
		'1',
		1,
		'1',
		tabe,
		code,
		vig
	);

end;
/
/*INSERTAR PROY_PARTIDA_MEZCLA*/
create or replace noneditionable procedure insertar_proy_partida_mezcla (
	codc       in proy_partida_mezcla.codcia%type,
	codpyto    in proy_partida_mezcla.codpyto%type,
	nroversion in proy_partida_mezcla.nroversion%type,
	padcod     in proy_partida_mezcla.padcodpartida%type,
	ineg       in proy_partida_mezcla.ingegr%type,
	can        in proy_partida_mezcla.cant%type
) as

	cursor cpm is
	select codpartida,
	       padcodpartida,
	       tunimed,
	       eunimed,
	       costounit,
	       nivel,
	       orden
	  from partida_mezcla
	 where codcia = codc
	   and padcodpartida = padcod
	   and ingegr = ineg;

	cursor cpm2 is
	select codpartida,
	       padcodpartida,
	       tunimed,
	       eunimed,
	       costounit,
	       nivel,
	       orden
	  from partida_mezcla
	 where codcia = codc
	   and codpartida = padcod
	   and padcodpartida = 0
	   and ingegr = ineg;

	v_total proy_partida_mezcla.costotot%type;
begin
	if ( ineg = 'E' ) then
		for r1 in cpm2 loop
			v_total := can * r1.costounit;
			insert into proy_partida_mezcla values (
				codc,
				codpyto,
				ineg,
				nroversion,
				r1.codpartida,
				sec_proy_partida_mezcla_e.nextval,
				r1.padcodpartida,
				r1.tunimed,
				r1.eunimed,
				r1.nivel,
				r1.orden,
				r1.costounit,
				can,
				v_total
			);

		end loop;

		for r2 in cpm loop
			v_total := can * r2.costounit;
			insert into proy_partida_mezcla values (
				codc,
				codpyto,
				ineg,
				nroversion,
				r2.codpartida,
				sec_proy_partida_mezcla_e.nextval,
				r2.padcodpartida,
				r2.tunimed,
				r2.eunimed,
				r2.nivel,
				r2.orden,
				r2.costounit,
				can,
				v_total
			);

		end loop;

	end if;

	if ( ineg = 'I' ) then
		for r3 in cpm2 loop
			v_total := can * r3.costounit;
			insert into proy_partida_mezcla values (
				codc,
				codpyto,
				ineg,
				nroversion,
				r3.codpartida,
				sec_proy_partida_mezcla_i.nextval,
				r3.padcodpartida,
				r3.tunimed,
				r3.eunimed,
				r3.nivel,
				r3.orden,
				r3.costounit,
				can,
				v_total
			);

		end loop;

		for r4 in cpm loop
			v_total := can * r4.costounit;
			insert into proy_partida_mezcla values (
				codc,
				codpyto,
				ineg,
				nroversion,
				r4.codpartida,
				sec_proy_partida_mezcla_i.nextval,
				r4.padcodpartida,
				r4.tunimed,
				r4.eunimed,
				r4.nivel,
				r4.orden,
				r4.costounit,
				can,
				v_total
			);

		end loop;

	end if;

end;
/
/*INSERTAR DPROY_PARTIDA_MEZCLA*/
create or replace procedure insertar_dproy_partida_mezcla (
	codcia       in dproy_partida_mezcla.codcia%type,
	codpyto      in dproy_partida_mezcla.codpyto%type,
	ingegr       in dproy_partida_mezcla.ingegr%type,
	nroversion   in dproy_partida_mezcla.nroversion%type,
	codpart      in dproy_partida_mezcla.codpartida%type,
	corr         in dproy_partida_mezcla.corr%type,
	edesemb      in dproy_partida_mezcla.edesembolso%type,
	ecpago       in dproy_partida_mezcla.ecomppago%type,
	fecdesemb    in dproy_partida_mezcla.fecdesembolso%type,
	impdesemneto in dproy_partida_mezcla.impdesembneto%type,
	impdesemigv  in dproy_partida_mezcla.impdesembigv%type,
	impdesemtot  in dproy_partida_mezcla.impdesembtot%type,
	semi         in dproy_partida_mezcla.semilla%type,
	repeticion   in dproy_partida_mezcla.codcia%type
) is
begin
	if ( ingegr = 'E' ) then
		if ( repeticion = 0 ) then
			if ( edesemb = 1 ) then
				insert into dproy_partida_mezcla values (
					codcia,
					codpyto,
					ingegr,
					nroversion,
					codpart,
					corr,
					sec_dproy_partida_mezcla_e.nextval,
					3,
					edesemb,
					sec_dproy_partida_mezcla_adelanto.nextval,
					4,
					ecpago,
					fecdesemb,
					impdesemneto,
					impdesemigv,
					impdesemtot,
					sec_dproy_partida_mezcla_semilla_e.nextval
				);

			else
				insert into dproy_partida_mezcla values (
					codcia,
					codpyto,
					ingegr,
					nroversion,
					codpart,
					corr,
					sec_dproy_partida_mezcla_e.nextval,
					3,
					edesemb,
					sec_dproy_partida_mezcla_pago.nextval,
					4,
					ecpago,
					fecdesemb,
					impdesemneto,
					impdesemigv,
					impdesemtot,
					sec_dproy_partida_mezcla_semilla_e.nextval
				);

			end if;

		else
			if ( edesemb = 1 ) then
				insert into dproy_partida_mezcla values (
					codcia,
					codpyto,
					ingegr,
					nroversion,
					codpart,
					corr,
					sec_dproy_partida_mezcla_e.nextval,
					3,
					edesemb,
					sec_dproy_partida_mezcla_adelanto.nextval,
					4,
					ecpago,
					fecdesemb,
					impdesemneto,
					impdesemigv,
					impdesemtot,
					semi
				);

			else
				insert into dproy_partida_mezcla values (
					codcia,
					codpyto,
					ingegr,
					nroversion,
					codpart,
					corr,
					sec_dproy_partida_mezcla_e.nextval,
					3,
					edesemb,
					sec_dproy_partida_mezcla_pago.nextval,
					4,
					ecpago,
					fecdesemb,
					impdesemneto,
					impdesemigv,
					impdesemtot,
					semi
				);

			end if;
		end if;
	end if;

	if ( ingegr = 'I' ) then
		if ( repeticion = 0 ) then
			if ( edesemb = 1 ) then
				insert into dproy_partida_mezcla values (
					codcia,
					codpyto,
					ingegr,
					nroversion,
					codpart,
					corr,
					sec_dproy_partida_mezcla_i.nextval,
					3,
					edesemb,
					sec_dproy_partida_mezcla_adelanto.nextval,
					4,
					ecpago,
					fecdesemb,
					impdesemneto,
					impdesemigv,
					impdesemtot,
					sec_dproy_partida_mezcla_semilla_i.nextval
				);

			else
				insert into dproy_partida_mezcla values (
					codcia,
					codpyto,
					ingegr,
					nroversion,
					codpart,
					corr,
					sec_dproy_partida_mezcla_i.nextval,
					3,
					edesemb,
					sec_dproy_partida_mezcla_pago.nextval,
					4,
					ecpago,
					fecdesemb,
					impdesemneto,
					impdesemigv,
					impdesemtot,
					sec_dproy_partida_mezcla_semilla_i.nextval
				);

			end if;

		else
			if ( edesemb = 1 ) then
				insert into dproy_partida_mezcla values (
					codcia,
					codpyto,
					ingegr,
					nroversion,
					codpart,
					corr,
					sec_dproy_partida_mezcla_i.nextval,
					3,
					edesemb,
					sec_dproy_partida_mezcla_adelanto.nextval,
					4,
					ecpago,
					fecdesemb,
					impdesemneto,
					impdesemigv,
					impdesemtot,
					semi
				);

			else
				insert into dproy_partida_mezcla values (
					codcia,
					codpyto,
					ingegr,
					nroversion,
					codpart,
					corr,
					sec_dproy_partida_mezcla_i.nextval,
					3,
					edesemb,
					sec_dproy_partida_mezcla_pago.nextval,
					4,
					ecpago,
					fecdesemb,
					impdesemneto,
					impdesemigv,
					impdesemtot,
					semi
				);

			end if;
		end if;

	end if;

	update_flujocaja_det_unalinea_y_global(
	                                      codcia,
	                                      codpyto,
	                                      ingegr,
	                                      codpart,
	                                      impdesemtot,
	                                      fecdesemb,
	                                      'P'
	);
end;
/
/*INSERTAR COMP_PAGOCAB*/
create or replace noneditionable procedure insertar_comp_pagocab (
	codcia       in comp_pagocab.codcia%type,
	codproveedor in comp_pagocab.codproveedor%type,
	nrocp        in comp_pagocab.nrocp%type,
	codpyto      in comp_pagocab.codpyto%type,
	nropago      in comp_pagocab.nropago%type,
	tcomppago    in comp_pagocab.tcomppago%type,
	ecomppago    in comp_pagocab.ecomppago%type,
	feccp        in comp_pagocab.feccp%type,
	tmoneda      in comp_pagocab.tmoneda%type,
	emoneda      in comp_pagocab.emoneda%type,
	tipcambio    in comp_pagocab.tipcambio%type,
	impmo        in comp_pagocab.impmo%type,
	impnetomn    in comp_pagocab.impnetomn%type,
	impigvmn     in comp_pagocab.impigvmn%type,
	imptotalmn   in comp_pagocab.imptotalmn%type,
	fotocp       in comp_pagocab.fotocp%type,
	fotoabono    in comp_pagocab.fotoabono%type,
	fecabono     in comp_pagocab.fecabono%type,
	desabono     in comp_pagocab.desabono%type,
	semilla      in comp_pagocab.semilla%type,
	tabestado    in comp_pagocab.tabestado%type,
	codestado    in comp_pagocab.codestado%type
) is
begin
	insert into comp_pagocab values (
		codcia,
		codproveedor,
		nrocp,
		codpyto,
		nropago,
		tcomppago,
		ecomppago,
		feccp,
		tmoneda,
		emoneda,
		tipcambio,
		impmo,
		impnetomn,
		impigvmn,
		imptotalmn,
		fotocp,
		fotoabono,
		fecabono,
		desabono,
		semilla,
		tabestado,
		codestado
	);

end;
/
/*INSERTAR INSERTAR_COMP_PAGODET*/
create or replace noneditionable procedure insertar_comp_pagodet (
	codcia            in comp_pagodet.codcia%type,
	codproveedor      in comp_pagodet.codproveedor%type,
	nro_cp            in comp_pagodet.nrocp%type,
	sec               in comp_pagodet.sec%type,
	ingegr            in comp_pagodet.ingegr%type,
	codpartida        in comp_pagodet.codpartida%type,
	impnetomn         in comp_pagodet.impnetomn%type,
	impigvmn          in comp_pagodet.impigvmn%type,
	imptotalmn        in comp_pagodet.imptotalmn%type,
	semill            in comp_pagocab.semilla%type,
	cod_pyto_auxiliar in proyecto.codpyto%type
) is
	fecha_de_cp date;
begin
	insert into comp_pagodet values (
		codcia,
		codproveedor,
		nro_cp,
		sec,
		ingegr,
		codpartida,
		impnetomn,
		impigvmn,
		imptotalmn,
		semill
	);

	select feccp
	  into fecha_de_cp
	  from comp_pagocab
	 where nrocp = nro_cp;

	update_flujocaja_det_unalinea_y_global(
	                                      codcia,
	                                      cod_pyto_auxiliar,
	                                      ingegr,
	                                      codpartida,
	                                      imptotalmn,
	                                      fecha_de_cp,
	                                      'R'
	);
end;
/
/*INSERTAR INSERTAR_VTACOMP_PAGOCAB*/
create or replace noneditionable procedure insertar_vtacomp_pagocab (
	codcia     in vtacomp_pagocab.codcia%type,
	nrocp      in vtacomp_pagocab.nrocp%type,
	codpyto    in vtacomp_pagocab.codpyto%type,
	codcliente in vtacomp_pagocab.codcliente%type,
  --NROPAGO IN VTACOMP_PAGOCAB.nropago%TYPE,
	tcomppago  in vtacomp_pagocab.tcomppago%type,
	ecomppago  in vtacomp_pagocab.ecomppago%type,
	feccp      in vtacomp_pagocab.feccp%type,
	tmoneda    in vtacomp_pagocab.tmoneda%type,
	emoneda    in vtacomp_pagocab.emoneda%type,
	tipcambio  in vtacomp_pagocab.tipcambio%type,
	impmo      in vtacomp_pagocab.impmo%type,
	impnetomn  in vtacomp_pagocab.impnetomn%type,
	impigvmn   in vtacomp_pagocab.impigvmn%type,
	imptotalmn in vtacomp_pagocab.imptotalmn%type,
	fotocp     in vtacomp_pagocab.fotocp%type,
	fotoabono  in vtacomp_pagocab.fotoabono%type,
	fecabono   in vtacomp_pagocab.fecabono%type,
	desabono   in vtacomp_pagocab.desabono%type,
	semilla    in vtacomp_pagocab.semilla%type,
	tabestado  in vtacomp_pagocab.tabestado%type,
	codestado  in vtacomp_pagocab.codestado%type
) is
begin
	insert into vtacomp_pagocab values (
		codcia,
		nrocp,
		codpyto,
		codcliente,
		sec_nro_pago_vta.nextval,
		tcomppago,
		ecomppago,
		feccp,
		tmoneda,
		emoneda,
		tipcambio,
		impmo,
		impnetomn,
		impigvmn,
		imptotalmn,
		fotocp,
		fotoabono,
		fecabono,
		desabono,
		semilla,
		tabestado,
		codestado
	);

end;
/
/*INSERTAR INSERTAR_VTACOMP_PAGODET*/
create or replace noneditionable procedure insertar_vtacomp_pagodet (
	codcia            in vtacomp_pagodet.codcia%type,
	nro_cp            in vtacomp_pagodet.nrocp%type,
	sec               in vtacomp_pagodet.sec%type,
	ingegr            in vtacomp_pagodet.ingegr%type,
	codpartida        in vtacomp_pagodet.codpartida%type,
	impnetomn         in vtacomp_pagodet.impnetomn%type,
	impigvmn          in vtacomp_pagodet.impigvmn%type,
	imptotalmn        in vtacomp_pagodet.imptotalmn%type,
	semill            in vtacomp_pagodet.semilla%type,
	cod_pyto_auxiliar in proyecto.codpyto%type
) is
	fecha_de_cp date;
begin
	insert into vtacomp_pagodet values (
		codcia,
		nro_cp,
		sec,
		ingegr,
		codpartida,
		impnetomn,
		impigvmn,
		imptotalmn,
		semill
	);

	select feccp
	  into fecha_de_cp
	  from vtacomp_pagocab
	 where nrocp = nro_cp;

	update_flujocaja_det_unalinea_y_global(
	                                      codcia,
	                                      cod_pyto_auxiliar,
	                                      ingegr,
	                                      codpartida,
	                                      imptotalmn,
	                                      fecha_de_cp,
	                                      'R'
	);
end;
/


alter table flujocaja_det drop constraint flujocaja_flujocaja_det_fk;
alter table flujocaja_det
	add constraint flujocaja_flujocaja_det_fk foreign key ( codcia,
	                                                        codpyto,
	                                                        ingegr,
	                                                        tipo,
	                                                        codpartida )
		references flujocaja ( codcia,
		                       codpyto,
		                       ingegr,
		                       tipo,
		                       codpartida )
			on delete cascade;

create or replace trigger delete_flujocaja_entries before
	delete on proy_partida_mezcla
	for each row
begin
	delete from flujocaja
	 where codcia = :old.codcia
	   and codpyto = :old.codpyto
	   and ingegr = :old.ingegr
	   and codpartida = :old.codpartida
	   and orden = :old.orden;
end;
/