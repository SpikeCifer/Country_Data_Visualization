import string
import mysql.connector
from mysql.connector import errorcode

TABLES = {}

DB_NAME = 'Chartify'

TABLES['countries'] = ("""CREATE TABLE `countries` (`country_code` varchar(3) NOT NULL,
                                                `country_name` varchar(50) NOT NULL,
                                                PRIMARY KEY (`country_code`))
                                                ENGINE=InnoDB """)
TABLES['indicators'] = ("""CREATE TABLE `indicators` (`indicator_code` varchar(50) NOT NULL,
                                                `indicator_name` varchar(200) NOT NULL,
                                                PRIMARY KEY (`indicator_code`))
                                                ENGINE=InnoDB""")
TABLES['years'] = ("""CREATE TABLE `years` (`year` int NOT NULL,
                                            `five_yr_period` varchar(100) NOT NULL,
                                            `ten_yr_period` varchar(100) NOT NULL,
                                            `twenty_yr_period` varchar(100) NOT NULL,
                                            PRIMARY KEY (`year`))
                                            ENGINE=InnoDB""")
TABLES['measurements'] = ("""CREATE TABLE `measurements` ( `country_code` varchar(3) NOT NULL,
                                                    `indicator_code` varchar(50) NOT NULL,
                                                    `year` int NOT NULL,
                                                    `measurement` DOUBLE,
                                                    PRIMARY KEY (`country_code`,`indicator_code`,`year`),
                                                    KEY `indicator_code` (`indicator_code`),
                                                    KEY `year` (`year`),
                                                    CONSTRAINT `measurements_ibfk_1` FOREIGN KEY (`country_code`) REFERENCES `countries` (`country_code`),
                                                    CONSTRAINT `measurements_ibfk_2` FOREIGN KEY (`indicator_code`) REFERENCES `indicators` (`indicator_code`),
                                                    CONSTRAINT `measurements_ibfk_3` FOREIGN KEY (`year`) REFERENCES `years` (`year`))
                                                    ENGINE=InnoDB""")

cnx = mysql.connector.connect(user='root',
                              host='localhost')
cursor = cnx.cursor()
cursor.execute("DROP DATABASE IF EXISTS Chartify;")
cursor.execute("CREATE DATABASE Chartify;")


def create_database(cursor):
    try:
        cursor.execute(
            "CREATE DATABASE {} DEFAULT CHARACTER SET 'utf8'".format(DB_NAME))
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
    cursor.execute("""LOAD DATA INFILE 'C://ProgramData//MySQL//MySQL Server 8.0//Uploads//{0}'
                        INTO TABLE {1}
                        FIELDS TERMINATED BY ',' ENCLOSED BY '"';""".format(filename, tablename))
    print(f"Loaded {filename} csv to {tablename} table.")
    cnx.commit()
    cursor.close()

load_file_into_table(filename="countries.csv", tablename="countries")
load_file_into_table(filename="indicators.csv", tablename="indicators")
load_file_into_table(filename="years.csv", tablename="years")
load_file_into_table(filename="measurements.csv", tablename="measurements")
cnx.close()
