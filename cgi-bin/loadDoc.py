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
f = open('log.txt','w')
loadId = form.getvalue("id")

params = urllib.urlencode({'q':'id:'+loadId, 'wt':'json'})
connection = httplib.HTTPConnection("localhost",8983)
connection.request('GET','/solr/SNSave_trial/select?'+params)
response = connection.getresponse()
f.write(params)
f.close()
print """
<html><head></head>
<body>
"""
data = response.read()
dictionary = json.loads(data)
#print dictionary['response']['numFound']
for doc in dictionary['response']['docs']:
	dataString = str(doc['note'])
	print dataString[3:len(dataString)-3]

print """
"""

print """
</body></html>
"""

print thisurl