from urllib.request import urlopen
from urllib.request import Request
from bs4 import BeautifulSoup
import requests
import request
import ssl
import re
import pymysql
import datetime
#


# URL = urlopen("https://coop.koreatech.ac.kr/dining/menu.php", context=sslcontext)
# bs = BeautifulSoup(URL.read(), 'html.parser')

# headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36'}
# req = requests.get('https://coop.koreatech.ac.kr/dining/menu.php', headers=headers)
# req.encoding = 'euc-kr'
# html = req.text
# bs = BeautifulSoup(html, 'html.parser')
sslcontext = ssl._create_unverified_context()
url = 'https://coop.koreatech.ac.kr/dining/menu.php'
user_agent = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36'
request = Request(url, headers={'User-Agent': user_agent})
response = urlopen(request, context=sslcontext)
html = response.read()
bs = BeautifulSoup(html, 'html.parser')

name = re.compile('menu-list?.')
menuList = []

for menulist in bs.find_all("td", {"class":name}):
     before = menulist.text
     after = re.sub('[\t\r]', '', before)
     if(after=="\n\xa0\n"):
          menuList.append('-')
     else:
          menuList.append(after)

breakfast = []
lunch = []
dinner = []
print(menuList)

for a in range(3):
     for b in range(6):
          if(a == 0):
               breakfast.append(re.sub('\n\n', '\n', menuList[a*8 + b]))
          elif(a==1):
               lunch.append(re.sub('\n\n', '\n', menuList[a*8 + b]))
          elif(a==2):
               dinner.append(re.sub('\n\n', '\n', menuList[a*8 + b]))

print(breakfast)
print(lunch)
print(dinner)

conn = pymysql.connect(
    host='menulist.cijah1y9hsc9.ap-northeast-2.rds.amazonaws.com',
    port=3306,
    user='admin',
    password='12345678',
    db='dailymenu',
    charset='utf8')
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