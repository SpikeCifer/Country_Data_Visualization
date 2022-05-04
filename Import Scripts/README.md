# Data & Import Scripts

This directory contains the python scripts used for loading country data into the MySQL Database

Make sure to update:

```
cnx = mysql.connector.connect(user='root',
                              host='localhost')
```
in load_files.py script with your configured authentication parameters
