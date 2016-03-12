#!C:\Python27\python.exe
# This outputs a copyright notice to a web page

import cgi
import httplib
import urllib
import json
import re
from bs4 import BeautifulSoup
print "Content-Type: text/html\n"
form = cgi.FieldStorage()
data = form.getvalue("q")
id = form.getvalue("id")
f = open('log.txt','w')
#soup = BeautifulSoup(search,'html.parser')
#search = soup.get_text(); 
#params = urllib.urlencode({'q':search, 'wt':'json'})
jsonS = '{\"add\":{\"doc\":{\"id\":\"'+id+'\",\"note\":\"'+str(data)+'\"}}}'
f.write(id)
f.write(jsonS)
f.write(data)
f.close()
connection = httplib.HTTPConnection("localhost",8983)
connection.request('POST','/solr/SNSave_trial/update/json?commit=true',jsonS)
response = connection.getresponse()
print """
<html><head></head>
<body>
"""
if response.status == 200:
	print 'Saved Successfully'
else:
	print 'Problem with save. Try again.'
print """
"""

print """
</body></html>
"""

print thisurl