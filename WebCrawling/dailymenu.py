from urllib.request import urlopen
from bs4 import BeautifulSoup
import ssl
import re
import pymysql
import datetime

context = ssl._create_unverified_context()
URL = urlopen("https://coop.koreatech.ac.kr/dining/menu.php", context=context)
bs = BeautifulSoup(URL, 'html.parser')

name = re.compile('menu-list?.')
menuList = []

for menulist in bs.find_all("td", {"class":name}):
     before = menulist.text
     after = re.sub('\t', '', before)
     if(after=="\r\n\xa0\r\n"):
          menuList.append('-')
     else:
          menuList.append(after)

breakfast = []
lunch = []
dinner = []

for a in range(3):
     for b in range(6):
          if(a == 0):
               breakfast.append(menuList[a*8 + b])
          elif(a==1):
               lunch.append(menuList[a*8 + b])
          elif(a==2):
               dinner.append(menuList[a*8 + b])

print(breakfast)
print(lunch)
print(dinner)


conn = pymysql.connect(host='localhost', user='root', password='12345', db='daily_menu', charset='utf8')
try:
    with conn.cursor() as curs:
        curs.execute('TRUNCATE TABLE breakfast;')
        curs.execute('TRUNCATE TABLE lunch;')
        curs.execute('TRUNCATE TABLE dinner;')

        sql_b = 'INSERT INTO breakfast(korean, special, onedish, western ,faculty ,subak ) VALUES(%s, %s, %s, %s, %s, %s)'
        sql_l = 'INSERT INTO lunch(korean, special, onedish, western ,faculty ,subak ) VALUES(%s, %s, %s, %s, %s, %s)'
        sql_d = 'INSERT INTO dinner(korean, special, onedish, western ,faculty ,subak ) VALUES(%s, %s, %s, %s, %s, %s)'
        for flag in range(3):
             if(flag == 0):
                  var = tuple(breakfast)
                  curs.execute(sql_b, var)
             elif(flag == 1):
                  var = tuple(lunch)
                  curs.execute(sql_l, var)
             elif(flag == 2):
                  var = tuple(dinner)
                  curs.execute(sql_d, var)

        conn.commit()

finally:
        conn.close()