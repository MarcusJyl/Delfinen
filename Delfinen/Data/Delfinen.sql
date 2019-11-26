drop database if exists Delfinen;
CREATE DATABASE Delfinen;

use Delfinen;

CREATE TABLE `medlemmer` (
   `medlems_id` int(11) NOT NULL AUTO_INCREMENT,
   `medlems_navn` varchar(255) NOT NULL,
   `medlems_alder` DATE NOT NULL,
   `medlems_holdtype` varchar(255) NOT NULL,
   PRIMARY KEY (`medlems_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;