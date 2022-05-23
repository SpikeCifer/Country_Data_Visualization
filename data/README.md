# Data & Import Scripts

## Step 1: Configure_data.py
This file prepares the data files for loading in the database by extracting their data and writing it in csv form.

## Step 2: Copy Files
Copy reated .csv files in the folder that mysql allows loading from

## Step 3: load.py
This file loads the data from the csv files into the database.

Make sure to update:

```
cnx = mysql.connector.connect(user='root',
                              host='localhost')
```
in load.py script with your configured authentication parameters.

Also you might have to update the loading from path:

```
def load_file_into_table(filename: string, tablename: string):
    cursor = cnx.cursor() 
    cursor.execute("""LOAD DATA INFILE '/var/lib/mysql-files/{0}' # This is the load path
                        INTO TABLE {1}
                        FIELDS TERMINATED BY ',' ENCLOSED BY '"';""".format(filename, tablename))
    print(f"Loaded {filename} csv to {tablename} table.")
    cnx.commit()
    cursor.close()
```
## Changing the Indicators
In case you want to change the indicators used, feel free to change the following in **Configure_data.py**
```
SELECTED_INDICATORS = {'SP.DYN.LE00.MA.IN', 'TX.VAL.MRCH.WL.CD', 'SP.POP.2024.FE.5Y', 'SP.DYN.TO65.MA.ZS',
                    'NY.GSR.NFCY.CD', 'EN.ATM.CO2E.SF.ZS', 'EG.ELC.FOSL.ZS', 'MS.MIL.XPND.CD',
                    'NV.MNF.FBTO.ZS.UN', 'EN.URB.LCTY.UR.ZS', 'BX.GSR.GNFS.CD', 'BM.GSR.GNFS.CD'}
```
