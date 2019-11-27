drop database if exists delfinen;
CREATE DATABASE delfinen;

use delfinen;

CREATE TABLE medlemmer (
   medlems_id int(11) NOT NULL AUTO_INCREMENT,
   medlems_navn varchar(255) NOT NULL,
   medlems_alder DATE NOT NULL,
   medlems_holdtype varchar(255) NOT NULL,
   medlems_status varchar(255) NOT NULL,
   medlems_kontingent double NOT NULL,
   medlems_kontingent_status boolean NOT NULL,
   PRIMARY KEY (medlems_id)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
 
 CREATE TABLE medlemmers_dicipliner (
   medlems_id int(11) NOT NULL,
   diciplin varchar(255) not null
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;