create table warehouse
(
	id serial not null
		constraint warehouse_id_pk
			primary key,
	name varchar(30) not null,
	numberofstorageshelves integer not null
)
;

create unique index "warehouse_ID_uindex"
	on warehouse (id)
;

create index "warehouse_Name_index"
	on warehouse (name)
;

create table storage
(
	id serial not null
		constraint storage_pkey
			primary key,
	numberofshelves integer,
	height double precision,
	width double precision,
	depth double precision,
	warehouseid integer not null
		constraint storage_warehouse_id_fk
			references warehouse (id)
				on update set null on delete set null,
	loadcapacity double precision not null
)
;

create unique index "Storage_ID_uindex"
	on storage (id)
;

create index "Storage_WarehouseID_index"
	on storage (warehouseid)
;

create table shelf
(
	id serial not null
		constraint shelf_pkey
			primary key,
	storageid integer not null
		constraint shelf_storage_id_fk
			references storage
				on update set null on delete set null,
	height double precision not null,
	width double precision not null,
	depth double precision not null,
	loadcapacity double precision not null
)
;

create unique index "Shelf_ID_uindex"
	on shelf (id)
;

create index "Shelf_StorageID_index"
	on shelf (storageid)
;

create table location
(
	id serial not null
		constraint location_pkey
			primary key,
	shelfid integer not null
		constraint location_shelf_id_fk
			references shelf
				on update set null on delete set null,
	height integer not null,
	width integer not null,
	depth integer not null
)
;

create unique index "Location_ID_uindex"
	on location (id)
;

create index "Location_ShelfID_index"
	on location (shelfid)
;

create table package
(
	id serial not null
		constraint package_pkey
			primary key,
	locationid integer
		constraint package_location_id_fk
			references location
				on update set null on delete set null,
	height double precision not null,
	width double precision not null,
	depth double precision not null,
	weight double precision not null
)
;

create unique index "Package_ID_uindex"
	on package (id)
;

create index "Package_LocationID_index"
	on package (locationid)
;

create table product
(
	id serial not null
		constraint product_pkey
			primary key,
	manufacturerid integer not null,
	weight double precision not null,
	quantity integer not null,
	description varchar(200) not null
)
;

create unique index "Product_ID_uindex"
	on product (id)
;

create index "Product_ManufacturerID_index"
	on product (manufacturerid)
;

create index "Product_Description_index"
	on product (description)
;

create table packageproduct
(
	id serial not null
		constraint packageproduct_pkey
			primary key,
	packageid integer not null
		constraint packageproduct_package_id_fk
			references package
				on update set null on delete set null,
	productid integer not null
		constraint packageproduct_product_id_fk
			references product
				on update set null on delete set null
)
;

create unique index "packageProduct_ID_uindex"
	on packageproduct (id)
;

create index "packageProduct_packageID_productID_index"
	on packageproduct (packageid, productid)
;

create table manufacturer
(
	id serial not null
		constraint manufacturer_pkey
			primary key,
	name varchar(50) not null,
	description varchar(200) not null
)
;

create unique index "manufacturer_ID_uindex"
	on manufacturer (id)
;

create index "manufacturer_Name_index"
	on manufacturer (name)
;

alter table product
	add constraint product_manufacturer_id_fk
		foreign key (manufacturerid) references manufacturer
			on update set null on delete set null
;

create function addstorage(numberofshelves integer, height double precision, width double precision, depth double precision, warehousename character varying, loadcapacity double precision) returns void
	language plpgsql
as $$
DECLARE
      warehouseid INT = NULL;
  BEGIN
      SELECT INTO  warehouseid id FROM warehouse WHERE name = warehouseName;

      INSERT INTO storage (numberofshelves, height, width, depth, warehouseid, loadcapacity)
      VALUES (numberofshelves, height, width, depth, warehouseid, loadcapacity );

      --RETURN 0;
  END;
$$
;

SELECT wh.name AS "Warehouse name"
			,'Storage ' || st.id AS "Storage name"
			,'Shelf ' || sh.id AS "Shelf"
			,sh.width AS "Shelf width"
			,'Location' || lo.id AS "Location width"
			FROM warehouse AS wh
			JOIN storage AS st ON st.warehouseid = wh.id
			JOIN shelf AS sh ON sh.storageid = st.id
			LEFT JOIN location AS lo ON lo.shelfid = sh.id
			ORDER BY wh.id, st.id, sh.id;
