#!C:\Python27\python.exe
# This outputs a copyright notice to a web page

import cgi
import httplib
import urllib
import json
import re
from bs4 import BeautifulSoup
print "Content-Type: text/html\n"
params = urllib.urlencode({'q':'*:*', 'wt':'json'})
f = open('log.txt','w')
f.write(params)

connection = httplib.HTTPConnection("localhost",8983)
connection.request('GET','/solr/SNSave_trial/select?'+params)
response = connection.getresponse()
print """
<html><head></head>
<body>
"""
data = response.read()
dictionary = json.loads(data)
print '<b>Saved Notes : </b>'
#print dictionary['response']['numFound']
print ' '
f.write(str(dictionary['response']['docs']))
for doc in dictionary['response']['docs']:
	data = str(doc['note'])
	id = str(doc['id'])
	soup = BeautifulSoup(data,'html.parser')
	data = soup.get_text();
	f.write(data)
	print '<a href=\'#\' id='+id+' class=\'loadClass\'>' + data[3:8] + '...</a>' 
	print ' '

print """
"""
f.close()
print """
</body></html>
"""

#print thisurl