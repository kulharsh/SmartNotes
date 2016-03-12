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
search = form.getvalue("q")

soup = BeautifulSoup(search,'html.parser')
search = soup.get_text(); 
params = urllib.urlencode({'q':search, 'wt':'json'})
connection = httplib.HTTPConnection("localhost",8983)
connection.request('GET','/solr/SN_trial/select?'+params)
response = connection.getresponse()
f.write(params)
f.close()
print """
<html><head></head>
<body>
"""
data = response.read()
dictionary = json.loads(data)
print dictionary['response']['numFound']
for doc in dictionary['response']['docs']:
	print doc['attr_stream_source_info']

print """
"""

print """
</body></html>
"""

print thisurl