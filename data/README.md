# Data & Import Scripts

## config.py
This file prepares the data files for loading in the database by extracting their data and writing it in csv form.

## load.py
This file loads the data from the csv files into the database

Make sure to update:

```
cnx = mysql.connector.connect(user='root',
                              host='localhost')
```
in load_files.py script with your configured authentication parameters


