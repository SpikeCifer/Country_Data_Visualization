import openpyxl as xl
import csv
import sys
import os


def create_countries_file():
    filepath = "Configured Data/countries.xls"
    wb = xl.Workbook()

def main():
    for filename in os.listdir("Data"):
        with open(os.path.join("Data", filename), 'r') as f:
            text = f.read()
            print(text)
    
if __name__ == '__main__':
    main()