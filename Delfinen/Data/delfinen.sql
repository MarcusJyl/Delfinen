drop database if exists delfinen;
CREATE DATABASE delfinen;

use delfinen;

CREATE TABLE medlemmer (
   medlems_id int(11) NOT NULL AUTO_INCREMENT,
   medlems_navn varchar(255) NOT NULL,
   medlems_fødselsdato DATE NOT NULL,
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
 
  CREATE TABLE træning (
   trænings_id int(11) NOT NULL,
   træning_dato varchar(255) not null,
   senior_træning boolean not null,
   konkurance_svømmer boolean not null,
   PRIMARY KEY (trænings_id)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
 
  CREATE TABLE resultater (
   trænings_id int(11) NOT NULL,
   medlems_id int(11) not null,
   tid varchar(255) not null,
   diciplin varchar(255) not null
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
   CREATE TABLE hold (
   hold_id int(11) NOT NULL,
   medlems_id int(11) not null
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
  CREATE TABLE stævne (
   stævne_id int(11) NOT NULL AUTO_INCREMENT,
   stævne_navn varchar(255) NOT NULL,
   dato varchar(255) NOT NULL,
   placering int(11) NOT NULL,
   PRIMARY KEY (stævne_id)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
 