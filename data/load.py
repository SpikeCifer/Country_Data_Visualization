#!/usr/bin/env python3
import string
import mysql.connector
from mysql.connector import errorcode

TABLES = {}

DB_NAME = 'Chartify'

TABLES['country'] = (
    """CREATE TABLE `country` (
        `code` varchar(3) NOT NULL,
        `name` varchar(50) NOT NULL,
        PRIMARY KEY (`code`)
    )
    ENGINE=InnoDB """
)

TABLES['indicator'] = (
    """CREATE TABLE `indicator` (
        `code` varchar(50) NOT NULL,
        `name` varchar(200) NOT NULL,
        PRIMARY KEY (`code`)
    )
    ENGINE=InnoDB"""
)

TABLES['time_period'] = (
    """CREATE TABLE `time_period` (
        `year` int NOT NULL,
        `quinquennial` varchar(100) NOT NULL,
        `decade` varchar(100) NOT NULL,
        `vicennial` varchar(100) NOT NULL,
        PRIMARY KEY (`year`)
    )
    ENGINE=InnoDB"""
)
                                            
TABLES['measurements'] = (
    """CREATE TABLE `measurement` (
        `country` varchar(3) NOT NULL,
        `indicator` varchar(50) NOT NULL,
        `year` int NOT NULL,
        `measurement` DOUBLE,
        PRIMARY KEY (`country`,`indicator`,`year`),
        KEY `indicator` (`indicator`),
        KEY `year` (`year`),
        CONSTRAINT `measurements_ibfk_1` FOREIGN KEY (`country`) REFERENCES `country` (`code`),
        CONSTRAINT `measurements_ibfk_2` FOREIGN KEY (`indicator`) REFERENCES `indicator` (`code`),
        CONSTRAINT `measurements_ibfk_3` FOREIGN KEY (`year`) REFERENCES `time_period` (`year`)
    )
    ENGINE=InnoDB"""
)

cnx = mysql.connector.connect(user='root', host='localhost')
cursor = cnx.cursor()
cursor.execute("DROP DATABASE IF EXISTS Chartify;")
cursor.execute("CREATE DATABASE Chartify;")


def create_database(cursor):
    try:
        cursor.execute(
            "CREATE DATABASE {} DEFAULT CHARACTER SET 'utf8'".format(DB_NAME)
        )

    except mysql.connector.Error as err:
        print("Failed creating database: {}".format(err))
        exit(1)


try:
    cursor.execute("USE {}".format(DB_NAME))

except mysql.connector.Error as err:
    print("Database {} does not exists.".format(DB_NAME))

    if err.errno == errorcode.ER_BAD_DB_ERROR:
        create_database(cursor)
        print("Database {} created successfully.".format(DB_NAME))
        cnx.database = DB_NAME
        
    else:
        print(err)
        exit(1)

for table_name in TABLES:
    table_description = TABLES[table_name]
    
    try:
        print("Creating table {}: ".format(table_name), end='')
        cursor.execute(table_description)
    
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    
    else:
        print("OK")

def load_file_into_table(filename: string, tablename: string):
    cursor = cnx.cursor() 
    cursor.execute("""LOAD DATA INFILE '/var/lib/mysql-files/{0}'
                        INTO TABLE {1}
                        FIELDS TERMINATED BY ',' ENCLOSED BY '"';""".format(filename, tablename))
    print(f"Loaded {filename} csv to {tablename} table.")
    cnx.commit()
    cursor.close()

load_file_into_table(filename="countries.csv", tablename="country")
load_file_into_table(filename="indicators.csv", tablename="indicator")
load_file_into_table(filename="years.csv", tablename="time_period")
load_file_into_table(filename="measurements.csv", tablename="measurement")
cnx.close()
