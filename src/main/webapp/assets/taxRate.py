import mysql.connector
import csv
 cnx = mysql.connector.connect(user='root', password='bl0b-39',
                              host='localhost',
                              database='s2020Iae')
#cnx = mysql.connector.connect(user='root', password='',
#                              host='localhost',
#                              database='in4_124')

cursor = cnx.cursor()
#
add_tax = ("INSERT INTO taxRates (stateName, zipCode, taxRegionName , combinedRate ) "
               "VALUES (%s, %s, %s, %s)")
with open('tax_rates2.csv', 'r') as csv_file:
    csv_reader = csv.reader(csv_file)
    next(csv_reader)

    for line in csv_reader:
        csvData = (line[0], int(line[1]), line[2], float(line[3]))
        #(csvData)
        cursor.execute(add_tax, csvData)
        cnx.commit()

cursor.close()
cnx.close()