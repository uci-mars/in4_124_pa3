import mysql.connector
import csv
 cnx = mysql.connector.connect(user='root', password='bl0b-39',
                              host='localhost',
                              database='s2020Iae')
# cnx = mysql.connector.connect(user='root', password='',
#                               host='localhost',
#                               database='in4_124')
#
cursor = cnx.cursor()

add_zip = ("INSERT INTO zipCode (zipCode, stateName, city) "
               "VALUES (%s, %s, %s)")
with open('zip_codes.csv', 'r') as csv_file:
    csv_reader = csv.reader(csv_file)
    next(csv_reader)

    for line in csv_reader:
        csvData = (int(line[0]), line[1], line[2])
        #print(csvData)
        cursor.execute(add_zip, csvData)
        cnx.commit()

 cursor.close()
 cnx.close()